package com.revitasinc.sonar.dp.batch;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.statcvs.input.Builder;
import net.sf.statcvs.input.CvsLogfileParser;
import net.sf.statcvs.input.LogSyntaxException;
import net.sf.statcvs.input.RepositoryFileManager;
import net.sf.statcvs.model.Repository;
import net.sf.statcvs.model.Revision;
import net.sf.statcvs.model.VersionedFile;
import net.sf.statcvs.output.ConfigurationException;
import net.sf.statcvs.output.ConfigurationOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;

import com.revitasinc.sonar.dp.DefectPredictionMetrics;
import com.revitasinc.sonar.dp.DefectPredictionPlugin;

/**
 * Assigns a score to each source file in the project and collects scores for
 * those files most likely to contain more defects.
 * 
 * @author John Amos (jamos@revitasinc.com)
 */
public class DefectPredictionSensor implements Sensor {
  private static final double TOP_SCORE = 100.0;

  private static Logger logger = LoggerFactory.getLogger(DefectPredictionSensor.class);

  private static Map<String, Double> scoreMap;

  public boolean shouldExecuteOnProject(Project project) {
    // This sensor is executed on all projects
    return true;
  }

  public void analyse(Project project, SensorContext sensorContext) {
    scoreMap = null;
    try {
      if (checkRequiredProperties(project, DefectPredictionPlugin.CVS_LOGFILE_PATH,
          "Logfile Path (sonar.cvs.logfile.path)")
          && checkRequiredProperties(project, DefectPredictionPlugin.CVS_SOURCE_PATH,
              "Source Path (sonar.cvs.source.path)")) {
        ConfigurationOptions.setLogFileName((String) project.getProperty(DefectPredictionPlugin.CVS_LOGFILE_PATH));
        ConfigurationOptions.setCheckedOutDirectory((String) project
            .getProperty(DefectPredictionPlugin.CVS_SOURCE_PATH));
        generateMeasures(project, sensorContext);
      }
    }
    catch (Exception e) {
      logger.error(e.getClass().getName() + " - " + e.getMessage(), e);
    }
  }

  /**
   * @param project
   * @param propertyName
   * @param desc
   * @return true if the property is supplied, false otherwise
   */
  private boolean checkRequiredProperties(Project project, String propertyName, String desc) {
    boolean result = true;
    if (project.getProperty(propertyName) == null) {
      logger.warn("The CVS log file path is not set in Configuration > System > General Settings > "
          + DefectPredictionPlugin.class.getSimpleName() + " > " + desc);
      result = false;
    }
    return result;
  }

  @Override
  public String toString() {
    return "DefectPredictionSensor";
  }

  /**
   * Reads the revision data from the scm repository to calculate a score for
   * each file and saves a Measure for each of the top scoring files.
   * 
   * @param sensorContext
   * @throws LogSyntaxException
   * @throws IOException
   * @throws ConfigurationException
   */
  public void generateMeasures(Project project, SensorContext sensorContext) throws LogSyntaxException, IOException,
      ConfigurationException {
    logger.info("Parsing CVS log '" + ConfigurationOptions.getLogFileName() + "'");

    Builder builder = initBuilder();
    Repository repository = builder.createCvsContent();
    logWarnings(repository, builder);
    saveScores(project, repository, sensorContext);

    builder.clean();
    builder = null;
  }

  /**
   * Initializes the Builder object for reading data from the CVS repository.
   * 
   * @return an initialized Builder object
   * @throws LogSyntaxException
   * @throws IOException
   */
  private Builder initBuilder() throws LogSyntaxException, IOException {
    Reader logReader = null;
    Builder builder = null;
    try {
      logReader = new FileReader(ConfigurationOptions.getLogFileName());
      final RepositoryFileManager repFileMan = new RepositoryFileManager(ConfigurationOptions.getCheckedOutDirectory());

      builder = new Builder(repFileMan, ConfigurationOptions.getIncludePattern(),
          ConfigurationOptions.getExcludePattern(), ConfigurationOptions.getSymbolicNamesPattern());
      new CvsLogfileParser(logReader, builder).parse();
    }
    finally {
      if (logReader != null) {
        logReader.close();
      }
    }
    if (ConfigurationOptions.getProjectName() == null) {
      ConfigurationOptions.setProjectName(builder.getProjectName());
    }
    return builder;
  }

  /**
   * Logs warnings found during the analysis.
   * 
   * @param repository
   * @param builder
   */
  private void logWarnings(Repository repository, Builder builder) {
    if (repository.isEmpty()) {
      if (builder.allRejectedByExcludePattern()) {
        logger.warn("Exclude pattern '" + ConfigurationOptions.getExcludePattern()
            + "' removed all files from repository");
      }
      else if (builder.allRejectedByIncludePattern()) {
        logger.warn("Include pattern '" + ConfigurationOptions.getIncludePattern()
            + "' rejected all files from repository");
      }
      else {
        logger.warn("Empty repository");
      }
    }
    if (builder.isLocalFilesNotFound()) {
      logger.warn("The log references many files that do not exist in the local copy.");
      logger.warn("Reports will be inaccurate or broken.");
      logger.warn("Log not generated in '" + ConfigurationOptions.getCheckedOutDirectory() + "'?");
    }
    else if (!builder.hasLocalCVSMetadata()) {
      logger.warn("No CVS metadata found in working copy. Reports may be inaccurate.");
    }
    else if (builder.isLogAndLocalFilesOutOfSync()) {
      logger.warn("Log and working copy are out of sync. Reports will be inaccurate.");
    }
  }

