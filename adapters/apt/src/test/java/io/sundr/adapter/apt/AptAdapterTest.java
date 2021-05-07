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

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.junit.Before;
import org.junit.Rule;

import com.google.testing.compile.CompilationRule;

import io.sundr.adapter.testing.AbstractAdapterTest;
import io.sundr.model.repo.DefinitionRepository;

public class AptAdapterTest extends AbstractAdapterTest<TypeElement, AptContext> {

  public @Rule CompilationRule rule = new CompilationRule();

  private Elements elements;
  private Types types;
  private AptContext context;

  @Before
  public void setup() {
    elements = rule.getElements();
    types = rule.getTypes();
    context = AptContext.create(elements, types, DefinitionRepository.getRepository());
  }

  @Override
  public AptContext getContext() {
    return context;
  }

  @Override
  public TypeElement getInput(Class type) {
    return elements.getTypeElement(type.getName());
  }
}
