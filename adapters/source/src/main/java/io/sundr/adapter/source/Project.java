package io.sundr.adapter.source;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.source.analysis.ImpactAnalysisResult;
import io.sundr.adapter.source.analysis.ImpactAnalyzer;
import io.sundr.adapter.source.change.ChangeDetector;
import io.sundr.adapter.source.change.ChangeSet;
import io.sundr.adapter.source.utils.Sources;
import io.sundr.model.Nameable;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;
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
   * Read a TypeDef from a Java source file.
   *
   * @param path The path to the Java source file
   * @return The TypeDef parsed from the file
   */
  TypeDef parse(Path path) {
    try {
      AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());
      try (java.io.FileInputStream fis = new java.io.FileInputStream(path.toFile())) {
        return Sources.readTypeDefFromStream(fis, context);
      }
    } catch (Exception e) {
      throw new RuntimeException("Failed to read TypeDef from " + path, e);
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
   * Performs impact analysis for the given ChangeSet.
   * Analyzes the impact of changes on the codebase to find all affected files, TypeDefs, and MethodReferences.
   *
   * @param changeSet the changes to analyze
   * @return the impact analysis result
   */
  public ImpactAnalysisResult analyzeImpact(ChangeSet changeSet) {
    ImpactAnalyzer analyzer = new ImpactAnalyzer(this);
    return analyzer.analyze(changeSet);
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

    /**
     * Starts watching for file changes and computes ChangeSet differences.
     * When files change, compares the new version with the previous version and
     * notifies the consumer with the detected changes.
     * Handles editor patterns like vi that delete/create files atomically.
     *
     * @param changeConsumer consumer that receives ChangeSet for each file change
     * @return a CompletableFuture that can be used to control the watching
     */
    public CompletableFuture<Void> watch(Consumer<ChangeSet> changeConsumer) {
      return CompletableFuture.runAsync(() -> {
        // Keep track of previous file states for comparison
        Map<Path, TypeDef> previousStates = new ConcurrentHashMap<>();

        // Buffer for pending events to handle delete/create patterns
        Map<Path, PendingEvent> pendingEvents = new ConcurrentHashMap<>();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Initialize previous states
        for (Path path : list()) {
          try {
            TypeDef typeDef = parse(path);
            previousStates.put(path, typeDef);
          } catch (Exception e) {
            // Skip files that can't be read initially
          }
        }

        try (WatchService watchService = moduleRoot.toPath().getFileSystem().newWatchService()) {
          registerWatchDirectories(watchService);

          while (!Thread.currentThread().isInterrupted()) {
            WatchKey key;
            try {
              key = watchService.take();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              break;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
              WatchEvent.Kind<?> kind = event.kind();

              if (kind == StandardWatchEventKinds.OVERFLOW) {
                continue;
              }

              @SuppressWarnings("unchecked")
              WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
              Path changedPath = ((Path) key.watchable()).resolve(pathEvent.context());

              // Only process if the changed file matches our selection criteria
              if (changedPath.toString().endsWith(".java") &&
                  matchesIncludePatterns(changedPath) &&
                  matchesExcludePatterns(changedPath)) {

                handleFileEventWithBuffering(changedPath, kind, previousStates,
                    pendingEvents, scheduler, changeConsumer);
              }
            }

            boolean valid = key.reset();
            if (!valid) {
              break;
            }
          }
        } catch (Exception e) {
          throw new RuntimeException("Error watching files", e);
        } finally {
          scheduler.shutdown();
        }
      });
    }

    private void handleFileEventWithBuffering(Path changedPath, WatchEvent.Kind<?> kind,
        Map<Path, TypeDef> previousStates,
        Map<Path, PendingEvent> pendingEvents,
        ScheduledExecutorService scheduler,
        Consumer<ChangeSet> changeConsumer) {

      PendingEvent existingEvent = pendingEvents.get(changedPath);

      if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
        if (existingEvent != null) {
          // Cancel any existing scheduled event
          existingEvent.cancel();
        }

        // Schedule the delete event with a small delay to check for subsequent CREATE
        PendingEvent deleteEvent = new PendingEvent(changedPath, kind, previousStates.get(changedPath));
        pendingEvents.put(changedPath, deleteEvent);

        deleteEvent.scheduledFuture = scheduler.schedule(() -> {
          // Process the delete after delay (no CREATE event came)
          pendingEvents.remove(changedPath);
          processDeleteEvent(changedPath, previousStates, changeConsumer);
        }, 100, TimeUnit.MILLISECONDS); // 100ms delay

      } else if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
        if (existingEvent != null && existingEvent.kind == StandardWatchEventKinds.ENTRY_DELETE) {
          // This is a DELETE followed by CREATE - treat as MODIFY
          existingEvent.cancel();
          pendingEvents.remove(changedPath);

          // Process as modify event
          processModifyEvent(changedPath, previousStates, changeConsumer);
        } else {
          // This is a genuine CREATE event
          processCreateEvent(changedPath, previousStates, changeConsumer);
        }

      } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
        if (existingEvent != null) {
          // Cancel any existing event
          existingEvent.cancel();
          pendingEvents.remove(changedPath);
        }

        // Process modify immediately
        processModifyEvent(changedPath, previousStates, changeConsumer);
      }
    }

    private void processDeleteEvent(Path changedPath, Map<Path, TypeDef> previousStates,
        Consumer<ChangeSet> changeConsumer) {
      TypeDef previousTypeDef = previousStates.get(changedPath);
      if (previousTypeDef != null) {
        try {
          ChangeSet changeSet = ChangeDetector.compare(previousTypeDef, (TypeDef) null);
          changeConsumer.accept(changeSet);
        } catch (Exception e) {
          System.err.println("Error processing delete for " + changedPath + ": " + e.getMessage());
        }
        previousStates.remove(changedPath);
      }
    }

    private void processCreateEvent(Path changedPath, Map<Path, TypeDef> previousStates,
        Consumer<ChangeSet> changeConsumer) {
      try {
        TypeDef newTypeDef = parse(changedPath);
        ChangeSet changeSet = ChangeDetector.compare((TypeDef) null, newTypeDef);
        changeConsumer.accept(changeSet);
        previousStates.put(changedPath, newTypeDef);
      } catch (Exception e) {
        System.err.println("Error processing create for " + changedPath + ": " + e.getMessage());
      }
    }

    private void processModifyEvent(Path changedPath, Map<Path, TypeDef> previousStates,
        Consumer<ChangeSet> changeConsumer) {
      try {
        TypeDef previousTypeDef = previousStates.get(changedPath);
        TypeDef newTypeDef = parse(changedPath);

        ChangeSet changeSet;
        if (previousTypeDef != null) {
          changeSet = ChangeDetector.compare(previousTypeDef, newTypeDef);
        } else {
          // File didn't exist before, treat as create
          changeSet = ChangeDetector.compare((TypeDef) null, newTypeDef);
        }

        if (changeSet.hasChanges()) {
          changeConsumer.accept(changeSet);
        }

        previousStates.put(changedPath, newTypeDef);
      } catch (Exception e) {
        System.err.println("Error processing modify for " + changedPath + ": " + e.getMessage());
      }
    }

    private void registerWatchDirectories(WatchService watchService) throws Exception {
      // Determine which directories to watch based on source type
      switch (sourceType) {
        case SOURCES:
          if (srcMainJava.exists()) {
            registerDirectoryTree(srcMainJava.toPath(), watchService);
          }
          break;
        case TEST_SOURCES:
          if (srcTestJava.exists()) {
            registerDirectoryTree(srcTestJava.toPath(), watchService);
          }
          break;
        case ALL_SOURCES:
        default:
          if (srcMainJava.exists()) {
            registerDirectoryTree(srcMainJava.toPath(), watchService);
          }
          if (srcTestJava.exists()) {
            registerDirectoryTree(srcTestJava.toPath(), watchService);
          }
          break;
      }
    }

    private void registerDirectoryTree(Path root, WatchService watchService) throws Exception {
      Files.walk(root)
          .filter(Files::isDirectory)
          .forEach(dir -> {
            try {
              dir.register(watchService,
                  StandardWatchEventKinds.ENTRY_CREATE,
                  StandardWatchEventKinds.ENTRY_DELETE,
                  StandardWatchEventKinds.ENTRY_MODIFY);
            } catch (Exception e) {
              throw new RuntimeException("Failed to register directory: " + dir, e);
            }
          });
    }
  }

  /**
   * Represents a pending file system event that may be part of an atomic operation.
   */
  private static class PendingEvent {
    final Path path;
    final WatchEvent.Kind<?> kind;
    final TypeDef previousState;
    volatile java.util.concurrent.ScheduledFuture<?> scheduledFuture;

    PendingEvent(Path path, WatchEvent.Kind<?> kind, TypeDef previousState) {
      this.path = path;
      this.kind = kind;
      this.previousState = previousState;
    }

    void cancel() {
      if (scheduledFuture != null) {
        scheduledFuture.cancel(false);
      }
    }
  }
}
