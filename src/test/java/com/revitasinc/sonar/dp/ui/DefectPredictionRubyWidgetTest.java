/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.ui;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DefectPredictionRubyWidgetTest {

  @Test
  public void test() {
    DefectPredictionRubyWidget widget = new DefectPredictionRubyWidget();
    assertNotNull(widget.getId());
    assertNotNull(widget.getTitle());
    assertNotNull(widget.getTemplatePath());
  }

}
