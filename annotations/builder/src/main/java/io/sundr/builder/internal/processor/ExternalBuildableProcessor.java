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

package io.sundr.builder.internal.processor;

import static io.sundr.builder.Constants.EDITABLE_ENABLED;
import static io.sundr.builder.Constants.EXTERNAL_BUILDABLE;
import static io.sundr.builder.Constants.LAZY_COLLECTIONS_INIT_ENABLED;
import static io.sundr.builder.Constants.LAZY_MAP_INIT_ENABLED;
import static io.sundr.builder.Constants.VALIDATION_ENABLED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import io.sundr.builder.Visitor;
import io.sundr.builder.annotations.ExternalBuildables;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.functions.ElementTo;
import io.sundr.model.Kind;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.codegen.utils.ModelUtils;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("io.sundr.builder.annotations.ExternalBuildables")
public class ExternalBuildableProcessor extends AbstractBuilderProcessor {
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
    Elements elements = processingEnv.getElementUtils();
    Types types = processingEnv.getTypeUtils();
    Filer filer = processingEnv.getFiler();

    BuilderContext ctx = null;
    Set<TypeDef> buildables = new HashSet<>();
    //First pass register all externals
    for (TypeElement annotation : annotations) {
      for (Element element : env.getElementsAnnotatedWith(annotation)) {
        final ExternalBuildables generated = element.getAnnotation(ExternalBuildables.class);
        if (generated == null) {
          continue;
        }
        ctx = BuilderContextManager.create(elements, types, generated.validationEnabled(), generated.generateBuilderPackage(),
            generated.builderPackage());

        for (String name : generated.value()) {
          PackageElement packageElement = elements.getPackageElement(name);
          List<TypeElement> typeElements = new ArrayList<>();

          if (packageElement != null) {
            for (Element e : packageElement.getEnclosedElements()) {
              if (e instanceof TypeElement) {
                typeElements.add((TypeElement) e);
              }
            }
          } else {
            TypeElement e = elements.getTypeElement(name);
            if (e != null) {
              typeElements.add(e);
            }
          }

          for (TypeElement typeElement : typeElements) {
            final boolean isLazyCollectionInitEnabled = generated.lazyCollectionInitEnabled();
            final boolean isLazyMapInitEnabled = generated.lazyMapInitEnabled();
            final boolean includeInterfaces = generated.includeInterfaces();
            final boolean includeAbstractClasses = generated.includeAbstractClasses();

            TypeDef original = ElementTo.TYPEDEF.apply(ModelUtils.getClassElement(typeElement));
            String fqcn = original.getFullyQualifiedName();
            boolean isBuildable = original.getKind() != Kind.ENUM
                && (includeAbstractClasses || !original.isAbstract())
                && (includeInterfaces || original.getKind() != Kind.INTERFACE)
                && isIncluded(fqcn, generated.includes()) && !isExcluded(fqcn, generated.excludes());

            TypeDef b = new TypeDefBuilder(original)
                .accept(new Visitor<PropertyBuilder>() {
                  @Override
                  public void visit(PropertyBuilder builder) {
                    if (isBuildable) {
                      builder.addToAttributes(EXTERNAL_BUILDABLE, generated);
                      builder.addToAttributes(EDITABLE_ENABLED, generated.editableEnabled());
                      builder.addToAttributes(VALIDATION_ENABLED, generated.validationEnabled());
                      builder.addToAttributes(LAZY_COLLECTIONS_INIT_ENABLED, isLazyCollectionInitEnabled);
                      builder.addToAttributes(LAZY_MAP_INIT_ENABLED, isLazyMapInitEnabled);
                    }
                  }
                }).build();

            if (b.getKind() == Kind.ENUM) {
              continue;
            }

            if (b.isAbstract() && !includeAbstractClasses) {
              continue;
            }

            if (b.getKind() == Kind.INTERFACE && !includeInterfaces) {
              continue;
            }

            if (!isIncluded(b.getFullyQualifiedName(), generated.includes())) {
              continue;
            }
            if (isExcluded(b.getFullyQualifiedName(), generated.excludes())) {
              continue;
            }
            ctx.getDefinitionRepository().register(b);
            ctx.getBuildableRepository().register(b);
            buildables.add(b);
          }
        }

        for (TypeElement ref : BuilderUtils.getBuildableReferences(ctx, generated)) {
          final boolean isLazyCollectionInitEnabled = generated.lazyCollectionInitEnabled();
          final boolean isLazyMapInitEnabled = generated.lazyMapInitEnabled();
          final boolean includeInterfaces = generated.includeInterfaces();
          final boolean includeAbstractClasses = generated.includeAbstractClasses();

          TypeDef original = ElementTo.TYPEDEF.apply(ModelUtils.getClassElement(ref));
          String fqcn = original.getFullyQualifiedName();
          boolean isBuildable = original.getKind() != Kind.ENUM && !original.isAbstract()
              && isIncluded(fqcn, generated.includes()) && !isExcluded(fqcn, generated.excludes());

          TypeDef r = new TypeDefBuilder(original)
              .accept(new Visitor<PropertyBuilder>() {
                @Override
                public void visit(PropertyBuilder builder) {
                  if (isBuildable) {
                    builder.addToAttributes(EXTERNAL_BUILDABLE, generated);
                    builder.addToAttributes(EDITABLE_ENABLED, generated.editableEnabled());
                    builder.addToAttributes(VALIDATION_ENABLED, generated.validationEnabled());
                    builder.addToAttributes(LAZY_COLLECTIONS_INIT_ENABLED, isLazyCollectionInitEnabled);
                    builder.addToAttributes(LAZY_MAP_INIT_ENABLED, isLazyMapInitEnabled);
                  }
                }
              }).build();

          if (r.getKind() == Kind.ENUM || r.isAbstract()) {
            continue;
          }
          if (r.getKind() == Kind.ENUM) {
            continue;
          }

          if (r.isAbstract() && !includeAbstractClasses) {
            continue;
          }

          if (r.getKind() == Kind.INTERFACE && !includeInterfaces) {
            continue;
          }

          if (!isIncluded(r.getFullyQualifiedName(), generated.includes())) {
            continue;
          }
          if (isExcluded(r.getFullyQualifiedName(), generated.excludes())) {
            continue;
          }

          ctx.getDefinitionRepository().register(r);
          ctx.getBuildableRepository().register(r);
          buildables.add(r);
        }
      }
    }

    if (ctx == null) {
      return true;
    }

    generateLocalDependenciesIfNeeded();
    addCustomMappings(ctx);
    ctx.getDefinitionRepository().updateReferenceMap();
    generateBuildables(ctx, buildables);
    generatePojos(ctx, buildables);
    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
        String.format("%-120s", "100%: Builder generation complete."));
    return true;
  }

  private boolean isIncluded(String fqcn, String[] includes) {
    return includes.length == 0 || Arrays.stream(includes).map(Pattern::compile).map(p -> p.matcher(fqcn))
        .filter(Matcher::matches).findAny().isPresent();
  }

  private boolean isExcluded(String fqcn, String[] excludes) {
    return excludes.length != 0 &&
        Arrays.stream(excludes).map(Pattern::compile).map(p -> p.matcher(fqcn)).filter(Matcher::matches).findAny().isPresent();
  }

}
