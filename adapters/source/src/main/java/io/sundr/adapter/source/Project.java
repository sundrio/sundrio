package io.sundr.adapter.source;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.sundr.model.Nameable;
import io.sundr.utils.Patterns;

public class Project {

  public static final File DIR = new File(".");

  public static final String NEWLINE = "\n";

  public static final String GIT = System.getProperty(".git");

  public static final String USER_HOME = System.getProperty("user.home");
  public static final String CURRENT_DIR = System.getProperty("user.dir");

  private final File moduleRoot;
  private final File src;
  private final File srcMain;
  private final File srcMainJava;
  private final File srcTest;
  private final File srcTestJava;

  public Project(File moduleRoot) {
    this.moduleRoot = moduleRoot;
    this.src = new File(moduleRoot, "src");
    this.srcMain = new File(src, "main");
    this.srcMainJava = new File(srcMain, "java");
    this.srcTest = new File(src, "test");
    this.srcTestJava = new File(srcTest, "java");
  }

  public static Project getProject() {
    return new Project(moduleRoot());
  }

  public static Project getProject(Path projectRoot) {
    return new Project(projectRoot.toFile());
  }

  public File getModuleRoot() {
    return moduleRoot;
  }

  public File getSrc() {
    return src;
  }

  public File getSrcMain() {
    return srcMain;
  }

  public File getSrcMainJava() {
    return srcMainJava;
  }

  public File getSrcTest() {
    return srcTest;
  }

  public File getSrcTestJava() {
    return srcTestJava;
  }

  private static File moduleRoot() {
    File currentDir = Optional.ofNullable(CURRENT_DIR).map(File::new).orElse(DIR);

    while (currentDir != null) {
      if (currentDir.getAbsolutePath().equals(USER_HOME)) {
        return currentDir;
      }

      File gitDir = new File(currentDir, ".git");
      if (gitDir.exists() && gitDir.isDirectory()) {
        return currentDir;
      }

      File pomFile = new File(currentDir, "pom.xml");
      File gradleFile = new File(currentDir, "build.gradle");
      if (pomFile.exists() || gradleFile.exists()) {
        return currentDir;
      }

      currentDir = currentDir.getParentFile();
    }
    return currentDir;
  }

  /**
   * Get the optional package of the fully qualified class name.
   *
   * @param fqcn the fully qualified class name
   * @return the package wrapped in optional or empty.
   **/
  public static Optional<String> packageOf(String fqcn) {
    return fqcn.contains(".")
        ? Optional.of(fqcn.substring(0, fqcn.lastIndexOf(".")))
        : Optional.empty();
  }

  /**
   * Get the class name of the fully qualified class name.
   *
   * @param fqcn the fully qualified class name
   * @return the class name.
   **/
  public static String classNameOf(String fqcn) {
    return fqcn.substring(fqcn.lastIndexOf(".") + 1);
  }

