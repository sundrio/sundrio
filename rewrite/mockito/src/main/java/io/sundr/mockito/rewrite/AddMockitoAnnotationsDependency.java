/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.mockito.rewrite;

import org.openrewrite.ExecutionContext;
import org.openrewrite.ScanningRecipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.maven.AddDependencyVisitor;
import org.openrewrite.maven.MavenIsoVisitor;
import org.openrewrite.xml.tree.Xml;

/**
 * Adds the {@code io.sundr:mockito-annotations} test-scope dependency, pinned to the recipe's own
 * release, to exactly the modules that get a {@code @Mockables} marker. It shares the
 * {@link MarkerPackages} scan with {@link GenerateMarker}, so the dependency and the generated
 * {@code MocksConfig} never drift apart: a module the marker generator writes into is a module the
 * dependency is added to, even when the mock is discovered through a {@code @MockBean} field or a
 * statically imported {@code when(...)} that a plain {@code onlyIfUsing} type match would miss.
 *
 * <p>
 * The version is derived from {@link AddMockitoAnnotationProcessorPath#resolveVersion()}, the single
 * source of truth shared with the annotation-processor path so both resolve to the same release.
 */
public class AddMockitoAnnotationsDependency extends ScanningRecipe<MarkerPackages.Scan> {

  @Override
  public String getDisplayName() {
    return "Add the mockito-annotations dependency to modules that mock";
  }

  @Override
  public String getDescription() {
    return "Adds io.sundr:mockito-annotations as a test-scope dependency, pinned to the recipe "
        + "version, to every module that has mocked types (the same modules that get a @Mockables "
        + "marker). Keeps the dependency and the generated marker in lockstep.";
  }

  @Override
  public MarkerPackages.Scan getInitialValue(ExecutionContext ctx) {
    return new MarkerPackages.Scan();
  }

  @Override
  public TreeVisitor<?, ExecutionContext> getScanner(MarkerPackages.Scan scan) {
    return MarkerPackages.scanner(scan);
  }

  @Override
  public TreeVisitor<?, ExecutionContext> getVisitor(MarkerPackages.Scan scan) {
    String version = AddMockitoAnnotationProcessorPath.resolveVersion();
    return new MavenIsoVisitor<ExecutionContext>() {
      @Override
      public Xml.Document visitDocument(Xml.Document document, ExecutionContext ctx) {
        String baseDir = pomBaseDir(document.getSourcePath());
        MarkerPackages.Module module = scan.modules.get(baseDir);
        if (module == null || module.mockTypes.isEmpty()) {
          return document;
        }
        AddDependencyVisitor add = new AddDependencyVisitor(
            AddMockitoAnnotationProcessorPath.PROCESSOR_GROUP_ID,
            AddMockitoAnnotationProcessorPath.PROCESSOR_ARTIFACT_ID,
            version, null, "test", null, null, null, null, null);
        return (Xml.Document) add.visitNonNull(document, ctx);
      }
    };
  }

  /**
   * The module base directory of a {@code pom.xml} path, matching the keys used by the shared scan:
   * everything preceding the trailing {@code pom.xml} (empty for the reactor-root pom).
   *
   * @param pomPath a reactor-root-relative pom path.
   * @return the module base dir with a trailing slash, or an empty string for the root pom.
   */
  static String pomBaseDir(java.nio.file.Path pomPath) {
    String normalized = pomPath.toString().replace('\\', '/');
    int idx = normalized.lastIndexOf("pom.xml");
    return idx <= 0 ? "" : normalized.substring(0, idx);
  }
}
