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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefectPredictionSensor uses this class to store scores in memory
 * for use later by DefectPredictionDecorator.
 *
 * @author John Amos (jamos@revitasinc.com)
 */
public final class ScoreHolder {
  private static Map<String, Double> scoreMap;

  private ScoreHolder() {
  }

  /**
   * Builds a Map that can be used to assign a score to each
   * file.
   *
   * @param set
   */
  public static void buildScoreMap(Set<FileScore> set) {
    scoreMap = new ConcurrentHashMap<String, Double>();
    for (FileScore score : set) {
      scoreMap.put(score.getPath(), score.getScore());
    }
  }

  /**
   * @return the Map of scores
   */
  public static Map<String, Double> getScoreMap() {
    return scoreMap;
  }
}
