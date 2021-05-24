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

import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.apt.AptContext;
import io.sundr.codegen.api.CodeGenerator;
import io.sundr.codegen.apt.AptOutput;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;

public abstract class AbstractCodeGeneratingProcessor extends AbstractProcessor {

  private final AtomicReference<AptContext> context = new AtomicReference<>();
  private CodeGenerator generator;

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    context.set(AptContext.create(processingEnv.getElementUtils(), processingEnv.getTypeUtils(),
        DefinitionRepository.createRepository()));

    generator = CodeGenerator.newGenerator(TypeDef.class)
        .withOutput(new AptOutput(processingEnv.getFiler()))
        .skipping(AbstractCodeGeneratingProcessor::classExists)
        .build();
  }

  public void generate(TypeDef type) {
    if (generator == null) {
      throw new IllegalStateException("CodeGenerator instance shoud not be null.");
    }
    generator.generate(type);
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
