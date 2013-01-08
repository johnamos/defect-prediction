/*
 * Defect Prediction Plugin
 * Copyright (C) 2013 Revitas, Inc.
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
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
