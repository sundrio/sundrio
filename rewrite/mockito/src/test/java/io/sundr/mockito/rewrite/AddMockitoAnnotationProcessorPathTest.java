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

import static org.assertj.core.api.Assertions.assertThat;
import static org.openrewrite.maven.Assertions.pomXml;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

class AddMockitoAnnotationProcessorPathTest implements RewriteTest {

  // The recipe only wires modules that already depend on mockito-annotations (added first by
  // AddMockitoAnnotationsDependency, which gates on Mockito usage). Fixtures that expect wiring must
  // therefore declare the dependency.
  private static final String MOCKITO_ANNOTATIONS_DEPENDENCY = "  <dependencies>\n" +
      "    <dependency>\n" +
      "      <groupId>io.sundr</groupId>\n" +
      "      <artifactId>mockito-annotations</artifactId>\n" +
      "      <version>999-SNAPSHOT</version>\n" +
      "      <scope>test</scope>\n" +
      "    </dependency>\n" +
      "  </dependencies>\n";

  @Override
  public void defaults(RecipeSpec spec) {
    spec.recipe(new AddMockitoAnnotationProcessorPath());
  }

  @Test
  void versionResolvesToSnapshotFallbackInTestClasspath() {
    assertThat(AddMockitoAnnotationProcessorPath.resolveVersion())
        .isEqualTo(AddMockitoAnnotationProcessorPath.FALLBACK_VERSION);
  }

  // A compiler plugin is present but declares no local annotationProcessorPaths. Since paths may be
  // inherited (and detecting inheritance through the resolved model is unreliable), the recipe adds
  // an annotationProcessorPaths block WITH combine.children="append": it appends to any inherited
  // list and is harmless when there is nothing to append to.
  @Test
  void addsCombineAppendBlockWhenCompilerPluginHasNoLocalPaths() {
    rewriteRun(
        pomXml(
            "<project>\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <groupId>com.example</groupId>\n" +
                "  <artifactId>proj</artifactId>\n" +
                "  <version>1.0</version>\n" +
                MOCKITO_ANNOTATIONS_DEPENDENCY +
                "  <build>\n" +
                "    <plugins>\n" +
                "      <plugin>\n" +
                "        <groupId>org.apache.maven.plugins</groupId>\n" +
                "        <artifactId>maven-compiler-plugin</artifactId>\n" +
                "        <configuration>\n" +
                "          <source>17</source>\n" +
                "          <target>17</target>\n" +
                "        </configuration>\n" +
                "      </plugin>\n" +
                "    </plugins>\n" +
                "  </build>\n" +
                "</project>\n",
            spec -> spec.after(pom -> {
              assertThat(pom).contains("<annotationProcessorPaths combine.children=\"append\">");
              assertThat(pom).contains("<artifactId>mockito-annotations</artifactId>");
              return pom;
            })));
  }

  // No compiler plugin infrastructure at all -> genuinely left untouched. The test-scope dependency
  // plus classpath processor discovery covers the annotation processor.
  @Test
  void leavesPomUntouchedWhenNoCompilerPlugin() {
    rewriteRun(
        pomXml(
            "<project>\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <groupId>com.example</groupId>\n" +
                "  <artifactId>proj</artifactId>\n" +
                "  <version>1.0</version>\n" +
                "</project>\n"));
  }

