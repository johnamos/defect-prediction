/**
 * Copyright
 * © 2012 Revitas, Inc. ALL RIGHTS RESERVED.
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
    this.date = date;
    this.comment = comment;
    this.addedLineCount = addedLineCount;
  }

  public String getAuthor() {
    return author;
  }

  public Date getDate() {
    return date;
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
