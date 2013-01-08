/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DefectPredictionPluginTest {

  @Test
  public void testGetExtensions() {
    assertNotNull(new DefectPredictionPlugin().getExtensions());
  }

}
