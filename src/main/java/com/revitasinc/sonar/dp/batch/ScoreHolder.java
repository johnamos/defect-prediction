/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
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
