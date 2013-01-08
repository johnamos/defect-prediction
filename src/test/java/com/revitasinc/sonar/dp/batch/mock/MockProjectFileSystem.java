/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
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