  /**
   * Search the project and find a java source file matching the specified
   * fully qualified class name.
   * The function searches the project sources.
   *
   * @param sourceRoot the root source directory to use.
   * @param fqcn the fully qualified class name
   * @return an optional path or empty if file not found.
   **/
  private Optional<Path> findJavaFile(File sourceRoot, String fqcn) {
    Optional<String> packageName = packageOf(fqcn);
    String className = classNameOf(fqcn);

    if (packageName.isPresent()) {
      Optional<Path> path = packageName.map(p -> p.replace(".", File.separator))
          .map(p -> new File(sourceRoot, p))
          .map(f -> new File(f, className + ".java"))
          .filter(File::exists)
          .map(File::toPath);
      return path;
    }

    try (Stream<Path> paths = Files.walk(sourceRoot.toPath())) {
      return paths.filter(p -> p.toFile().getName().equals(className + ".java")).findFirst();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Search the project and find a java source file matching the specified
   * fully qualified class name.
   * The function searches the project sources.
   *
   * @param fqcn the fully qualified class name
   * @return an optional path or empty if file not found.
   **/
  public Optional<Path> findJavaSourceFile(String fqcn) {
    return findJavaFile(srcMainJava, fqcn);
  }

  /**
   * Search the project and find a java test file matching the specified
   * fully qualified class name.
   * The function searches the project sources.
   *
   * @param fqcn the fully qualified class name
   * @return an optional path or empty if file not found.
   **/
  public Optional<Path> findJavaTestFile(String fqcn) {
    return findJavaFile(srcTestJava, fqcn);
  }

  /**
   * Read the content of the file
   *
   * @param path The path to the file
   * @return The content in String
   */
  public static String readFile(Path path) {
    try {
      return Files.readAllLines(path).stream().collect(Collectors.joining(NEWLINE));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * List all Java source files in the project.
   * The function searches the project main sources directory recursively.
   *
   * @return a list of paths to Java source files
   */
  public List<Path> listJavaSourceFiles() {
    if (!srcMainJava.exists()) {
      return List.of();
    }
    try (Stream<Path> paths = Files.walk(srcMainJava.toPath())) {
      return paths.filter(p -> p.toFile().isFile() && p.toString().endsWith(".java"))
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * List all Java test files in the project.
   * The function searches the project test sources directory recursively.
   *
   * @return a list of paths to Java test files
   */
  public List<Path> listJavaTestFiles() {
    if (!srcTestJava.exists()) {
      return List.of();
    }
    try (Stream<Path> paths = Files.walk(srcTestJava.toPath())) {
      return paths.filter(p -> p.toFile().isFile() && p.toString().endsWith(".java"))
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * List all Java files in the project, including both source and test files.
   * The function combines results from both main and test sources directories.
   *
   * @return a list of paths to all Java files (source and test)
   */
  public List<Path> listJavaFiles() {
    List<Path> allFiles = listJavaSourceFiles();
    allFiles.addAll(listJavaTestFiles());
    return allFiles;
  }

  /**
   * Returns a fluent API for selecting and filtering source files.
   *
   * @return a source file selector
   */
  public SourceSelector sources() {
    return new SourceSelector(SourceType.SOURCES);
  }

  /**
   * Returns a fluent API for selecting and filtering test source files.
   *
   * @return a test source file selector
   */
  public SourceSelector testSources() {
    return new SourceSelector(SourceType.TEST_SOURCES);
  }

  /**
   * Returns a fluent API for selecting and filtering all source files (both main and test).
   *
   * @return an all sources file selector
   */
  public SourceSelector allSources() {
    return new SourceSelector(SourceType.ALL_SOURCES);
  }

  /**
   * Source type enumeration for file selection.
   */
  public enum SourceType {
    SOURCES, TEST_SOURCES, ALL_SOURCES
  }

  /**
   * Fluent API for selecting and filtering project source files.
   */
  public class SourceSelector {
    private final SourceType sourceType;
    private String[] includePatterns = new String[0];
    private String[] excludePatterns = new String[0];

    private SourceSelector(SourceType sourceType) {
      this.sourceType = sourceType;
    }

    /**
     * Adds inclusion patterns. Only files matching at least one of these patterns will be included.
     * Patterns can be glob patterns (*.java, *Test.java) or regex patterns.
     *
     * @param patterns the patterns to include*@return this selector for method chaining
     * @return this selector for method chaining
     **/
    public SourceSelector including(String... patterns) {
      this.includePatterns = patterns != null ? patterns : new String[0];
      return this;
    }

    /**
     * Adds exclusion patterns. Files matching any of these patterns will be excluded.
     * Patterns can be glob patterns (*.java, *Test.java) or regex patterns.
     *
     * @param patterns the patterns to exclude
     * @return this selector for method chaining
     **/
    public SourceSelector excluding(String... patterns) {
      this.excludePatterns = patterns != null ? patterns : new String[0];
      return this;
    }

    /**
     * Returns the list of files matching the configured criteria.
     *
     * @return list of paths matching the selection criteria
     */
    public List<Path> list() {
      // Get base file list depending on source type
      List<Path> baseFiles;
      switch (sourceType) {
        case SOURCES:
          baseFiles = listJavaSourceFiles();
          break;
        case TEST_SOURCES:
          baseFiles = listJavaTestFiles();
          break;
        case ALL_SOURCES:
        default:
          baseFiles = listJavaFiles();
          break;
      }

      // Apply filtering
      return baseFiles.stream()
          .filter(this::matchesIncludePatterns)
          .filter(this::matchesExcludePatterns)
          .collect(Collectors.toList());
    }

    /**
     * Finds a specific class by fully qualified class name within the filtered set.
     * Only searches within the files that match the current include/exclude criteria.
     *
     * @param fqcn the fully qualified class name to search for
     * @return an optional path to the class file, empty if not found in the filtered set
     */
    public Optional<Path> find(String fqcn) {
      if (fqcn == null || fqcn.trim().isEmpty()) {
        return Optional.empty();
      }

      // Search within the filtered set only
      List<Path> filteredFiles = list();

      return filteredFiles.stream()
          .filter(path -> matchesFQCN(path, fqcn))
          .findFirst();
    }

    /**
     * Finds a specific class by Nameable within the filtered set.
     * Only searches within the files that match the current include/exclude criteria.
     *
     * @param nameable the Nameable to search for
     * @return an optional path to the class file, empty if not found in the filtered set
     */
    public Optional<Path> find(Nameable nameable) {
      return find(nameable.getFullyQualifiedName());
    }

    /**
     * Checks if a file path corresponds to the given fully qualified class name.
     */
    private boolean matchesFQCN(Path filePath, String fqcn) {
      // Use the existing logic from Project.findJavaFile but adapted for filtering
      Optional<String> packageName = packageOf(fqcn);
      String className = classNameOf(fqcn);

      String fileName = filePath.getFileName().toString();

      // Check if filename matches the class name
      if (!fileName.equals(className + ".java")) {
        return false;
      }

      // If no package, just check filename
      if (!packageName.isPresent()) {
        return true;
      }

      // Check if the path contains the expected package structure
      String expectedPackagePath = packageName.get().replace(".", File.separator);
      String filePathString = filePath.toString();

      // The path should contain the package structure before the filename
      return filePathString.contains(expectedPackagePath + File.separator + fileName);
    }

    private boolean matchesIncludePatterns(Path path) {
      if (includePatterns.length == 0) {
        return true; // No include patterns means include all
      }
      String fileName = path.getFileName().toString();
      return Patterns.isIncluded(fileName, includePatterns);
    }

    private boolean matchesExcludePatterns(Path path) {
      if (excludePatterns.length == 0) {
        return true; // No exclude patterns means exclude none
      }
      String fileName = path.getFileName().toString();
      return !Patterns.isExcluded(fileName, excludePatterns);
    }
  }
}
