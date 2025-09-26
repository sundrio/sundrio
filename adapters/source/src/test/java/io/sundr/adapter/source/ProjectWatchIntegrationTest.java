package io.sundr.adapter.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for Project watch functionality.
 * Tests the actual API and basic functionality without timing dependencies.
 */
public class ProjectWatchIntegrationTest {

  private Path tempDir;
  private Path srcMainJava;
  private Project project;

  @Before
  public void setUp() throws Exception {
    // Create temporary directory structure
    tempDir = Files.createTempDirectory("project-watch-integration-test");
    Path srcDir = tempDir.resolve("src");
    srcMainJava = srcDir.resolve("main").resolve("java");
    Files.createDirectories(srcMainJava);

    // Create a test project pointing to our temp directory
    project = new Project(tempDir.toFile());
  }

  @After
  public void tearDown() throws Exception {
    // Clean up temporary directory
    if (tempDir != null) {
      deleteRecursively(tempDir);
    }
  }

  @Test
  public void testWatchMethodExists() {
    // Test that the watch method with ChangeSet consumer exists and returns CompletableFuture
    assertNotNull("Project should have sources() method", project.sources());

    // Test that we can call watch without it throwing
    try {
      var watchFuture = project.sources().watch(changeSet -> {
        // Do nothing - just testing the API exists
      });
      assertNotNull("Watch should return CompletableFuture", watchFuture);
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
      assertNotNull("Watch with filtering should return CompletableFuture", watchFuture);
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

      assertNotNull("Sources watch should work", sourcesWatch);
      assertNotNull("Test sources watch should work", testSourcesWatch);
      assertNotNull("All sources watch should work", allSourcesWatch);

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

    assertFalse("Future should not be cancelled initially", watchFuture.isCancelled());
    assertFalse("Future should not be done initially", watchFuture.isDone());

    boolean cancelled = watchFuture.cancel(true);
    assertTrue("Future should be cancellable", cancelled);
    assertTrue("Future should be cancelled after cancel()", watchFuture.isCancelled());
    assertTrue("Future should be done after cancel()", watchFuture.isDone());
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

    assertNotNull("First watcher should be created", watcher1);
    assertNotNull("Second watcher should be created", watcher2);
    assertNotNull("Third watcher should be created", watcher3);

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
    assertTrue("Watch should not block caller, took " + duration + "ms", duration < 100);

    watchFuture.cancel(true);
  }

  @Test
  public void testProjectDirectoryStructure() {
    // Verify our test setup creates the expected structure
    assertTrue("Temp directory should exist", Files.exists(tempDir));
    assertTrue("src/main/java should exist", Files.exists(srcMainJava));
    assertTrue("src/main/java should be directory", Files.isDirectory(srcMainJava));

    // Verify project recognizes the structure
    List<Path> sources = project.sources().list();
    assertNotNull("Sources list should not be null", sources);
    // Empty initially, which is expected
    assertTrue("Sources should be empty initially", sources.isEmpty());
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
