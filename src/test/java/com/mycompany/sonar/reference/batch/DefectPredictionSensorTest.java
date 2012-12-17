/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.mycompany.sonar.reference.batch;

import static org.junit.Assert.fail;
import net.sf.statcvs.output.ConfigurationOptions;

import org.junit.Test;
import org.sonar.api.resources.Project;

import com.revitasinc.sonar.dp.batch.DefectPredictionSensor;

/**
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public class DefectPredictionSensorTest {

  @Test
  public void test() {
    try {
      String cvsPath = System.getProperty("junit.cvspath");
      if (cvsPath == null) {
        System.out
            .println("Please set the system property \"junit.cvspath\" to the folder that contains the CVS log file (cvs.log) and the checked out source code");
        return;
      }
      ConfigurationOptions.setLogFileName(cvsPath + "/cvs.log");
      ConfigurationOptions.setCheckedOutDirectory(cvsPath);
      new DefectPredictionSensor().generateMeasures(new Project(null), new MockSensorContext());
    }
    catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

}
