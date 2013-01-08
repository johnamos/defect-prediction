/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Contains methods common to ScmLogParserS.
 *
 * @author John Amos (jamos@revitasinc.com)
 */
public final class ParserHelper {
  private static Logger logger = LoggerFactory.getLogger(ParserHelper.class);

  private ParserHelper() {
  }

  /**
   * Returns an InputStream that contains the output of an SCM log command.
   *
   * @param workingDir
   * @param command
   * @return
   */
  public static InputStream getLogStream(File workingDir, String command) {
    InputStream result = null;
    try {
      Process process = new ProcessBuilder(command.split(" ")).directory(workingDir).start();
      new Thread(new StreamGobbler(process.getErrorStream(), StreamGobbler.ERR)).start();
      result = process.getInputStream();
    } catch (IOException e) {
      logger.error("", e);
    }
    return result;
  }

}
