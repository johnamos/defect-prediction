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

import org.sonar.api.batch.Event;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.design.Dependency;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.MeasuresFilter;
import org.sonar.api.measures.Metric;
import org.sonar.api.resources.ProjectLink;
import org.sonar.api.resources.Resource;
import org.sonar.api.rules.Violation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * For unit testing.
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
@SuppressWarnings("rawtypes")
public class MockSensorContext implements SensorContext {

  private List<Measure> measures = new ArrayList<Measure>();

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

  public List<Measure> getMeasures() {
    return measures;
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
    measures.add(arg0);
    return arg0;
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
