/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.sonar.api.batch.Event;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.design.Dependency;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.MeasuresFilter;
import org.sonar.api.measures.Metric;
import org.sonar.api.resources.ProjectLink;
import org.sonar.api.resources.Resource;
import org.sonar.api.rules.Violation;

/**
 * For unit testing.
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public class MockSensorContext implements SensorContext {

  public Event createEvent(Resource arg0, String arg1, String arg2, String arg3, Date arg4) {
    // TODO Auto-generated method stub
    return null;
  }

  public void deleteEvent(Event arg0) {
    // TODO Auto-generated method stub

  }

  public void deleteLink(String arg0) {
    // TODO Auto-generated method stub

  }

  public Collection<Resource> getChildren(Resource arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Set<Dependency> getDependencies() {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Event> getEvents(Resource arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Collection<Dependency> getIncomingDependencies(Resource arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Measure getMeasure(Metric arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Measure getMeasure(Resource arg0, Metric arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  public <M> M getMeasures(MeasuresFilter<M> arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public <M> M getMeasures(Resource arg0, MeasuresFilter<M> arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  public Collection<Dependency> getOutgoingDependencies(Resource arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Resource getParent(Resource arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public <R extends Resource> R getResource(R arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean index(Resource arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean index(Resource arg0, Resource arg1) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean isExcluded(Resource arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean isIndexed(Resource arg0, boolean arg1) {
    // TODO Auto-generated method stub
    return false;
  }

  public Dependency saveDependency(Dependency arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public void saveLink(ProjectLink arg0) {
    // TODO Auto-generated method stub

  }

  public Measure saveMeasure(Measure arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Measure saveMeasure(Metric arg0, Double arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  public Measure saveMeasure(Resource arg0, Measure arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  public Measure saveMeasure(Resource arg0, Metric arg1, Double arg2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Deprecated
  public String saveResource(Resource arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public void saveSource(Resource arg0, String arg1) {
    // TODO Auto-generated method stub

  }

  public void saveViolation(Violation arg0) {
    // TODO Auto-generated method stub

  }

  public void saveViolation(Violation arg0, boolean arg1) {
    // TODO Auto-generated method stub

  }

  public void saveViolations(Collection<Violation> arg0) {
    // TODO Auto-generated method stub

  }

}
