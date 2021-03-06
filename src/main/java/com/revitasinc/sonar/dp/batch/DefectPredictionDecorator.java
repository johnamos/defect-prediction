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

import com.revitasinc.sonar.dp.DefectPredictionMetrics;
import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.measures.MeasureUtils;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.api.resources.Scopes;

import java.io.File;

/**
 * Sets the score on each resource and sums the scores at higher levels.
 *
 * @author John Amos (jamos@revitasinc.com)
 */
public class DefectPredictionDecorator implements Decorator {
  public boolean shouldExecuteOnProject(Project project) {
    return true;
  }

  @SuppressWarnings("rawtypes")
  public void decorate(Resource resource, DecoratorContext context) {
    // This method is executed on the whole tree of resources.
    // Bottom-up navigation : Java methods -> Java classes -> files -> packages
    // -> modules -> project
    if (Scopes.isFile(resource) && ScoreHolder.getScoreMap() != null) {
      // Add a measure to the current file
      Double val = findValueForResource(resource, context);
      if (val != null) {
        context.saveMeasure(DefectPredictionMetrics.DEFECT_PREDICTION, val);
      }
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

  /**
   * Tries several paths for the supplied resource to get the score from the Map
   * built from the SCM log file.
   *
   * @param resource
   * @param context
   * @return
   */
  @SuppressWarnings("rawtypes")
  private Double findValueForResource(Resource resource, DecoratorContext context) {
    String filename = resource.getKey().replace('.', '/') + ".java";
    Double val = ScoreHolder.getScoreMap().get(filename);
    if (val == null) {
      String basepath = context.getProject().getFileSystem().getBasedir().getPath();
      for (File dir : context.getProject().getFileSystem().getSourceDirs()) {
        String dirpath = dir.getPath();
        if (dirpath.startsWith(basepath) && dirpath.length() > basepath.length()) {
          String prepath = dirpath.substring(basepath.length() + 1).replace('\\', '/');
          val = ScoreHolder.getScoreMap().get(prepath + "/" + filename);
          if (val != null) {
            break;
          }
        }
      }
    }
    return val;
  }
}