  // Case 2: annotationProcessorPaths declared in the SAME pom -> merge in place, no combine attr.
  @Test
  void mergesIntoExistingSamePomProcessorPathsWithoutCombineAttribute() {
    rewriteRun(
        pomXml(
            "<project>\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <groupId>com.example</groupId>\n" +
                "  <artifactId>proj</artifactId>\n" +
                "  <version>1.0</version>\n" +
                MOCKITO_ANNOTATIONS_DEPENDENCY +
                "  <build>\n" +
                "    <plugins>\n" +
                "      <plugin>\n" +
                "        <groupId>org.apache.maven.plugins</groupId>\n" +
                "        <artifactId>maven-compiler-plugin</artifactId>\n" +
                "        <configuration>\n" +
                "          <annotationProcessorPaths>\n" +
                "            <path>\n" +
                "              <groupId>org.projectlombok</groupId>\n" +
                "              <artifactId>lombok</artifactId>\n" +
                "              <version>1.18.30</version>\n" +
                "            </path>\n" +
                "          </annotationProcessorPaths>\n" +
                "        </configuration>\n" +
                "      </plugin>\n" +
                "    </plugins>\n" +
                "  </build>\n" +
                "</project>\n",
            spec -> spec.after(pom -> {
              assertThat(pom).contains("<artifactId>lombok</artifactId>");
              assertThat(pom).contains("<artifactId>mockito-annotations</artifactId>");
              assertThat(pom).contains("<version>999-SNAPSHOT</version>");
              assertThat(pom).doesNotContain("combine.children");
              return pom;
            })));
  }

  // Case 3: annotationProcessorPaths inherited from parent pluginManagement, local pom has none.
  // The recipe must CREATE the block WITH combine.children="append" so Maven appends our path
  // to the inherited Lombok path instead of replacing it.
  @Test
  void createsCombineAppendBlockWhenInheritedFromParent() {
    rewriteRun(
        pomXml(
            "<project>\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <groupId>com.example</groupId>\n" +
                "  <artifactId>parent</artifactId>\n" +
                "  <version>1.0</version>\n" +
                "  <packaging>pom</packaging>\n" +
                "  <modules><module>child</module></modules>\n" +
                "  <build>\n" +
                "    <pluginManagement>\n" +
                "      <plugins>\n" +
                "        <plugin>\n" +
                "          <groupId>org.apache.maven.plugins</groupId>\n" +
                "          <artifactId>maven-compiler-plugin</artifactId>\n" +
                "          <configuration>\n" +
                "            <annotationProcessorPaths>\n" +
                "              <path>\n" +
                "                <groupId>org.projectlombok</groupId>\n" +
                "                <artifactId>lombok</artifactId>\n" +
                "                <version>1.18.30</version>\n" +
                "              </path>\n" +
                "            </annotationProcessorPaths>\n" +
                "          </configuration>\n" +
                "        </plugin>\n" +
                "      </plugins>\n" +
                "    </pluginManagement>\n" +
                "  </build>\n" +
                "</project>\n"),
        pomXml(
            "<project>\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <parent>\n" +
                "    <groupId>com.example</groupId>\n" +
                "    <artifactId>parent</artifactId>\n" +
                "    <version>1.0</version>\n" +
                "  </parent>\n" +
                "  <artifactId>child</artifactId>\n" +
                MOCKITO_ANNOTATIONS_DEPENDENCY +
                "  <build>\n" +
                "    <plugins>\n" +
                "      <plugin>\n" +
                "        <groupId>org.apache.maven.plugins</groupId>\n" +
                "        <artifactId>maven-compiler-plugin</artifactId>\n" +
                "      </plugin>\n" +
                "    </plugins>\n" +
                "  </build>\n" +
                "</project>\n",
            spec -> spec.path("child/pom.xml").after(pom -> {
              assertThat(pom).contains("<annotationProcessorPaths combine.children=\"append\">");
              assertThat(pom).contains("<artifactId>mockito-annotations</artifactId>");
              assertThat(pom).contains("<version>999-SNAPSHOT</version>");
              return pom;
            })));
  }

