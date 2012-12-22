/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.revitasinc.sonar.dp.batch.RevisionInfo;
import com.revitasinc.sonar.dp.batch.Utils;

/**
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public class CvsLogParserTest {

  @Test
  public void testParse() {
    String path = System.getProperty("junit.cvs.log.file.path");
    if (path != null) {
      Map<String, List<RevisionInfo>> map = new CvsLogParser().parse(new File(path), "cvs log");
      assertNotNull(map);
      Utils.writeMapContents(map);
    }
  }
}
