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

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Reads output streams from a process spawned using Runtime.exec(). From
 * http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html?page=4.
 *
 * @author John Amos (jamos@imany.com)
 */
public class StreamGobbler implements Runnable {
  private static Logger logger = LoggerFactory.getLogger(StreamGobbler.class);

  private final InputStream is;

  private final String type;

  public static final String ERR = "ERR";

  public static final String OUT = "OUT";

  public StreamGobbler(InputStream is, String type) {
    this.is = is;
    this.type = type;
  }

  public void run() {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(is, CharEncoding.UTF_8));
      String line = null;
      while ((line = br.readLine()) != null) {
        if (ERR.equals(type)) {
          logger.debug(ERR + ">" + line);
        }
        else {
          logger.debug(line);
        }
      }
    } catch (IOException ioe) {
      logger.error("", ioe);
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          logger.error("", e);
        }
      }
      logger.debug("StreamGobbler exit for " + type);
    }
  }
}
