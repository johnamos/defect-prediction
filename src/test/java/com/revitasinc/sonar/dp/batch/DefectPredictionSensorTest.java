/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public class DefectPredictionSensorTest {

  @Test
  public void testSaveScores() {
    Map<String, List<RevisionInfo>> map = new HashMap<String, List<RevisionInfo>>();
    map.put(
        "somepath/somefile1.java",
        Arrays.asList(new RevisionInfo("jamos", buildDate(2012, 1, 1), "", 1),
            new RevisionInfo("jamos", buildDate(2012, 3, 1), "", 15)));
    map.put(
        "somepath/somefile2.java",
        Arrays.asList(new RevisionInfo("jamos", buildDate(2012, 4, 1), "", 1),
            new RevisionInfo("jamos", buildDate(2012, 6, 1), "", 15)));
    map.put(
        "somepath/somefile3.java",
        Arrays.asList(new RevisionInfo("jamos", buildDate(2012, 5, 1), "", 1),
            new RevisionInfo("jamos", buildDate(2012, 7, 1), "", 15)));
    MockSensorContext sensorContext = new MockSensorContext();
    new DefectPredictionSensor().saveScores(map, sensorContext, 2.0, 1.0, 18.0);
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