  // Case 3 variant: child compiler plugin already has a <configuration> (source/target) but no
  // annotationProcessorPaths, and the paths are inherited from parent pluginManagement. The recipe
  // must ADD an annotationProcessorPaths block WITH combine.children="append" into that existing
  // configuration -- this is the branch that mirrors a real multi-module project layout.
  @Test
  void createsCombineAppendBlockWhenChildHasConfigurationButInheritsPaths() {
    rewriteRun(
        pomXml(
            "<project>\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <groupId>com.example</groupId>\n" +
                "  <artifactId>parent</artifactId>\n" +
                "  <version>1.0</version>\n" +
                "  <packaging>pom</packaging>\n" +
                "  <modules><module>child</module></modules>\n" +
                "  <build>\n" +
                "    <pluginManagement>\n" +
                "      <plugins>\n" +
                "        <plugin>\n" +
                "          <groupId>org.apache.maven.plugins</groupId>\n" +
                "          <artifactId>maven-compiler-plugin</artifactId>\n" +
                "          <configuration>\n" +
                "            <annotationProcessorPaths>\n" +
                "              <path>\n" +
                "                <groupId>org.projectlombok</groupId>\n" +
                "                <artifactId>lombok</artifactId>\n" +
                "                <version>1.18.30</version>\n" +
                "              </path>\n" +
                "            </annotationProcessorPaths>\n" +
                "          </configuration>\n" +
                "        </plugin>\n" +
                "      </plugins>\n" +
                "    </pluginManagement>\n" +
                "  </build>\n" +
                "</project>\n"),
        pomXml(
            "<project>\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <parent>\n" +
                "    <groupId>com.example</groupId>\n" +
                "    <artifactId>parent</artifactId>\n" +
                "    <version>1.0</version>\n" +
                "  </parent>\n" +
                "  <artifactId>child</artifactId>\n" +
                MOCKITO_ANNOTATIONS_DEPENDENCY +
                "  <build>\n" +
                "    <plugins>\n" +
                "      <plugin>\n" +
                "        <groupId>org.apache.maven.plugins</groupId>\n" +
                "        <artifactId>maven-compiler-plugin</artifactId>\n" +
                "        <configuration>\n" +
                "          <source>17</source>\n" +
                "          <target>17</target>\n" +
                "        </configuration>\n" +
                "      </plugin>\n" +
                "    </plugins>\n" +
                "  </build>\n" +
                "</project>\n",
            spec -> spec.path("child/pom.xml").after(pom -> {
              assertThat(pom).contains("<annotationProcessorPaths combine.children=\"append\">");
              assertThat(pom).contains("<artifactId>mockito-annotations</artifactId>");
              return pom;
            })));
  }

  // A compiler plugin is present but the module does not depend on mockito-annotations (it does not
  // use Mockito) -> the recipe must not wire it.
  @Test
  void leavesPomUntouchedWhenModuleDoesNotUseMockitoAnnotations() {
    rewriteRun(
        pomXml(
            "<project>\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <groupId>com.example</groupId>\n" +
                "  <artifactId>proj</artifactId>\n" +
                "  <version>1.0</version>\n" +
                "  <build>\n" +
                "    <plugins>\n" +
                "      <plugin>\n" +
                "        <groupId>org.apache.maven.plugins</groupId>\n" +
                "        <artifactId>maven-compiler-plugin</artifactId>\n" +
                "        <configuration>\n" +
                "          <source>17</source>\n" +
                "          <target>17</target>\n" +
                "        </configuration>\n" +
                "      </plugin>\n" +
                "    </plugins>\n" +
                "  </build>\n" +
                "</project>\n"));
  }

  // Idempotency: our path already present in the same pom -> no change, no duplicate.
  @Test
  void isIdempotentWhenPathAlreadyPresent() {
    rewriteRun(
        pomXml(
            "<project>\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <groupId>com.example</groupId>\n" +
                "  <artifactId>proj</artifactId>\n" +
                "  <version>1.0</version>\n" +
                "  <build>\n" +
                "    <plugins>\n" +
                "      <plugin>\n" +
                "        <groupId>org.apache.maven.plugins</groupId>\n" +
                "        <artifactId>maven-compiler-plugin</artifactId>\n" +
                "        <configuration>\n" +
                "          <annotationProcessorPaths>\n" +
                "            <path>\n" +
                "              <groupId>io.sundr</groupId>\n" +
                "              <artifactId>mockito-annotations</artifactId>\n" +
                "              <version>999-SNAPSHOT</version>\n" +
                "            </path>\n" +
                "          </annotationProcessorPaths>\n" +
                "        </configuration>\n" +
                "      </plugin>\n" +
                "    </plugins>\n" +
                "  </build>\n" +
                "</project>\n"));
  }
}
