/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Returns an appropriate ScmLogParser instance to parse an SCM log.
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public final class ScmLogParserFactory {
  private static Logger logger = LoggerFactory.getLogger(ScmLogParserFactory.class);

  private static final List<ScmLogParser> PARSERS = Arrays.asList(new GitLogParser(), new CvsLogParser());

  private ScmLogParserFactory() {
  }

  /**
   * @param command
   * @return an ScmLogParser instance that can parse a log file generated by the
   *         supplied command
   */
  public static ScmLogParser getParser(String command) {
    ScmLogParser result = null;
    for (ScmLogParser parser : PARSERS) {
      if (parser.isRecognized(command)) {
        result = parser;
        break;
      }
    }
    if (result == null) {
      logger.error("no parser found for the command \"" + command + "\"");
    }
    return result;
  }
}
