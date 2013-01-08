/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch;

import com.revitasinc.sonar.dp.batch.mock.MockConfiguration;
import com.revitasinc.sonar.dp.batch.mock.MockSensorContext;
import org.junit.Test;
import org.sonar.api.resources.Project;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author John Amos (jamos@revitasinc.com)
 */
public class DefectPredictionSensorTest {

  @Test
  public void testSaveScores() {
    Map<String, List<RevisionInfo>> map = new HashMap<String, List<RevisionInfo>>();
    map.put(
        "src/com/mycompany/module/Somefile1.java",
        Arrays.asList(new RevisionInfo("jamos", buildDate(2012, 1, 1), "", 1),
            new RevisionInfo("jamos", buildDate(2012, 3, 1), "", 15)));
    map.put(
        "src/com/mycompany/module/Somefile2.java",
        Arrays.asList(new RevisionInfo("jamos", buildDate(2012, 4, 1), "", 1),
            new RevisionInfo("jamos", buildDate(2012, 6, 1), "", 15)));
    map.put(
        "src/com/mycompany/module/Somefile3.java",
        Arrays.asList(new RevisionInfo("jamos", buildDate(2012, 5, 1), "", 1),
            new RevisionInfo("jamos", buildDate(2012, 7, 1), "", 15)));
    MockSensorContext sensorContext = new MockSensorContext();
    Project project = new Project(null);
    project.setConfiguration(new MockConfiguration());
    new DefectPredictionSensor().saveScores(project, map, sensorContext, 2.0, 1.0, 18.0, null, null);
    assertEquals(3, sensorContext.getMeasures().size());
    Utils.writeMapContents(map);
  }

  /**
   * @param year
   * @param month
   * @param date
   * @return
   */
  public static Date buildDate(int year, int month, int date) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, date, 0, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }
}
