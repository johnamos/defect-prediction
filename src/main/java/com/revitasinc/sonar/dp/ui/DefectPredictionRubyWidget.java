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
package com.revitasinc.sonar.dp.ui;

import com.revitasinc.sonar.dp.DefectPredictionPlugin;
import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.Description;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.UserRole;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetProperties;
import org.sonar.api.web.WidgetProperty;
import org.sonar.api.web.WidgetPropertyType;

/**
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
@UserRole(UserRole.USER)
@Description("Displays files most likely to contain a higher defect count")
@WidgetCategory(DefectPredictionPlugin.PLUGIN_NAME)
@WidgetProperties({
  @WidgetProperty(key = "fileCount", description = "number of files to display", type = WidgetPropertyType.INTEGER, defaultValue = "5"),
  @WidgetProperty(key = "width", description = "width of the chart", type = WidgetPropertyType.INTEGER, defaultValue = "800"),
  @WidgetProperty(key = "height", description = "height of the chart", type = WidgetPropertyType.INTEGER, defaultValue = "125")})
public class DefectPredictionRubyWidget extends AbstractRubyTemplate implements RubyRailsWidget {

  public String getId() {
    return "defectPrediction";
  }

  public String getTitle() {
    return DefectPredictionPlugin.PLUGIN_NAME;
  }

  @Override
  protected String getTemplatePath() {
    return "/dp/dp_widget.html.erb";
  }
}
