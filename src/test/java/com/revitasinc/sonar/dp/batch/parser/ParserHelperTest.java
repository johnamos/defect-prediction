/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.parser;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ParserHelperTest {

  @Test
  public void testGetLogStream() {
    assertNull(ParserHelper.getLogStream(null, ""));
    assertNotNull(ParserHelper.getLogStream(null, "java"));
  }

}
