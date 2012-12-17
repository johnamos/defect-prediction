package com.revitasinc.sonar.dp;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import com.revitasinc.sonar.dp.batch.DefectPredictionDecorator;
import com.revitasinc.sonar.dp.batch.DefectPredictionSensor;
import com.revitasinc.sonar.dp.ui.DefectPredictionRubyWidget;

/**
 * This class is the entry point for all Sonar extensions.
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
@Properties({
    @Property(key = DefectPredictionPlugin.CVS_LOGFILE_PATH, name = "Logfile Path", description = "Path to the CVS logfile"),
    @Property(key = DefectPredictionPlugin.CVS_SOURCE_PATH, name = "Source Path", description = "Path to the root folder of the source code"),
    @Property(key = DefectPredictionPlugin.AUTHOR_CHANGE_WEIGHT, name = "Author Change Weight", description = "The multiplier for a change of authors between revisions", defaultValue = DefectPredictionPlugin.DEFAULT_AUTHOR_CHANGE_WEIGHT),
    @Property(key = DefectPredictionPlugin.LINES_CHANGED_WEIGHT, name = "Lines Changed Weight", description = "The multiplier for the number of lines added or changed in a revision", defaultValue = DefectPredictionPlugin.DEFAULT_LINES_CHANGED_WEIGHT),
    @Property(key = DefectPredictionPlugin.TIME_DECAY_EXPONENT, name = "Time Decay Exponent", description = "Used in the formula exp(-xt+x) where x is this value and t is the normalized time", defaultValue = DefectPredictionPlugin.DEFAULT_TIME_DECAY_EXPONENT) })
public final class DefectPredictionPlugin extends SonarPlugin {

  public static final String PLUGIN_NAME = "Defect Prediction";

  public static final String CVS_LOGFILE_PATH = "sonar.cvs.logfile.path";

  public static final String CVS_SOURCE_PATH = "sonar.cvs.source.path";

  public static final String AUTHOR_CHANGE_WEIGHT = "sonar.author.change.weight";

  public static final String LINES_CHANGED_WEIGHT = "sonar.lines.changed.weight";

  public static final String TIME_DECAY_EXPONENT = "sonar.time.decay.exponent";

  public static final String DEFAULT_AUTHOR_CHANGE_WEIGHT = "2.0";

  public static final String DEFAULT_LINES_CHANGED_WEIGHT = "1.0";

  public static final String DEFAULT_TIME_DECAY_EXPONENT = "18.0";

  // This is where you're going to declare all your Sonar extensions
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List getExtensions() {
    return Arrays.asList(
    // Definitions
        DefectPredictionMetrics.class,

        // Batch
        DefectPredictionSensor.class, DefectPredictionDecorator.class,

        // UI
        DefectPredictionRubyWidget.class);
  }
}