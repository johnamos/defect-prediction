/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
 */

package com.revitasinc.sonar.dp.batch;

/**
 * Contains a file path and defect prediction score.
 * 
 * @author John Amos (jamos@imany.com)
 */
public class FileScore implements Comparable<FileScore> {
  private final String path;

  private final Double score;

  public FileScore(String path, Double score) {
    this.path = path;
    this.score = score;
  }

  public int compareTo(FileScore o) {
    if (score < o.score) {
      return 1;
    }
    else if (score > o.score) {
      return -1;
    }
    else {
      return path.compareTo(o.path);
    }
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return ((obj instanceof FileScore) && obj.toString().equals(toString()));
  }

  @Override
  public String toString() {
    return String.format("%.4f", score) + " " + path;
  }

  public String getPath() {
    return path;
  }

  public Double getScore() {
    return score;
  }
}
