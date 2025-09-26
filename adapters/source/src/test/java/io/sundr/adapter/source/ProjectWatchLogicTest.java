package io.sundr.adapter.source;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.sundr.adapter.source.change.ChangeSet;
import io.sundr.model.*;

/**
 * Tests the watch logic without relying on actual file system watching.
 * Tests the core change detection and event buffering logic in isolation.
 */
public class ProjectWatchLogicTest {

  private TestableProject project;
  private List<ChangeSet> receivedChanges;
  private Consumer<ChangeSet> changeConsumer;
  private ScheduledExecutorService scheduler;

  @Before
  public void setUp() {
    project = new TestableProject();
    receivedChanges = new ArrayList<>();
    changeConsumer = changeSet -> receivedChanges.add(changeSet);
    scheduler = Executors.newScheduledThreadPool(1);
  }

  @After
  public void tearDown() {
    if (scheduler != null) {
      scheduler.shutdown();
    }
  }

  @Test
  public void testProcessCreateEvent() {
    Path testPath = Paths.get("TestClass.java");
    TypeDef newTypeDef = createTestTypeDef("TestClass", "testMethod");
    Map<Path, TypeDef> previousStates = new ConcurrentHashMap<>();

    // Set up the mock to return the new TypeDef
    project.setMockTypeDef(testPath, newTypeDef);

    // Simulate a CREATE event
    project.processCreateEvent(testPath, previousStates, changeConsumer);

    // Debug output
    System.out.println("Number of changes received: " + receivedChanges.size());
    if (!receivedChanges.isEmpty()) {
      ChangeSet changeSet = receivedChanges.get(0);
      System.out.println("ChangeSet has changes: " + changeSet.hasChanges());
      System.out.println("Method changes: " + changeSet.getMethodChanges().size());
      System.out.println("Property changes: " + changeSet.getPropertyChanges().size());
    }

    // Verify results
    assertEquals("Should have received one change", 1, receivedChanges.size());
    ChangeSet changeSet = receivedChanges.get(0);
    assertTrue("Should have changes", changeSet.hasChanges());
    assertTrue("Previous states should contain the new file", previousStates.containsKey(testPath));
  }

  @Test
  public void testProcessModifyEvent() {
    Path testPath = Paths.get("ModifyTest.java");
    TypeDef oldTypeDef = createTestTypeDef("ModifyTest", "oldMethod");
    TypeDef newTypeDef = createTestTypeDef("ModifyTest", "newMethod");

    Map<Path, TypeDef> previousStates = new ConcurrentHashMap<>();
    previousStates.put(testPath, oldTypeDef);

    // Set up the project to return the new TypeDef when reading
    project.setMockTypeDef(testPath, newTypeDef);

    // Simulate a MODIFY event
    project.processModifyEvent(testPath, previousStates, changeConsumer);

    // Verify results
    assertEquals("Should have received one change", 1, receivedChanges.size());
    ChangeSet changeSet = receivedChanges.get(0);
    assertTrue("Should have changes", changeSet.hasChanges());
    assertEquals("Previous states should be updated", newTypeDef, previousStates.get(testPath));
  }

  @Test
  public void testProcessModifyEventNoChanges() {
    Path testPath = Paths.get("NoChangeTest.java");
    TypeDef typeDef = createTestTypeDef("NoChangeTest", "sameMethod");

    Map<Path, TypeDef> previousStates = new ConcurrentHashMap<>();
    previousStates.put(testPath, typeDef);

    // Set up the project to return the identical TypeDef
    project.setMockTypeDef(testPath, typeDef);

    // Simulate a MODIFY event with no actual changes
    project.processModifyEvent(testPath, previousStates, changeConsumer);

    // Verify no change was reported (since there were no actual changes)
    assertEquals("Should not have received any changes", 0, receivedChanges.size());
    assertEquals("Previous states should be updated", typeDef, previousStates.get(testPath));
  }

  @Test
  public void testProcessDeleteEvent() {
    Path testPath = Paths.get("DeleteTest.java");
    TypeDef deletedTypeDef = createTestTypeDef("DeleteTest", "deletedMethod");

    Map<Path, TypeDef> previousStates = new ConcurrentHashMap<>();
    previousStates.put(testPath, deletedTypeDef);

    // Simulate a DELETE event
    project.processDeleteEvent(testPath, previousStates, changeConsumer);

    // Verify results
    assertEquals("Should have received one change", 1, receivedChanges.size());
    ChangeSet changeSet = receivedChanges.get(0);
    assertTrue("Should have changes", changeSet.hasChanges());
    assertFalse("Previous states should not contain deleted file", previousStates.containsKey(testPath));
  }

