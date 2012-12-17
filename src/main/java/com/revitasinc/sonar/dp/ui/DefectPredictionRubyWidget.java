package com.revitasinc.sonar.dp.ui;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.Description;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.UserRole;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetProperties;
import org.sonar.api.web.WidgetProperty;
import org.sonar.api.web.WidgetPropertyType;

import com.revitasinc.sonar.dp.DefectPredictionPlugin;

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
    @WidgetProperty(key = "height", description = "height of the chart", type = WidgetPropertyType.INTEGER, defaultValue = "125") })
public class DefectPredictionRubyWidget extends AbstractRubyTemplate implements RubyRailsWidget {

  public String getId() {
    return "defectPrediction";
  }

  public String getTitle() {
    return DefectPredictionPlugin.PLUGIN_NAME;
  }

  @Override
  protected String getTemplatePath() {
    return "C:/john/eclipse-workspace/sonar-examples/src/main/resources/dp/dp_widget.html.erb";
  }
}