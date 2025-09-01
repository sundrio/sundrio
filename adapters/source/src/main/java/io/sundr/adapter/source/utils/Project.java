package io.sundr.adapter.source.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Project {

  public static final File DIR = new File(".");

  public static final String NEWLINE = "\n";

  public static final String GIT = System.getProperty(".git");

  public static final String USER_HOME = System.getProperty("user.home");
  public static final String CURRENT_DIR = System.getProperty("user.dir");

  public static final File MODULE_ROOT = moduleRoot();

  public static final File SRC = new File(MODULE_ROOT, "src");
  public static final File SRC_MAIN = new File(SRC, "main");
  public static final File SRC_MAIN_JAVA = new File(SRC_MAIN, "java");

  public static final File SRC_TEST = new File(SRC, "test");
  public static final File SRC_TEST_JAVA = new File(SRC_TEST, "java");

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
  public static Optional<Path> findJavaFile(File sourceRoot, String fqcn) {
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
  public static Optional<Path> findJavaSourceFile(String fqcn) {
    return findJavaFile(SRC_MAIN_JAVA, fqcn);
  }

  /**
   * Search the project and find a java test file matching the specified
   * fully qualified class name.
   * The function searches the project sources.
   *
   * @param fqcn the fully qualified class name
   * @return an optional path or empty if file not found.
   **/
  public static Optional<Path> findJavaTestFile(String fqcn) {
    return findJavaFile(SRC_TEST_JAVA, fqcn);
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
}
