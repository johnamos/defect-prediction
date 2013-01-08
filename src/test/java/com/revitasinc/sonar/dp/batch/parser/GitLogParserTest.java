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

public class GitLogParserTest {

  @Test
  public void testParse() {
    Map<String, List<RevisionInfo>> map = new GitLogParser().parse(getClass().getResourceAsStream("/dp/git.log"));
    assertNotNull(map);
    assertEquals(25, map.size());
    Utils.writeMapContents(map);
  }

}
