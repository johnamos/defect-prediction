/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
 * Extracts revision data from a CVS log.
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public class CvsLogParser implements ScmLogParser {
  private static final String EMPTY_STRING = "";

  private static final String SPACE = " ";

  private static final String AUTHOR = "author: ";

  private static final String SEMICOLON = ";";

  private static final String REVISION = "revision ";

  private static final String BRANCHES = "branches:";

  private static final String LINES = "lines: ";

  private static final String DATE = "date: ";

  private static final String WORKING_FILE = "Working file: ";

  private static final String REV_SEPARATOR = "----------------------------";

  private static final String FILE_SEPARATOR = "=============================================================================";

  private static Logger logger = LoggerFactory.getLogger(CvsLogParser.class);

  public boolean isRecognized(String command) {
    return command.startsWith("cvs ");
  }

  public Map<String, List<RevisionInfo>> parse(File workingDir, String command) {
    Map<String, List<RevisionInfo>> result = new HashMap<String, List<RevisionInfo>>();
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    try {
      String currentFile = null;
      boolean isRevision = false;
      List<RevisionInfo> revList = null;
      RevisionInfo revInfo = null;
      Process process = new ProcessBuilder(command.split(SPACE)).directory(workingDir).start();
      new Thread(new StreamGobbler(process.getErrorStream(), StreamGobbler.ERR)).start();
      for (LineIterator iterator = IOUtils.lineIterator(process.getInputStream(), "UTF-8"); iterator.hasNext();) {
        String line = iterator.nextLine();
        if (line.startsWith(WORKING_FILE)) {
          currentFile = line.substring(WORKING_FILE.length());
          revList = new ArrayList<RevisionInfo>();
        }
        else if (line.equals(REV_SEPARATOR)) {
          isRevision = true;
          revInfo = null;
        }
        else if (line.equals(FILE_SEPARATOR)) {
          isRevision = false;
          putResult(result, currentFile, revList);
        }
        else if (isRevision) {
          if (isDataLine(line)) {
            revInfo = infoFromLine(line, df);
          }
          else if (isCommentLine(line, revInfo)) {
            revList.add(new RevisionInfo(revInfo.getAuthor(), revInfo.getDate(), line, revInfo.getAddedLineCount()));
            revInfo = null;
          }
        }
      }
    }
    catch (IOException e) {
      logger.error(EMPTY_STRING, e);
    }
    return result;
  }

  /**
   * @param line
   * @param revInfo
   * @return true if this is the first comment line
   */
  private boolean isCommentLine(String line, RevisionInfo revInfo) {
    return !line.startsWith(REVISION) && !line.startsWith(BRANCHES) && revInfo != null;
  }

  /**
   * @param line
   * @return true if this is a data line that contains the date of the commit
   *         and the number of lines added
   */
  private boolean isDataLine(String line) {
    return line.startsWith(DATE) && line.contains(LINES);
  }

  /**
   * Puts the supplied revList into the supplied Map using the supplied
   * currentFile as the key in the Map.
   * 
   * @param result
   * @param currentFile
   * @param revList
   */
  private void putResult(Map<String, List<RevisionInfo>> result, String currentFile, List<RevisionInfo> revList) {
    if (currentFile != null && !revList.isEmpty()) {
      result.put(currentFile, revList);
    }
  }

  /**
   * Builds a RevisionInfo object from the supplied line.
   * 
   * @param line
   * @param df
   * @return
   */
  private RevisionInfo infoFromLine(String line, DateFormat df) {
    RevisionInfo result = null;
    try {
      String author = null;
      Date date = null;
      int lineCount = 0;
      String[] secs = line.split(SEMICOLON);
      for (String sec : secs) {
        sec = sec.trim();
        if (sec.startsWith(DATE)) {
          date = df.parse(sec.substring(DATE.length()));
        }
        else if (sec.startsWith(AUTHOR)) {
          author = sec.substring(AUTHOR.length());
        }
        else if (sec.startsWith(LINES)) {
          lineCount = Integer.parseInt(sec.substring(LINES.length() + 1).split(SPACE)[0]);
        }
      }
      result = new RevisionInfo(author, date, EMPTY_STRING, lineCount);
    }
    catch (ParseException e) {
      logger.error(EMPTY_STRING, e);
    }
    return result;
  }
}
