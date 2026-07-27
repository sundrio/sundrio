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

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.maven.MavenIsoVisitor;
import org.openrewrite.maven.tree.Plugin;
import org.openrewrite.maven.tree.ResolvedPom;
import org.openrewrite.xml.AddToTagVisitor;
import org.openrewrite.xml.XPathMatcher;
import org.openrewrite.xml.tree.Xml;

/**
 * Wires {@code io.sundr:mockito-annotations} into the migrated module's maven-compiler-plugin
 * {@code annotationProcessorPaths} so the annotation processor runs at compile time and
 * {@code @Mockables} actually generates the {@code Mocks}/{@code XxxMock} classes. The version is
 * derived at runtime from the recipe's own artifact so it always matches the release being applied.
 *
 * <p>
 * Because {@code annotationProcessorPaths} is a whole-list override rather than a merge, the
 * recipe follows the effective, resolved pom model to avoid clobbering inherited processors
 * (Lombok, MapStruct):
 * <ul>
 * <li>No {@code annotationProcessorPaths} in effect anywhere (neither local nor inherited): the
 * build is left untouched. The project relies on classpath processor discovery and the
 * test-scope {@code mockito-annotations} dependency is enough. Introducing
 * {@code annotationProcessorPaths} here would disable that discovery.</li>
 * <li>{@code annotationProcessorPaths} declared in this same pom: our {@code <path>} is merged
 * into that in-place list, no combine attribute needed.</li>
 * <li>Otherwise (compiler config exists but declares no local paths, whether or not a parent
 * contributes some): a fresh {@code <annotationProcessorPaths combine.children="append">} is
 * emitted. The attribute makes Maven append our path to any inherited list instead of replacing
 * it, and is harmless when there is nothing to append to. Emitting it unconditionally avoids
 * relying on the resolved model to prove inheritance, which does not always surface a parent's
 * plugin configuration reliably.</li>
 * </ul>
 * Idempotent in all cases.
 */
public class AddMockitoAnnotationProcessorPath extends Recipe {

  static final String PROCESSOR_GROUP_ID = "io.sundr";
  static final String PROCESSOR_ARTIFACT_ID = "mockito-annotations";
  static final String FALLBACK_VERSION = "999-SNAPSHOT";

  private static final String COMPILER_PLUGIN_GROUP_ID = "org.apache.maven.plugins";
  private static final String COMPILER_PLUGIN_ARTIFACT_ID = "maven-compiler-plugin";
  private static final String ANNOTATION_PROCESSOR_PATHS = "annotationProcessorPaths";
  private static final XPathMatcher BUILD_MATCHER = new XPathMatcher("/project/build");

  @Override
  public String getDisplayName() {
    return "Add mockito-annotations to the maven-compiler-plugin annotationProcessorPaths";
  }

  @Override
  public String getDescription() {
    return "Adds io.sundr:mockito-annotations to the maven-compiler-plugin annotationProcessorPaths "
        + "so the annotation processor runs at compile time and @Mockables generates the Mocks DSL. "
        + "The version is resolved at runtime to match the recipe's own release. Idempotent.";
  }

  static String resolveVersion() {
    String implementationVersion = AddMockitoAnnotationProcessorPath.class.getPackage()
        .getImplementationVersion();
    if (implementationVersion != null && !implementationVersion.isEmpty()) {
      return implementationVersion;
    }
    String fromPomProperties = versionFromPomProperties();
    if (fromPomProperties != null && !fromPomProperties.isEmpty()) {
      return fromPomProperties;
    }
    return FALLBACK_VERSION;
  }

  private static String versionFromPomProperties() {
    String resource = "/META-INF/maven/io.sundr/mockito-annotations-rewrite/pom.properties";
    try (InputStream in = AddMockitoAnnotationProcessorPath.class.getResourceAsStream(resource)) {
      if (in == null) {
        return null;
      }
      Properties properties = new Properties();
      properties.load(in);
      return properties.getProperty("version");
    } catch (Exception e) {
      throw new RuntimeException("Failed to read mockito-annotations-rewrite pom.properties", e);
    }
  }

