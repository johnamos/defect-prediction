/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public class Utils {
  /**
   * Writes the supplied Map's contents to the console.
   * 
   * @param map
   */
  public static void writeMapContents(Map<String, List<RevisionInfo>> map) {
    for (Entry<String, List<RevisionInfo>> entry : map.entrySet()) {
      System.out.println(entry.getKey());
      for (RevisionInfo info : entry.getValue()) {
        System.out.println("    " + info.toString());
      }
    }
  }
}
