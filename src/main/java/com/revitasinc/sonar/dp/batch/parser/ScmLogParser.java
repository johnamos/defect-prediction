/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.revitasinc.sonar.dp.batch.RevisionInfo;

/**
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public interface ScmLogParser {
  /**
   * @param command
   * @return true if this ScmLogParser can use this command
   */
  boolean isRecognized(String command);

  /**
   * Creates a Map by generating and then parsing an scm log file.
   * 
   * @param workingDir
   * @param command
   * @return
   */
  Map<String, List<RevisionInfo>> parse(File workingDir, String command);
}