  @Override
  public TreeVisitor<?, ExecutionContext> getVisitor() {
    return new MavenIsoVisitor<ExecutionContext>() {
      private final String version = resolveVersion();
      private boolean moduleUsesMockitoAnnotations;

      @Override
      public Xml.Document visitDocument(Xml.Document document, ExecutionContext ctx) {
        moduleUsesMockitoAnnotations = usesMockitoAnnotations(document);
        if (shouldWire(document)) {
          Xml.Tag root = document.getRoot();
          if (!root.getChild("build").isPresent()) {
            document = (Xml.Document) new AddToTagVisitor<>(root, Xml.Tag.build("<build/>"))
                .visitNonNull(document, ctx, getCursor().getParentOrThrow());
          }
        }
        return super.visitDocument(document, ctx);
      }

      @Override
      public Xml.Tag visitTag(Xml.Tag tag, ExecutionContext ctx) {
        Xml.Tag t = super.visitTag(tag, ctx);
        if (!BUILD_MATCHER.matches(getCursor())) {
          return t;
        }
        if (!moduleUsesMockitoAnnotations || !shouldWireBuild(t)) {
          return t;
        }

        Xml.Tag plugins;
        Optional<Xml.Tag> maybePlugins = t.getChild("plugins");
        if (maybePlugins.isPresent()) {
          plugins = maybePlugins.get();
        } else {
          t = (Xml.Tag) new AddToTagVisitor<>(t, Xml.Tag.build("<plugins/>"))
              .visitNonNull(t, ctx, getCursor().getParentOrThrow());
          plugins = t.getChild("plugins").get();
        }

        Optional<Xml.Tag> maybePlugin = plugins.getChildren().stream()
            .filter(p -> "plugin".equals(p.getName())
                && COMPILER_PLUGIN_ARTIFACT_ID.equals(p.getChildValue("artifactId").orElse(null)))
            .findAny();

        if (!maybePlugin.isPresent()) {
          Xml.Tag pluginTag = Xml.Tag.build(
              "<plugin>\n"
                  + "<groupId>" + COMPILER_PLUGIN_GROUP_ID + "</groupId>\n"
                  + "<artifactId>" + COMPILER_PLUGIN_ARTIFACT_ID + "</artifactId>\n"
                  + "<configuration>\n"
                  + inheritedPathsXml()
                  + "</configuration>\n"
                  + "</plugin>");
          return (Xml.Tag) new AddToTagVisitor<>(plugins, pluginTag)
              .visitNonNull(t, ctx, getCursor().getParentOrThrow());
        }

        Xml.Tag plugin = maybePlugin.get();
        Optional<Xml.Tag> maybeConfiguration = plugin.getChild("configuration");
        if (!maybeConfiguration.isPresent()) {
          Xml.Tag configurationTag = Xml.Tag.build(
              "<configuration>\n"
                  + inheritedPathsXml()
                  + "</configuration>");
          return (Xml.Tag) new AddToTagVisitor<>(plugin, configurationTag)
              .visitNonNull(t, ctx, getCursor().getParentOrThrow());
        }

        Xml.Tag configuration = maybeConfiguration.get();
        Optional<Xml.Tag> maybePaths = configuration.getChild(ANNOTATION_PROCESSOR_PATHS);
        if (!maybePaths.isPresent()) {
          Xml.Tag pathsTag = Xml.Tag.build(inheritedPathsXml());
          return (Xml.Tag) new AddToTagVisitor<>(configuration, pathsTag)
              .visitNonNull(t, ctx, getCursor().getParentOrThrow());
        }

        Xml.Tag paths = maybePaths.get();
        boolean alreadyPresent = paths.getChildren("path").stream()
            .anyMatch(p -> PROCESSOR_GROUP_ID.equals(p.getChildValue("groupId").orElse(null))
                && PROCESSOR_ARTIFACT_ID.equals(p.getChildValue("artifactId").orElse(null)));
        if (alreadyPresent) {
          return t;
        }
        Xml.Tag pathTag = Xml.Tag.build(pathXml());
        return (Xml.Tag) new AddToTagVisitor<>(paths, pathTag)
            .visitNonNull(t, ctx, getCursor().getParentOrThrow());
      }

      private Optional<Xml.Tag> configurationWithPaths(Xml.Tag build) {
        return build.getChild("plugins")
            .flatMap(plugins -> plugins.getChildren().stream()
                .filter(p -> "plugin".equals(p.getName())
                    && COMPILER_PLUGIN_ARTIFACT_ID.equals(p.getChildValue("artifactId").orElse(null)))
                .findAny())
            .flatMap(plugin -> plugin.getChild("configuration"))
            .flatMap(configuration -> configuration.getChild(ANNOTATION_PROCESSOR_PATHS));
      }

      /**
       * Wire the module only when it actually uses mockito-annotations (the dependency was added by
       * {@link AddMockitoAnnotationsDependency}, which runs first and gates on Mockito usage) AND a
       * maven-compiler-plugin is in effect. A module that does not use Mockito, or has no
       * compiler-plugin infrastructure at all, is left untouched.
       */
      private boolean shouldWire(Xml.Document document) {
        if (!moduleUsesMockitoAnnotations) {
          return false;
        }
        boolean localCompilerPlugin = document.getRoot().getChild("build")
            .flatMap(build -> build.getChild("plugins"))
            .map(plugins -> plugins.getChildren().stream()
                .anyMatch(p -> "plugin".equals(p.getName())
                    && COMPILER_PLUGIN_ARTIFACT_ID.equals(p.getChildValue("artifactId").orElse(null))))
            .orElse(false);
        return localCompilerPlugin || effectiveHasCompilerPlugin();
      }

      private boolean shouldWireBuild(Xml.Tag build) {
        boolean localCompilerPlugin = build.getChild("plugins")
            .map(plugins -> plugins.getChildren().stream()
                .anyMatch(p -> "plugin".equals(p.getName())
                    && COMPILER_PLUGIN_ARTIFACT_ID.equals(p.getChildValue("artifactId").orElse(null))))
            .orElse(false);
        return localCompilerPlugin || effectiveHasCompilerPlugin();
      }

      /**
       * Whether this module's own pom declares the {@code io.sundr:mockito-annotations} dependency.
       * The dependency is added only to Mockito-using modules by the preceding
       * {@link AddMockitoAnnotationsDependency} step, so its presence in this pom is the signal that
       * the annotation processor is wanted here. Read from the module's own XML rather than the
       * resolved model, so a dependency belonging to a sibling or child module never wires a parent
       * (e.g. a pom-packaging aggregator) that does not itself use Mockito.
       */
      private boolean usesMockitoAnnotations(Xml.Document document) {
        if (document == null) {
          return false;
        }
        return document.getRoot().getChild("dependencies")
            .map(deps -> deps.getChildren("dependency").stream()
                .anyMatch(d -> PROCESSOR_GROUP_ID.equals(d.getChildValue("groupId").orElse(null))
                    && PROCESSOR_ARTIFACT_ID.equals(d.getChildValue("artifactId").orElse(null))))
            .orElse(false);
      }

      private boolean effectiveHasCompilerPlugin() {
        ResolvedPom pom = getResolutionResult().getPom();
        return declaresCompilerPlugin(pom.getPlugins())
            || declaresCompilerPlugin(pom.getPluginManagement());
      }

      private boolean declaresCompilerPlugin(java.util.List<Plugin> plugins) {
        return plugins.stream().anyMatch(p -> COMPILER_PLUGIN_ARTIFACT_ID.equals(p.getArtifactId()));
      }

      private String inheritedPathsXml() {
        return "<annotationProcessorPaths combine.children=\"append\">\n"
            + pathXml()
            + "</annotationProcessorPaths>\n";
      }

      private String pathXml() {
        return "<path>\n"
            + "<groupId>" + PROCESSOR_GROUP_ID + "</groupId>\n"
            + "<artifactId>" + PROCESSOR_ARTIFACT_ID + "</artifactId>\n"
            + "<version>" + version + "</version>\n"
            + "</path>\n";
      }
    };
  }
}
