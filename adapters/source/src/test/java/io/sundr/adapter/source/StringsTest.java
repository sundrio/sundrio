/**
 * Test to verify that Strings.java can be processed without the null condition error.
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

public class StringsTest {

  @Test
  public void shouldProcessStringsJavaWithoutError() throws Exception {
    Project project = Project.getProject();
    Project coreProject = Project.getProject(project.getModuleRoot().toPath().getParent().getParent().resolve("core"));

    // Find the strings.java file in the core module
    Optional<Path> stringsPath = coreProject.allSources().find("io.sundr.utils.Strings");

    assertTrue("Should find Strings.java", stringsPath.isPresent());

    // Parse the TypeDef from the file - should now work without null condition error
    TypeDef typeDef = coreProject.parse(stringsPath.get());
    assertNotNull("TypeDef should be created", typeDef);
    assertEquals("Strings", typeDef.getName());
    assertEquals(Kind.CLASS, typeDef.getKind());
    assertEquals("io.sundr.utils", typeDef.getPackageName());

    //Now render
    assertNotNull(typeDef.render());

  }
}
