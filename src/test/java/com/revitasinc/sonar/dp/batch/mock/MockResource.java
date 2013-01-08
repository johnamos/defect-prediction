/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
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
