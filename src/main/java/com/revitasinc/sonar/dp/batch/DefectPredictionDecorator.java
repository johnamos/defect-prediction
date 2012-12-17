package com.revitasinc.sonar.dp.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.measures.MeasureUtils;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.api.resources.Scopes;

import com.revitasinc.sonar.dp.DefectPredictionMetrics;

public class DefectPredictionDecorator implements Decorator {
  private static Logger logger = LoggerFactory.getLogger(DefectPredictionDecorator.class);

  public boolean shouldExecuteOnProject(Project project) {
    return true;
  }

  @SuppressWarnings("rawtypes")
  public void decorate(Resource resource, DecoratorContext context) {
    // This method is executed on the whole tree of resources.
    // Bottom-up navigation : Java methods -> Java classes -> files -> packages
    // -> modules -> project
    if (Scopes.isFile(resource) && DefectPredictionSensor.getScoreMap() != null) {
      // Add a measure to the current file
      Double val = DefectPredictionSensor.getScoreMap().get(resource.getKey());
      if (val != null) {
        context.saveMeasure(DefectPredictionMetrics.DEFECT_PREDICTION, val);
      }
      logger.debug("resource.getKey(): " + resource.getKey());
    }
    else if (Scopes.isHigherThan(resource, Scopes.FILE)) {
      // sum values at higher levels
      context.saveMeasure(DefectPredictionMetrics.DEFECT_PREDICTION,
          MeasureUtils.sum(true, context.getChildrenMeasures(DefectPredictionMetrics.DEFECT_PREDICTION)));
    }
  }

  @Override
  public String toString() {
    return "DefectPredictionDecorator";
  }
}
