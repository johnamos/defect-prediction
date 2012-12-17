package com.revitasinc.sonar.dp;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * Defines metrics.
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public final class DefectPredictionMetrics implements Metrics {
  private static final int FILE_COUNT = 10;

  public static final List<Metric> TOP_FILES_METRICS = new ArrayList<Metric>();

  public static final Metric DEFECT_PREDICTION = new Metric.Builder("defect_prediction", "Defect Prediction",
      Metric.ValueType.FLOAT).setDescription("Predicts relative defect count in source code files")
      .setDirection(Metric.DIRECTION_WORST).setQualitative(false).setDomain(CoreMetrics.DOMAIN_GENERAL).create();

  static {
    for (int i = 1; i <= FILE_COUNT; i++) {
      TOP_FILES_METRICS.add(new Metric.Builder("top_defects_" + i, "Most Likely File " + i, Metric.ValueType.STRING)
          .setDescription("File most likely to contain defects (rank = " + i + ")")
          .setDirection(Metric.DIRECTION_WORST).setQualitative(false).setDomain(CoreMetrics.DOMAIN_GENERAL).create());
    }
  }

  // getMetrics() method is defined in the Metrics interface and is used by
  // Sonar to retrieve the list of new metrics
  public List<Metric> getMetrics() {
    List<Metric> result = new ArrayList<Metric>(TOP_FILES_METRICS);
    result.add(DEFECT_PREDICTION);
    return result;
  }
}
