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
