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

import java.util.Date;

/**
 *
 * @author John Amos (jamos@revitasinc.com)
 */
public class RevisionInfo {
  private final String author;

  private final Date date;

  private final String comment;

  private final int addedLineCount;

  public RevisionInfo(String author, Date date, String comment, int addedLineCount) {
    super();
    this.author = author;
    if (date != null) {
      this.date = new Date(date.getTime());
    }
    else {
      this.date = null;
    }
    this.comment = comment;
    this.addedLineCount = addedLineCount;
  }

  public String getAuthor() {
    return author;
  }

  public Date getDate() {
    Date result = null;
    if (date != null) {
      result = new Date(date.getTime());
    }
    return result;
  }

  public String getComment() {
    return comment;
  }

  public int getAddedLineCount() {
    return addedLineCount;
  }

  @Override
  public String toString() {
    return getAuthor() + " - " + getDate() + " - " + getAddedLineCount() + " - " + getComment();
  }
}