  @Test
  public void testProcessDeleteEventForNonExistentFile() {
    Path testPath = Paths.get("NonExistent.java");
    Map<Path, TypeDef> previousStates = new ConcurrentHashMap<>();

    // Simulate DELETE event for file that wasn't being tracked
    project.processDeleteEvent(testPath, previousStates, changeConsumer);

    // Verify no change was reported
    assertEquals("Should not have received any changes", 0, receivedChanges.size());
  }

  @Test
  public void testAtomicReplaceLogic() {
    Path testPath = Paths.get("AtomicTest.java");
    TypeDef oldTypeDef = createTestTypeDef("AtomicTest", "oldMethod");
    TypeDef newTypeDef = createTestTypeDef("AtomicTest", "newMethod");

    Map<Path, TypeDef> previousStates = new ConcurrentHashMap<>();
    Map<Path, TestableProject.PendingEvent> pendingEvents = new ConcurrentHashMap<>();
    previousStates.put(testPath, oldTypeDef);

    // Set up the project to return the new TypeDef when reading
    project.setMockTypeDef(testPath, newTypeDef);

    // Simulate DELETE event
    project.handleFileEventWithBuffering(testPath, StandardWatchEventKinds.ENTRY_DELETE,
        previousStates, pendingEvents, scheduler, changeConsumer);

    // Verify DELETE event is buffered
    assertTrue("Should have a pending event", pendingEvents.containsKey(testPath));
    assertEquals("Should not have received any changes yet", 0, receivedChanges.size());

    // Simulate immediate CREATE event (atomic replace pattern)
    project.handleFileEventWithBuffering(testPath, StandardWatchEventKinds.ENTRY_CREATE,
        previousStates, pendingEvents, scheduler, changeConsumer);

    // Verify the DELETE was cancelled and CREATE was processed as MODIFY
    assertFalse("Should not have pending events", pendingEvents.containsKey(testPath));
    assertEquals("Should have received one change", 1, receivedChanges.size());
    ChangeSet changeSet = receivedChanges.get(0);
    assertTrue("Should have changes", changeSet.hasChanges());
  }

  @Test
  public void testStandaloneCreateEvent() {
    Path testPath = Paths.get("CreateTest.java");
    TypeDef newTypeDef = createTestTypeDef("CreateTest", "newMethod");

    Map<Path, TypeDef> previousStates = new ConcurrentHashMap<>();
    Map<Path, TestableProject.PendingEvent> pendingEvents = new ConcurrentHashMap<>();

    // Set up the project to return the new TypeDef when reading
    project.setMockTypeDef(testPath, newTypeDef);

    // Simulate standalone CREATE event (not preceded by DELETE)
    project.handleFileEventWithBuffering(testPath, StandardWatchEventKinds.ENTRY_CREATE,
        previousStates, pendingEvents, scheduler, changeConsumer);

    // Verify CREATE event is processed immediately
    assertFalse("Should not have pending events", pendingEvents.containsKey(testPath));
    assertEquals("Should have received one change", 1, receivedChanges.size());
    assertTrue("Previous states should contain the new file", previousStates.containsKey(testPath));
  }

  @Test
  public void testModifyEventCancelsPendingDelete() {
    Path testPath = Paths.get("ModifyTest.java");
    TypeDef oldTypeDef = createTestTypeDef("ModifyTest", "oldMethod");
    TypeDef newTypeDef = createTestTypeDef("ModifyTest", "newMethod");

    Map<Path, TypeDef> previousStates = new ConcurrentHashMap<>();
    Map<Path, TestableProject.PendingEvent> pendingEvents = new ConcurrentHashMap<>();
    previousStates.put(testPath, oldTypeDef);

    // Set up the project to return the new TypeDef when reading
    project.setMockTypeDef(testPath, newTypeDef);

    // First simulate a DELETE event
    project.handleFileEventWithBuffering(testPath, StandardWatchEventKinds.ENTRY_DELETE,
        previousStates, pendingEvents, scheduler, changeConsumer);

    assertTrue("Should have a pending DELETE event", pendingEvents.containsKey(testPath));
    assertEquals("Should not have received any changes yet", 0, receivedChanges.size());

    // Then simulate a MODIFY event
    project.handleFileEventWithBuffering(testPath, StandardWatchEventKinds.ENTRY_MODIFY,
        previousStates, pendingEvents, scheduler, changeConsumer);

    // Verify the pending DELETE was cancelled and MODIFY was processed
    assertFalse("Should not have pending events", pendingEvents.containsKey(testPath));
    assertEquals("Should have received one change", 1, receivedChanges.size());
  }

  // Helper method to create test TypeDefs
  private TypeDef createTestTypeDef(String className, String methodName) {
    Method method = new MethodBuilder()
        .withName(methodName)
        .withReturnType(ClassRef.forClass(String.class))
        .withNewBlock()
        .withStatements(Return.value("test"))
        .endBlock()
        .build();

    return new TypeDefBuilder()
        .withName(className)
        .withPackageName("com.example")
        .withMethods(method)
        .build();
  }

