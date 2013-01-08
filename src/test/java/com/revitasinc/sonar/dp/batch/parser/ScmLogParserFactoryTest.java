/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ScmLogParserFactoryTest {

  @Test
  public void testGetParser() {
    assertTrue(ScmLogParserFactory.getParser("cvs log -b") instanceof CvsLogParser);
    assertTrue(ScmLogParserFactory.getParser("git log --numstat") instanceof GitLogParser);
    assertNull(ScmLogParserFactory.getParser(""));
  }

}
