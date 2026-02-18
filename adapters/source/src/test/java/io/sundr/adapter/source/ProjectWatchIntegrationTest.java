package io.sundr.adapter.source;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Integration tests for Project watch functionality.
 * Tests the actual API and basic functionality without timing dependencies.
 */
public class ProjectWatchIntegrationTest {

  private Path tempDir;
  private Path srcMainJava;
  private Project project;

  @BeforeEach
  public void setUp() throws Exception {
    // Create temporary directory structure
    tempDir = Files.createTempDirectory("project-watch-integration-test");
    Path srcDir = tempDir.resolve("src");
    srcMainJava = srcDir.resolve("main").resolve("java");
    Files.createDirectories(srcMainJava);

    // Create a test project pointing to our temp directory
    project = new Project(tempDir.toFile());
  }

  @AfterEach
  public void tearDown() throws Exception {
    // Clean up temporary directory
    if (tempDir != null) {
      deleteRecursively(tempDir);
    }
  }

  @Test
  public void testWatchMethodExists() {
    // Test that the watch method with ChangeSet consumer exists and returns CompletableFuture
    assertNotNull(project.sources(), "Project should have sources() method");

    // Test that we can call watch without it throwing
    try {
      var watchFuture = project.sources().watch(changeSet -> {
        // Do nothing - just testing the API exists
      });
      assertNotNull(watchFuture, "Watch should return CompletableFuture");
      watchFuture.cancel(true);
    } catch (Exception e) {
      fail("Watch method should exist and be callable: " + e.getMessage());
    }
  }

  @Test
  public void testWatchWithFiltering() {
    // Test that watch works with pattern filtering
    try {
      var watchFuture = project.sources()
          .including("*Service.java")
          .excluding("*Test.java")
          .watch(changeSet -> {
            // Do nothing - just testing the API exists
          });
      assertNotNull(watchFuture, "Watch with filtering should return CompletableFuture");
      watchFuture.cancel(true);
    } catch (Exception e) {
      fail("Watch method with filtering should work: " + e.getMessage());
    }
  }

  @Test
  public void testWatchOnDifferentSourceTypes() {
    // Test that watch works on different source selectors
    try {
      var sourcesWatch = project.sources().watch(changeSet -> {
      });
      var testSourcesWatch = project.testSources().watch(changeSet -> {
      });
      var allSourcesWatch = project.allSources().watch(changeSet -> {
      });

      assertNotNull(sourcesWatch, "Sources watch should work");
      assertNotNull(testSourcesWatch, "Test sources watch should work");
      assertNotNull(allSourcesWatch, "All sources watch should work");

      sourcesWatch.cancel(true);
      testSourcesWatch.cancel(true);
      allSourcesWatch.cancel(true);
    } catch (Exception e) {
      fail("Watch should work on all source types: " + e.getMessage());
    }
  }

  @Test
  public void testWatchFutureCanBeCancelled() {
    // Test that the returned CompletableFuture can be cancelled
    var watchFuture = project.sources().watch(changeSet -> {
    });

    assertFalse(watchFuture.isCancelled(), "Future should not be cancelled initially");
    assertFalse(watchFuture.isDone(), "Future should not be done initially");

    boolean cancelled = watchFuture.cancel(true);
    assertTrue(cancelled, "Future should be cancellable");
    assertTrue(watchFuture.isCancelled(), "Future should be cancelled after cancel()");
    assertTrue(watchFuture.isDone(), "Future should be done after cancel()");
  }

  @Test
  public void testMultipleWatchersCanBeCreated() {
    // Test that multiple watchers can be created simultaneously
    var watcher1 = project.sources().watch(changeSet -> {
    });
    var watcher2 = project.testSources().watch(changeSet -> {
    });
    var watcher3 = project.allSources().including("*.java").watch(changeSet -> {
    });

    assertNotNull(watcher1, "First watcher should be created");
    assertNotNull(watcher2, "Second watcher should be created");
    assertNotNull(watcher3, "Third watcher should be created");

    // Clean up
    watcher1.cancel(true);
    watcher2.cancel(true);
    watcher3.cancel(true);
  }

  @Test
  public void testWatchDoesNotBlockCaller() {
    // Test that calling watch() doesn't block the calling thread
    long startTime = System.currentTimeMillis();

    var watchFuture = project.sources().watch(changeSet -> {
    });

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    // Watch should return almost immediately (within 100ms is very generous)
    assertTrue(duration < 100, "Watch should not block caller, took " + duration + "ms");

    watchFuture.cancel(true);
  }

  @Test
  public void testProjectDirectoryStructure() {
    // Verify our test setup creates the expected structure
    assertTrue(Files.exists(tempDir), "Temp directory should exist");
    assertTrue(Files.exists(srcMainJava), "src/main/java should exist");
    assertTrue(Files.isDirectory(srcMainJava), "src/main/java should be directory");

    // Verify project recognizes the structure
    List<Path> sources = project.sources().list();
    assertNotNull(sources, "Sources list should not be null");
    // Empty initially, which is expected
    assertTrue(sources.isEmpty(), "Sources should be empty initially");
  }

  // Helper method to recursively delete directory
  private void deleteRecursively(Path path) throws IOException {
    if (Files.isDirectory(path)) {
      Files.list(path).forEach(child -> {
        try {
          deleteRecursively(child);
        } catch (IOException e) {
          // Ignore cleanup errors
        }
      });
    }
    Files.deleteIfExists(path);
  }
}