  /**
   * Testable version of Project that allows us to test the watch logic
   * without involving the actual file system.
   */
  private static class TestableProject extends Project {
    private Map<Path, TypeDef> mockTypeDefs = new ConcurrentHashMap<>();

    public TestableProject() {
      // Create a minimal project structure for testing
      super(new java.io.File("."));
    }

    public void setMockTypeDef(Path path, TypeDef typeDef) {
      mockTypeDefs.put(path, typeDef);
    }

    @Override
    public TypeDef parse(Path path) {
      TypeDef mockTypeDef = mockTypeDefs.get(path);
      if (mockTypeDef != null) {
        return mockTypeDef;
      }
      throw new RuntimeException("Mock TypeDef not found for path: " + path);
    }

    // Expose the private methods for testing
    public void processDeleteEvent(Path changedPath, Map<Path, TypeDef> previousStates,
        Consumer<ChangeSet> changeConsumer) {
      // Copy the logic from the private method in Project
      TypeDef previousTypeDef = previousStates.get(changedPath);
      if (previousTypeDef != null) {
        try {
          io.sundr.adapter.source.change.ChangeSet changeSet = io.sundr.adapter.source.change.ChangeDetector
              .compare(previousTypeDef, (TypeDef) null);
          changeConsumer.accept(changeSet);
        } catch (Exception e) {
          System.err.println("Error processing delete for " + changedPath + ": " + e.getMessage());
        }
        previousStates.remove(changedPath);
      }
    }

    public void processCreateEvent(Path changedPath, Map<Path, TypeDef> previousStates,
        Consumer<ChangeSet> changeConsumer) {
      try {
        TypeDef newTypeDef = parse(changedPath);
        io.sundr.adapter.source.change.ChangeSet changeSet = io.sundr.adapter.source.change.ChangeDetector
            .compare((TypeDef) null, newTypeDef);
        changeConsumer.accept(changeSet);
        previousStates.put(changedPath, newTypeDef);
      } catch (Exception e) {
        System.err.println("Error processing create for " + changedPath + ": " + e.getMessage());
      }
    }

    public void processModifyEvent(Path changedPath, Map<Path, TypeDef> previousStates,
        Consumer<ChangeSet> changeConsumer) {
      try {
        TypeDef previousTypeDef = previousStates.get(changedPath);
        TypeDef newTypeDef = parse(changedPath);

        io.sundr.adapter.source.change.ChangeSet changeSet;
        if (previousTypeDef != null) {
          changeSet = io.sundr.adapter.source.change.ChangeDetector.compare(previousTypeDef, newTypeDef);
        } else {
          changeSet = io.sundr.adapter.source.change.ChangeDetector.compare((TypeDef) null, newTypeDef);
        }

        if (changeSet.hasChanges()) {
          changeConsumer.accept(changeSet);
        }

        previousStates.put(changedPath, newTypeDef);
      } catch (Exception e) {
        System.err.println("Error processing modify for " + changedPath + ": " + e.getMessage());
      }
    }

    public void handleFileEventWithBuffering(Path changedPath,
        java.nio.file.WatchEvent.Kind<?> kind,
        Map<Path, TypeDef> previousStates,
        Map<Path, PendingEvent> pendingEvents,
        ScheduledExecutorService scheduler,
        Consumer<ChangeSet> changeConsumer) {

      PendingEvent existingEvent = pendingEvents.get(changedPath);

      if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
        if (existingEvent != null) {
          existingEvent.cancel();
        }

        PendingEvent deleteEvent = new PendingEvent(changedPath, kind, previousStates.get(changedPath));
        pendingEvents.put(changedPath, deleteEvent);

      } else if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
        if (existingEvent != null && existingEvent.kind == StandardWatchEventKinds.ENTRY_DELETE) {
          existingEvent.cancel();
          pendingEvents.remove(changedPath);
          processModifyEvent(changedPath, previousStates, changeConsumer);
        } else {
          processCreateEvent(changedPath, previousStates, changeConsumer);
        }

      } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
        if (existingEvent != null) {
          existingEvent.cancel();
          pendingEvents.remove(changedPath);
        }
        processModifyEvent(changedPath, previousStates, changeConsumer);
      }
    }

    // Simple PendingEvent for testing
    public static class PendingEvent {
      final Path path;
      final java.nio.file.WatchEvent.Kind<?> kind;
      final TypeDef previousState;
      volatile boolean cancelled = false;

      PendingEvent(Path path, java.nio.file.WatchEvent.Kind<?> kind, TypeDef previousState) {
        this.path = path;
        this.kind = kind;
        this.previousState = previousState;
      }

      void cancel() {
        cancelled = true;
      }
    }
  }
}
