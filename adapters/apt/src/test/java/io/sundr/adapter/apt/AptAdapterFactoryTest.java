/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.adapter.apt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.sundr.adapter.api.Adapter;
import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.Adapters;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;
import net.serverpeon.testing.compile.CompilationExtension;

@ExtendWith(CompilationExtension.class)
public class AptAdapterFactoryTest {

  private Elements elements;
  private Types types;

  @BeforeEach
  public void setup(Elements elements, Types types) {
    this.elements = elements;
    this.types = types;
  }

  private Optional<Adapter<TypeElement, TypeMirror, VariableElement, ExecutableElement>> createAdapter() {
    AptContext.create(elements, types, DefinitionRepository.getRepository());
    return Adapters.getAdapterForType(TypeElement.class, AdapterContext.getContext());
  }

  @Test
  public void shouldCreateAptAdapter() throws Exception {
    Optional<Adapter<TypeElement, TypeMirror, VariableElement, ExecutableElement>> adapter = createAdapter();
    assertTrue(adapter.isPresent());
  }

  @Test
  public void shouldAdaptList() throws Exception {
    String fqcn = "java.util.List";
    TypeElement element = elements.getTypeElement(fqcn);
    Optional<Adapter<TypeElement, TypeMirror, VariableElement, ExecutableElement>> adapter = createAdapter();
    assertTrue(adapter.isPresent());
    adapter.ifPresent(a -> {
      TypeDef def = a.adaptType(element);
      assertEquals(def.getFullyQualifiedName(), fqcn);
    });
  }
}