  /**
   * Calculates and saves the score for each file using the code from
   * https://github.com/igrigorik/bugspots/blob/master/lib/bugspots/scanner.rb
   * <p>
   * t = 1 - ((Time.now - fix.date).to_f / (Time.now - fixes.last.date)) <br>
   * hotspots[file] += 1/(1+Math.exp((-12*t)+12))
   */
  private void saveScores(Project project, Repository repository, SensorContext sensorContext) {
    final long startTime = System.currentTimeMillis();
    double authorChangeWeight = doubleFromProperty(project, DefectPredictionPlugin.AUTHOR_CHANGE_WEIGHT,
        DefectPredictionPlugin.DEFAULT_AUTHOR_CHANGE_WEIGHT);
    double lineWeight = doubleFromProperty(project, DefectPredictionPlugin.LINES_CHANGED_WEIGHT,
        DefectPredictionPlugin.DEFAULT_LINES_CHANGED_WEIGHT);
    double timeDecay = doubleFromProperty(project, DefectPredictionPlugin.TIME_DECAY_EXPONENT,
        DefectPredictionPlugin.DEFAULT_TIME_DECAY_EXPONENT);
    Set<FileScore> set = new TreeSet<FileScore>();
    double projectDuration = startTime - repository.getRevisions().iterator().next().getDate().getTime();
    for (VersionedFile file : repository.getFiles()) {
      double score = 0.0;
      String lastAuthor = null;
      for (Revision revision : file.getRevisions()) {
        if (revision.getAuthor() != null) {
          double authorChange = (lastAuthor != null && !lastAuthor.equals(revision.getAuthor().getName())) ? authorChangeWeight
              : 1.0;
          double ti = 1.0 - ((startTime - revision.getDate().getTime()) / projectDuration);
          score += authorChange * (lineWeight * revision.getNewLines()) / (1 + Math.exp((-timeDecay * ti) + timeDecay));
          lastAuthor = revision.getAuthor().getName();
        }
      }
      set.add(new FileScore(file.getFilenameWithPath(), score));
    }
    set = normalizeScores(set);
    buildScoreMap(set);
    buildSortedMeasure(set, sensorContext);
  }

  /**
   * Normalizes the score for every file in the repository so that the top score
   * is TOP_SCORE.
   * 
   * @param set
   * @return
   */
  private Set<FileScore> normalizeScores(Set<FileScore> set) {
    double topRawScore = set.iterator().next().getScore();
    Set<FileScore> newSet = new TreeSet<FileScore>();
    for (FileScore score : set) {
      newSet.add(new FileScore(score.getPath(), (score.getScore() / topRawScore) * TOP_SCORE));
    }
    return newSet;
  }

  /**
   * @param project
   * @param key
   * @param defaultValue
   * @return a double value from the property with the supplied key
   */
  private double doubleFromProperty(Project project, String key, String defaultValue) {
    double result = Double.parseDouble(defaultValue);
    try {
      result = Double.parseDouble((String) project.getProperty(key));
    }
    catch (Exception e) {
      logger.warn("The property \"" + key
          + "\" is not set to a valid number in Configuration > System > General Settings > "
          + DefectPredictionPlugin.PLUGIN_NAME + ".  Using " + defaultValue + " instead.");
    }
    return result;
  }

  /**
   * Saves one Measure for each of the top-scoring files.
   * 
   * @param set
   * @param sensorContext
   */
  private void buildSortedMeasure(Set<FileScore> set, SensorContext sensorContext) {
    int i = 0;
    for (FileScore score : set) {
      if (i >= DefectPredictionMetrics.TOP_FILES_METRICS.size()) {
        break;
      }
      String value = String.format("%.2f", score.getScore()) + "#"
          + score.getPath().substring(score.getPath().lastIndexOf('/') + 1);
      logger.debug("Top defects measure value: \"" + value + "\"");
      sensorContext.saveMeasure(new Measure(DefectPredictionMetrics.TOP_FILES_METRICS.get(i), value));
      i++;
    }
  }

  /**
   * Builds a Map that can be used by the Decorator to assign a score to each
   * file.
   * 
   * @param set
   */
  private void buildScoreMap(Set<FileScore> set) {
    scoreMap = new ConcurrentHashMap<String, Double>();
    for (FileScore score : set) {
      scoreMap.put(score.getPath(), score.getScore());
    }
  }

  /**
   * @return the Map of scores
   */
  public static Map<String, Double> getScoreMap() {
    return scoreMap;
  }
}
