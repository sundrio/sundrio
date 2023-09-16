package io.sundr.adapter.source.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProjectTest {

  @Test
  public void shouldFindModuleRoot() {
    assertTrue(Project.MODULE_ROOT.exists());
  }

  @Test
  public void shouldFindJavaRoot() {
    assertTrue(Project.SRC_MAIN_JAVA.exists());
    assertTrue(Project.SRC_TEST_JAVA.exists());
  }

  @Test
  public void shoudFindClassByFQCN() {
    assertTrue(Project.findJavaSourceFile("io.sundr.adapter.source.utils.Project").isPresent());
  }

  @Test
  public void shoudFindClassByName() {
    assertTrue(Project.findJavaSourceFile("Project").isPresent());
  }
}
