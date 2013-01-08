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
package com.revitasinc.sonar.dp.batch.mock;

import org.sonar.api.resources.Language;
import org.sonar.api.resources.Resource;

@SuppressWarnings("rawtypes")
public class MockResource extends Resource {
  private final String scope;

  public MockResource(String scope) {
    super();
    this.scope = scope;
    setKey(getLongName());
  }

  @Override
  protected void setKey(String s) {
    super.setKey(s);
  }

  @Override
  public String getName() {
    return getLongName();
  }

  @Override
  public String getLongName() {
    return "com.mycompany.module.Somefile1";
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Language getLanguage() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getScope() {
    return scope;
  }

  @Override
  public String getQualifier() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resource getParent() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean matchFilePattern(String antPattern) {
    // TODO Auto-generated method stub
    return false;
  }

}
