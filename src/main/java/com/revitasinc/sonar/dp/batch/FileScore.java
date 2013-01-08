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
