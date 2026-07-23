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

import static org.openrewrite.java.Assertions.java;
import static org.openrewrite.java.Assertions.mavenProject;
import static org.openrewrite.java.Assertions.srcTestJava;
import static org.openrewrite.maven.Assertions.pomXml;

import org.junit.jupiter.api.Test;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;
import org.openrewrite.test.TypeValidation;

class AddDependencyTest implements RewriteTest {

  @Override
  public void defaults(RecipeSpec spec) {
    spec.recipeFromYaml(
        "type: specs.openrewrite.org/v1beta/recipe\n" +
            "name: io.sundr.mockito.rewrite.AddMockitoAnnotations\n" +
            "displayName: Add mockito-annotations\n" +
            "description: Adds the mockito-annotations test dependency.\n" +
            "recipeList:\n" +
            "  - org.openrewrite.maven.AddDependency:\n" +
            "      groupId: io.sundr\n" +
            "      artifactId: mockito-annotations\n" +
            "      version: latest.release\n" +
            "      scope: test\n" +
            "      onlyIfUsing: org.mockito.Mockito\n" +
            "      acceptTransitive: true\n",
        "io.sundr.mockito.rewrite.AddMockitoAnnotations")
        .parser(JavaParser.fromJavaVersion().classpath("mockito-core"))
        .afterTypeValidationOptions(TypeValidation.none());
  }

  @Test
  void addsMockitoAnnotationsDependencyWhenMockitoUsed() {
    rewriteRun(
        mavenProject("proj",
            srcTestJava(
                java(
                    "import static org.mockito.Mockito.mock;\n" +
                        "class SomeTest {\n" +
                        "  Object o = mock(Object.class);\n" +
                        "}\n")),
            pomXml(
                "<project>\n" +
                    "  <modelVersion>4.0.0</modelVersion>\n" +
                    "  <groupId>com.example</groupId>\n" +
                    "  <artifactId>proj</artifactId>\n" +
                    "  <version>1.0</version>\n" +
                    "  <dependencies>\n" +
                    "    <dependency>\n" +
                    "      <groupId>org.mockito</groupId>\n" +
                    "      <artifactId>mockito-core</artifactId>\n" +
                    "      <version>5.14.2</version>\n" +
                    "      <scope>test</scope>\n" +
                    "    </dependency>\n" +
                    "  </dependencies>\n" +
                    "</project>\n",
                spec -> spec.after(pom -> {
                  org.assertj.core.api.Assertions.assertThat(pom)
                      .contains("mockito-annotations");
                  return pom;
                }))));
  }
}
