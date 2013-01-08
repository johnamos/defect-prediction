/*
 * Defect Prediction Plugin
 * Copyright (C) 2013 Revitas, Inc.
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
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
