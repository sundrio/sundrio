/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.adapter.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.Optional;

import org.junit.Test;

import io.sundr.model.Kind;
import io.sundr.model.TypeDef;

public class EnumSourceAdapterTest {

  @Test
  public void shouldAdaptSingularizeEnum() throws Exception {
    Project project = Project.getProject();
    Project coreProject = Project.getProject(project.getModuleRoot().toPath().getParent().getParent().resolve("core"));

    // Find the Singularize enum in the core module
    Optional<Path> singularizePath = coreProject.allSources().find("io.sundr.functions.Singularize");

    assertTrue("Should find Singularize.java", singularizePath.isPresent());

    // Parse the TypeDef from the file - should now work with enum support
    TypeDef typeDef = coreProject.parse(singularizePath.get());
    assertNotNull(typeDef);
    assertEquals("Singularize", typeDef.getName());
    assertEquals(Kind.ENUM, typeDef.getKind());
    assertEquals("io.sundr.functions", typeDef.getPackageName());

    // Verify the enum has the expected structure
    assertTrue("Should have FUNCTION constant",
        typeDef.getProperties().stream()
            .anyMatch(p -> "FUNCTION".equals(p.getName())));

    assertTrue("Should have apply method",
        typeDef.getMethods().stream()
            .anyMatch(m -> "apply".equals(m.getName())));
  }

  @Test
  public void shouldAdaptPluralizeEnum() throws Exception {
    Project project = Project.getProject();
    Project coreProject = Project.getProject(project.getModuleRoot().toPath().getParent().getParent().resolve("core"));

    // Find the Pluralize enum in the core module
    Optional<Path> pluralizePath = coreProject.allSources().find("io.sundr.functions.Pluralize");

    assertTrue("Should find Pluralize.java", pluralizePath.isPresent());

    // Parse the TypeDef from the file - should now work with enum support
    TypeDef typeDef = coreProject.parse(pluralizePath.get());
    assertNotNull(typeDef);
    assertEquals("Pluralize", typeDef.getName());
    assertEquals(Kind.ENUM, typeDef.getKind());
    assertEquals("io.sundr.functions", typeDef.getPackageName());

    // Verify the enum has the expected structure
    assertTrue("Should have FUNCTION constant",
        typeDef.getProperties().stream()
            .anyMatch(p -> "FUNCTION".equals(p.getName())));

    assertTrue("Should have apply method",
        typeDef.getMethods().stream()
            .anyMatch(m -> "apply".equals(m.getName())));

    assertTrue("Should have isAlreadyPlural method",
        typeDef.getMethods().stream()
            .anyMatch(m -> "isAlreadyPlural".equals(m.getName())));
  }
}
