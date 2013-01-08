package com.revitasinc.sonar.dp;

import com.revitasinc.sonar.dp.batch.DefectPredictionDecorator;
import com.revitasinc.sonar.dp.batch.DefectPredictionSensor;
import com.revitasinc.sonar.dp.ui.DefectPredictionRubyWidget;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * This class is the entry point for all Sonar extensions.
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
@Properties({
  @Property(key = DefectPredictionPlugin.COMMENT_REGEX, name = "Comment Regex (Optional)",
    description = "A regular expression (Java style) for including revisions by commit comment"),
  @Property(key = DefectPredictionPlugin.FILE_NAME_REGEX, name = "File Name Regex (Optional)", description = "A regular expression (Java style) for file names to include"),
  @Property(key = DefectPredictionPlugin.COMMAND, name = "SCM Command (Required)",
    description = "The command to run on this system to generate a log file from your source code management system"),
  @Property(key = DefectPredictionPlugin.SOURCE_PATH, name = "Source Path (Optional)",
    description = "Path to the root folder of the source code, either absolute or relative to the project path"),
  @Property(key = DefectPredictionPlugin.AUTHOR_CHANGE_WEIGHT, name = "Author Change Weight (Optional)", description = "The multiplier for a change of authors between revisions",
    defaultValue = DefectPredictionPlugin.DEFAULT_AUTHOR_CHANGE_WEIGHT),
  @Property(key = DefectPredictionPlugin.LINES_CHANGED_WEIGHT, name = "Lines Changed Weight (Optional)",
    description = "The multiplier for the number of lines added or changed in a revision", defaultValue = DefectPredictionPlugin.DEFAULT_LINES_CHANGED_WEIGHT),
  @Property(key = DefectPredictionPlugin.TIME_DECAY_EXPONENT, name = "Time Decay Exponent (Optional)",
    description = "Used in the formula exp(-xt+x) where x is this value and t is the normalized time", defaultValue = DefectPredictionPlugin.DEFAULT_TIME_DECAY_EXPONENT)})
public final class DefectPredictionPlugin extends SonarPlugin {

  public static final String PLUGIN_NAME = "Defect Prediction";

  public static final String COMMENT_REGEX = "sonar.dp.comment.regex";

  public static final String FILE_NAME_REGEX = "sonar.dp.file.name.regex";

  public static final String COMMAND = "sonar.dp.scm.command";

  public static final String SOURCE_PATH = "sonar.dp.source.path";

  public static final String AUTHOR_CHANGE_WEIGHT = "sonar.dp.author.change.weight";

  public static final String LINES_CHANGED_WEIGHT = "sonar.dp.lines.changed.weight";

  public static final String TIME_DECAY_EXPONENT = "sonar.dp.time.decay.exponent";

  public static final String DEFAULT_AUTHOR_CHANGE_WEIGHT = "2.0";

  public static final String DEFAULT_LINES_CHANGED_WEIGHT = "1.0";

  public static final String DEFAULT_TIME_DECAY_EXPONENT = "18.0";

  // This is where you're going to declare all your Sonar extensions
  @SuppressWarnings({"unchecked", "rawtypes"})
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
