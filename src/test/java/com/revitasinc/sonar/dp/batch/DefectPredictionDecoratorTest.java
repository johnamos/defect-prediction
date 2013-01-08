/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch;

import com.revitasinc.sonar.dp.batch.mock.MockDecoratorContext;
import com.revitasinc.sonar.dp.batch.mock.MockResource;
import org.junit.Test;
import org.sonar.api.resources.Scopes;

import static org.junit.Assert.assertTrue;

public class DefectPredictionDecoratorTest {

  @Test
  public void testDecorate() {
    DefectPredictionDecorator decorator = new DefectPredictionDecorator();
    System.out.println("testing \"" + decorator + "\"");
    assertTrue(decorator.shouldExecuteOnProject(new MockDecoratorContext().getProject()));
    // populate the score map
    new DefectPredictionSensorTest().testSaveScores();
    decorator.decorate(new MockResource(Scopes.FILE), new MockDecoratorContext());
    decorator.decorate(new MockResource(Scopes.DIRECTORY), new MockDecoratorContext());
  }

}
