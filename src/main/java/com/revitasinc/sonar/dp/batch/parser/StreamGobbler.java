/**
 * Copyright
 * � 2002-2008 I-MANY, INC. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

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
      br = new BufferedReader(new InputStreamReader(is));
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
