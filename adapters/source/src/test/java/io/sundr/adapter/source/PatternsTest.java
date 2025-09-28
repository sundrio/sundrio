/**
 * Test to verify that Patterns.java can be processed without the null condition error.
 */

package io.sundr.adapter.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.Optional;

import org.junit.Test;

import io.sundr.model.Kind;
import io.sundr.model.TypeDef;

public class PatternsTest {

  @Test
  public void shouldProcessPatternsJavaWithoutError() throws Exception {
    Project project = Project.getProject();
    Project coreProject = Project.getProject(project.getModuleRoot().toPath().getParent().getParent().resolve("core"));

    // Find the Patterns.java file in the core module
    Optional<Path> patternsPath = coreProject.allSources().find("io.sundr.utils.Patterns");

    assertTrue("Should find Patterns.java", patternsPath.isPresent());

    // Parse the TypeDef from the file - should now work without null condition error
    TypeDef typeDef = coreProject.parse(patternsPath.get());
    assertNotNull("TypeDef should be created", typeDef);
    assertEquals("Patterns", typeDef.getName());
    assertEquals(Kind.CLASS, typeDef.getKind());
    assertEquals("io.sundr.utils", typeDef.getPackageName());

    //Now render
    assertNotNull(typeDef.render());

  }
}
