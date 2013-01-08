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

import org.sonar.api.resources.InputFile;
import org.sonar.api.resources.Language;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.api.resources.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("rawtypes")
public class MockProjectFileSystem implements ProjectFileSystem {
  public static final String BASEDIR = "/home/test/project";

  public static final String SOURCEDIR = BASEDIR + "/src";

  public MockProjectFileSystem() {
  }

  public Charset getSourceCharset() {
    // TODO Auto-generated method stub
    return null;
  }

  public File getBasedir() {
    return new File(BASEDIR);
  }

  public File getBuildDir() {
    // TODO Auto-generated method stub
    return null;
  }

  public File getBuildOutputDir() {
    // TODO Auto-generated method stub
    return null;
  }

  public List<File> getSourceDirs() {
    return Arrays.asList(new File(SOURCEDIR));
  }

  @Deprecated
  public ProjectFileSystem addSourceDir(File dir) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<File> getTestDirs() {
    // TODO Auto-generated method stub
    return null;
  }

  @Deprecated
  public ProjectFileSystem addTestDir(File dir) {
    // TODO Auto-generated method stub
    return null;
  }

  public File getReportOutputDir() {
    // TODO Auto-generated method stub
    return null;
  }

  public File getSonarWorkingDirectory() {
    // TODO Auto-generated method stub
    return null;
  }

  public File resolvePath(String path) {
    // TODO Auto-generated method stub
    return null;
  }

  @Deprecated
  public List<File> getSourceFiles(Language... langs) {
    // TODO Auto-generated method stub
    return null;
  }

  @Deprecated
  public List<File> getJavaSourceFiles() {
    // TODO Auto-generated method stub
    return null;
  }

  @Deprecated
  public boolean hasJavaSourceFiles() {
    // TODO Auto-generated method stub
    return false;
  }

  @Deprecated
  public List<File> getTestFiles(Language... langs) {
    // TODO Auto-generated method stub
    return null;
  }

  @Deprecated
  public boolean hasTestFiles(Language lang) {
    // TODO Auto-generated method stub
    return false;
  }

  public File writeToWorkingDirectory(String content, String fileName) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  public File getFileFromBuildDirectory(String filename) {
    // TODO Auto-generated method stub
    return null;
  }

  public Resource toResource(File file) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<InputFile> mainFiles(String... langs) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<InputFile> testFiles(String... langs) {
    // TODO Auto-generated method stub
    return null;
  }

}
