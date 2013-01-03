/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revitasinc.sonar.dp.batch.RevisionInfo;

/**
 * Extracts revision data from a Git log.
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public class GitLogParser implements ScmLogParser {
  private static final String TAB = "\t";

  private static final String FILE_LINE_REGEX = "\\d*\t\\d*\t.*";

  private static final String EMAIL_START = " <";

  private static final String EMPTY_STRING = "";

  private static final String COMMIT = "commit ";

  private static final String AUTHOR = "Author: ";

  private static final String DATE = "Date: ";

  private static Logger logger = LoggerFactory.getLogger(GitLogParser.class);

  public boolean isRecognized(String command) {
    return command.startsWith("git ");
  }

  public Map<String, List<RevisionInfo>> parse(File workingDir, String command) {
    Map<String, List<RevisionInfo>> result = new HashMap<String, List<RevisionInfo>>();
    // Wed May 2 15:24:20 2012 -0700
    DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy");
    try {
      RevisionInfo revInfo = null;
      String author = null;
      Date date = null;
      Process process = new ProcessBuilder(command.split(" ")).directory(workingDir).start();
      new Thread(new StreamGobbler(process.getErrorStream(), StreamGobbler.ERR)).start();
      for (LineIterator iterator = IOUtils.lineIterator(process.getInputStream(), "UTF-8"); iterator.hasNext();) {
        String line = iterator.nextLine();
        if (line.startsWith(AUTHOR)) {
          author = line.split(EMAIL_START)[0].substring(AUTHOR.length());
          revInfo = null;
        }
        else if (line.startsWith(DATE)) {
          date = df.parse(line.substring(DATE.length()).trim());
        }
        else if (!line.trim().isEmpty() && !line.startsWith(COMMIT)) {
          if (revInfo == null) {
            revInfo = new RevisionInfo(author, date, line.trim(), 0);
            author = null;
            date = null;
          }
          else if (line.matches(FILE_LINE_REGEX)) {
            String[] segs = line.split(TAB);
            List<RevisionInfo> list = result.get(segs[2]);
            if (list == null) {
              list = new ArrayList<RevisionInfo>();
              result.put(segs[2], list);
            }
            list.add(new RevisionInfo(revInfo.getAuthor(), revInfo.getDate(), revInfo.getComment(), Integer
                .parseInt(segs[0])));
          }
        }
      }
    }
    catch (Exception e) {
      logger.error(EMPTY_STRING, e);
    }
    return result;
  }
}