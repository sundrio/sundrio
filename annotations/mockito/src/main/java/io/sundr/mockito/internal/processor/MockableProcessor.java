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

package io.sundr.mockito.internal.processor;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

import io.sundr.adapter.api.Adapters;
import io.sundr.adapter.apt.utils.Apt;
import io.sundr.codegen.apt.processor.AbstractCodeGeneratingProcessor;
import io.sundr.mockito.annotations.Mockable;
import io.sundr.mockito.annotations.Mockables;
import io.sundr.mockito.annotations.OnNameCollision;
import io.sundr.mockito.internal.MockTarget;
import io.sundr.mockito.internal.visitors.AddAggregatorMethods;
import io.sundr.mockito.internal.visitors.AddDoStubClasses;
import io.sundr.mockito.internal.visitors.AddMockEntryPoints;
import io.sundr.mockito.internal.visitors.AddStubClasses;
import io.sundr.mockito.internal.visitors.AddVerifyClasses;
import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;

@SupportedAnnotationTypes({
    "io.sundr.mockito.annotations.Mockable",
    "io.sundr.mockito.annotations.Mockables" })
public class MockableProcessor extends AbstractCodeGeneratingProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (roundEnv.processingOver()) {
      return false;
    }

    for (TypeElement annotation : annotations) {
      for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
        Mockable mockable = element.getAnnotation(Mockable.class);
        if (mockable != null) {
          generateMockClass(adapt(element), mockable.prefix(), mockable.suffix(),
              mockable.verification(), mockable.onNameCollision(), element);
        }

        Mockables mockables = element.getAnnotation(Mockables.class);
        if (mockables != null) {
          List<MockTarget> targets = new ArrayList<>();
          for (TypeElement type : typesOf(mockables)) {
            MockTarget target = generateMockClass(adapt(type), mockables.prefix(), mockables.suffix(),
                mockables.verification(), mockables.onNameCollision(), element);
            if (target != null) {
              targets.add(target);
            }
          }
          generateAggregator(element, mockables.aggregator(), targets);
        }
      }
    }
    return false;
  }

  private MockTarget generateMockClass(TypeDef definition, String prefix, String suffix, boolean verification,
      OnNameCollision onNameCollision, Element origin) {
    String mockName = resolveMockName(definition, prefix, suffix, onNameCollision, origin);
    if (mockName == null) {
      return null;
    }
    MockTarget target = new MockTarget(definition, mockName, verification);
    warnOnTargetIssues(target);
    generate(new TypeDefBuilder()
        .withPackageName(target.getPackageName())
        .withName(target.getMockName())
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withFinal().endModifiers()
        .accept(new AddMockEntryPoints(target), new AddStubClasses(target), new AddVerifyClasses(target),
            new AddDoStubClasses(target))
        .build());
    return target;
  }

  /**
   * Resolves the name of the mock DSL class to generate, applying the collision policy when the
   * preferred name is already taken by an existing type. Returns {@code null} (after emitting an
   * error) when generation must be abandoned.
   */
  private String resolveMockName(TypeDef definition, String prefix, String suffix,
      OnNameCollision onNameCollision, Element origin) {
    String pkg = definition.getPackageName();
    String preferred = prefix + definition.getName() + suffix;
    if (!typeExists(pkg, preferred)) {
      return preferred;
    }
    if (onNameCollision == OnNameCollision.FAIL) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
          "'" + preferred + "' already exists; cannot generate a mock DSL for '" + definition.getName()
              + "'. Rename the existing type or set a different prefix()/suffix().",
          origin);
      return null;
    }
    String fallback = prefix + definition.getName() + "Generated" + suffix;
    if (typeExists(pkg, fallback)) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
          "Both '" + preferred + "' and the fallback '" + fallback + "' already exist; cannot generate a mock "
              + "DSL for '" + definition.getName() + "'. Set a custom prefix()/suffix().",
          origin);
      return null;
    }
    processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
        "'" + preferred + "' already exists; generating the mock DSL for '" + definition.getName()
            + "' as '" + fallback + "' instead. Set prefix()/suffix() to choose the name explicitly.",
        origin);
    return fallback;
  }

  private boolean typeExists(String pkg, String simpleName) {
    String fqcn = pkg == null || pkg.isEmpty() ? simpleName : pkg + "." + simpleName;
    return processingEnv.getElementUtils().getTypeElement(fqcn) != null;
  }

  private void generateAggregator(Element marker, String aggregator, List<MockTarget> targets) {
    if (aggregator.isEmpty() || targets.isEmpty()) {
      return;
    }
    if (aggregator.equals(marker.getSimpleName().toString())) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
          "The aggregator name '" + aggregator + "' collides with the annotated marker's own name. "
              + "Rename the marker or set a different aggregator() on @Mockables.",
          marker);
      return;
    }
    String packageName = processingEnv.getElementUtils().getPackageOf(marker).getQualifiedName().toString();
    String aggregatorFqcn = packageName == null || packageName.isEmpty()
        ? aggregator
        : packageName + "." + aggregator;
    generate(new TypeDefBuilder()
        .withPackageName(packageName)
        .withName(aggregator)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withFinal().endModifiers()
        .accept(new AddAggregatorMethods(targets, aggregatorFqcn))
        .build());
  }

  private TypeDef adapt(Element element) {
    return Adapters.adaptType(Apt.getClassElement(element), getAdapterContext());
  }

  private void warnOnTargetIssues(MockTarget target) {
    if (target.hasSyntheticParameterNames()) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
          "Parameter names of " + target.getDefinition().getFullyQualifiedName()
              + " are not available, so the generated withers will be named withArg0, withArg1, ... "
              + "Compile the target type with -parameters to get named withers.");
    }
    for (Map.Entry<String, String> skipped : target.getSkippedMethods().entrySet()) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
          "Skipping mock DSL for method '" + skipped.getKey() + "' of "
              + target.getDefinition().getFullyQualifiedName() + ": " + skipped.getValue());
    }
  }

  private Set<TypeElement> typesOf(Mockables mockables) {
    Set<TypeElement> types = new LinkedHashSet<>();
    try {
      mockables.value();
    } catch (MirroredTypesException e) {
      for (TypeMirror mirror : e.getTypeMirrors()) {
        Element element = processingEnv.getTypeUtils().asElement(mirror);
        if (element instanceof TypeElement) {
          types.add((TypeElement) element);
        }
      }
    }
    return types;
  }
}
