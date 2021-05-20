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

package io.sundr.codegen.apt.processor;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

import io.sundr.SundrException;
import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.apt.AptContext;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;

public abstract class AbstractCodeGeneratingProcessor extends AbstractProcessor {

  private final AtomicReference<AptContext> context = new AtomicReference<>();
  private final Set<String> generated = new HashSet<>();

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    context.set(AptContext.create(processingEnv.getElementUtils(), processingEnv.getTypeUtils(),
        DefinitionRepository.createRepository()));
  }

  public void generate(TypeDef type) {
    Filer filer = processingEnv.getFiler();
    if (classExists(type)) {
      printSkipping(type.getFullyQualifiedName(), "Class already exists.");
      return;
    }
    //Only generate each file once ...
    if (generated.contains(type.getFullyQualifiedName())) {
      return;
    }
    try (Writer writer = filer.createSourceFile(type.getFullyQualifiedName()).openWriter()) {
      writer.write(type.render());
      generated.add(type.getFullyQualifiedName());
    } catch (IOException e) {
      throw SundrException.launderThrowable(e);
    }
  }

  public AptContext getAptContext() {
    return context.get();
  }

  public AdapterContext getAdapterContext() {
    return getAptContext().getAdapterContext();
  }

  public DefinitionRepository getDefinitionRepository() {
    return getAdapterContext().getDefinitionRepository();
  }

  private void printSkipping(String name, String msg) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER, String.format("Skipping: %s. %-120s", name, msg));
  }

  /**
   * Checks if class already exists.
   * 
   * @param typeDef The type definition to check if exists.
   * @return True if class can be found, false otherwise.
   */
  private static boolean classExists(TypeDef typeDef) {
    try {
      Class.forName(typeDef.getFullyQualifiedName());
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }
}
