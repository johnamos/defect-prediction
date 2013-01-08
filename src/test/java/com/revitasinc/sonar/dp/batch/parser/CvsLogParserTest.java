/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

import com.revitasinc.sonar.dp.batch.RevisionInfo;
import com.revitasinc.sonar.dp.batch.Utils;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author John Amos (jamos@revitasinc.com)
 */
public class CvsLogParserTest {

  @Test
  public void testParse() {
    Map<String, List<RevisionInfo>> map = new CvsLogParser().parse(getClass().getResourceAsStream("/dp/cvs.log"));
    assertNotNull(map);
    assertEquals(2, map.size());
    Utils.writeMapContents(map);
  }
}
