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

package io.sundr.generator.internal.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import io.sundr.adapter.api.Adapters;
import io.sundr.adapter.apt.AptContext;
import io.sundr.adapter.apt.utils.Apt;
import io.sundr.codegen.apt.processor.AbstractCodeGeneratingProcessor;
import io.sundr.generator.annotations.Generator;
import io.sundr.generator.annotations.Generators;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;

@SupportedAnnotationTypes({ "io.sundr.generator.annotations.Generator", "io.sundr.generator.annotations.Generators" })
public class GeneratorProcessor extends AbstractCodeGeneratingProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
    Elements elements = processingEnv.getElementUtils();
    Types types = processingEnv.getTypeUtils();
    Filer filer = processingEnv.getFiler();
    AptContext aptContext = AptContext.create(elements, types, DefinitionRepository.getRepository());

    for (TypeElement typeElement : annotations) {
      for (Element element : env.getElementsAnnotatedWith(typeElement)) {
        Generator generator = element.getAnnotation(Generator.class);
        Generators generators = element.getAnnotation(Generators.class);
        if (generator == null && generators == null) {
          continue;
        }
        List<Generator> generatorList = new ArrayList<>();

        if (generator != null) {
          generatorList.add(generator);
        }

        if (generators != null) {
          for (Generator g : generators.value()) {
            generatorList.add(g);
          }
        }

        for (Generator g : generatorList) {
          TypeDef typeDef = Adapters.adaptType(Apt.getClassElement(element), aptContext);
          processGenerator(g, typeDef);
        }
      }
    }

    return false;
  }

  private void processGenerator(Generator generator, TypeDef target) {
    try {
      Class<? extends Function<TypeDef, TypeDef>> generatorFunctionClass = loadClass(getGeneratorMirror(generator));
      Function<TypeDef, TypeDef> generatorFunction = generatorFunctionClass.newInstance();
      TypeDef generatedTypeDef = generatorFunction.apply(target);
      if (generatedTypeDef != null) {
        generate(generatedTypeDef);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
            String.format("Generated class %s using generator %s",
                generatedTypeDef.getFullyQualifiedName(), generatorFunctionClass.getSimpleName()));
      }
    } catch (Exception e) {
      throw new IllegalStateException("Error processing generator: " + e.getMessage(), e);
    }
  }

  private TypeMirror getGeneratorMirror(Generator generator) {
    try {
      generator.value();
      return null;
    } catch (MirroredTypeException m) {
      return m.getTypeMirror();
    }
  }

  private Class loadClass(TypeMirror typeMirror) {
    if (typeMirror instanceof DeclaredType) {
      DeclaredType declaredType = (DeclaredType) typeMirror;
      TypeElement typeElement = (TypeElement) declaredType.asElement();
      String className = typeElement.getQualifiedName().toString();
      try {
        return Class.forName(className);
      } catch (ClassNotFoundException e) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
            String.format("Class not found: %s", className));
      }
    }
    return null;
  }
}
