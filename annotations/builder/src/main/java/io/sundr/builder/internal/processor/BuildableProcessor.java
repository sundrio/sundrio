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

import static io.sundr.builder.Constants.BUILDABLE;
import static io.sundr.builder.Constants.EDITABLE_ENABLED;
import static io.sundr.builder.Constants.IGNORE_PROPERTIES;
import static io.sundr.builder.Constants.LAZY_COLLECTIONS_INIT_ENABLED;
import static io.sundr.builder.Constants.LAZY_MAP_INIT_ENABLED;
import static io.sundr.builder.Constants.VALIDATION_ENABLED;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import io.sundr.adapter.api.Adapters;
import io.sundr.adapter.apt.AptContext;
import io.sundr.adapter.apt.utils.Apt;
import io.sundr.builder.Visitor;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.repo.DefinitionRepository;

@SupportedAnnotationTypes("io.sundr.builder.annotations.Buildable")
public class BuildableProcessor extends AbstractBuilderProcessor {
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
    Elements elements = processingEnv.getElementUtils();
    Types types = processingEnv.getTypeUtils();
    Filer filer = processingEnv.getFiler();

    BuilderContext ctx = null;

    //First pass register all buildables
    Set<TypeDef> buildables = new HashSet<>();
    for (TypeElement typeElement : annotations) {
      for (Element element : env.getElementsAnnotatedWith(typeElement)) {
        Buildable buildable = element.getAnnotation(Buildable.class);
        if (buildable == null) {
          continue;
        }

        AptContext aptContext = AptContext.create(elements, types, DefinitionRepository.getRepository());
        ctx = BuilderContextManager.create(elements, types, buildable.validationEnabled(), buildable.generateBuilderPackage(),
            buildable.builderPackage());
        TypeDef b = new TypeDefBuilder(Adapters.adaptType(Apt.getClassElement(element), aptContext))
            .addToAttributes(BUILDABLE, buildable)
            .addToAttributes(EDITABLE_ENABLED, buildable.editableEnabled())
            .addToAttributes(VALIDATION_ENABLED, buildable.validationEnabled())
            .addToAttributes(IGNORE_PROPERTIES, buildable.ignore())
            .accept(new Visitor<PropertyBuilder>() {
              @Override
              public void visit(PropertyBuilder builder) {
                builder.addToAttributes(LAZY_COLLECTIONS_INIT_ENABLED, buildable.lazyCollectionInitEnabled());
                builder.addToAttributes(LAZY_MAP_INIT_ENABLED, buildable.lazyMapInitEnabled());
              }
            }).build();

        ctx.getDefinitionRepository().register(b);
        ctx.getBuildableRepository().register(b);
        buildables.add(b);

        for (TypeElement ref : BuilderUtils.getBuildableReferences(ctx, buildable)) {
          TypeDef r = new TypeDefBuilder(Adapters.adaptType(Apt.getClassElement(ref), aptContext))
              .addToAttributes(BUILDABLE, buildable)
              .addToAttributes(EDITABLE_ENABLED, buildable.editableEnabled())
              .addToAttributes(VALIDATION_ENABLED, buildable.validationEnabled())
              .addToAttributes(IGNORE_PROPERTIES, buildable.ignore())
              .accept(new Visitor<PropertyBuilder>() {
                @Override
                public void visit(PropertyBuilder builder) {
                  builder.addToAttributes(LAZY_COLLECTIONS_INIT_ENABLED, buildable.lazyCollectionInitEnabled());
                  builder.addToAttributes(LAZY_MAP_INIT_ENABLED, buildable.lazyMapInitEnabled());
                }
              }).build();

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
    ctx.getDefinitionRepository().updateReferenceMap();
    generateBuildables(ctx, buildables);
    generatePojos(ctx, buildables);
    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
        String.format("%-120s", "100%: Builder generation complete."));
    return false;
  }
}
