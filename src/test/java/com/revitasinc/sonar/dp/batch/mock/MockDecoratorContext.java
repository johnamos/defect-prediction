/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch.mock;

import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.batch.Event;
import org.sonar.api.design.Dependency;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.MeasuresFilter;
import org.sonar.api.measures.Metric;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.api.rules.Violation;
import org.sonar.api.violations.ViolationQuery;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class MockDecoratorContext implements DecoratorContext {
  private final Project project;

  @SuppressWarnings("deprecation")
  public MockDecoratorContext() {
    project = new Project("key");
    project.setFileSystem(new MockProjectFileSystem());
  }

  public Project getProject() {
    return project;
  }

  public Resource getResource() {
    // TODO Auto-generated method stub
    return null;
  }

  public List<DecoratorContext> getChildren() {
    // TODO Auto-generated method stub
    return null;
  }

  public Measure getMeasure(Metric metric) {
    // TODO Auto-generated method stub
    return null;
  }

  public <M> M getMeasures(MeasuresFilter<M> filter) {
    // TODO Auto-generated method stub
    return null;
  }

  public Collection<Measure> getChildrenMeasures(MeasuresFilter filter) {
    // TODO Auto-generated method stub
    return null;
  }

  public Collection<Measure> getChildrenMeasures(Metric metric) {
    // TODO Auto-generated method stub
    return null;
  }

  public DecoratorContext saveMeasure(Measure measure) {
    // TODO Auto-generated method stub
    return null;
  }

  public DecoratorContext saveMeasure(Metric metric, Double value) {
    // TODO Auto-generated method stub
    return null;
  }

  public Dependency saveDependency(Dependency dependency) {
    // TODO Auto-generated method stub
    return null;
  }

  public Set<Dependency> getDependencies() {
    // TODO Auto-generated method stub
    return null;
  }

  public Collection<Dependency> getIncomingDependencies() {
    // TODO Auto-generated method stub
    return null;
  }

  public Collection<Dependency> getOutgoingDependencies() {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Violation> getViolations(ViolationQuery violationQuery) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Violation> getViolations() {
    // TODO Auto-generated method stub
    return null;
  }

  public DecoratorContext saveViolation(Violation violation, boolean force) {
    // TODO Auto-generated method stub
    return null;
  }

  public DecoratorContext saveViolation(Violation violation) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Event> getEvents() {
    // TODO Auto-generated method stub
    return null;
  }

  public Event createEvent(String name, String description, String category, Date date) {
    // TODO Auto-generated method stub
    return null;
  }

  public void deleteEvent(Event event) {
    // TODO Auto-generated method stub

  }

}
