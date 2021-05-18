/*
 * Copyright 2016 The original authors.
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

package io.sundr.adapter.apt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.repo.DefinitionRepository;

public class AptContext extends AdapterContext {

  private final Types types;
  private final Elements elements;

  private static AptContext INSTANCE;
  private final Set<TypeElement> references = new HashSet<>();

  private AptContext(Elements elements, Types types, DefinitionRepository repository) {
    super(repository);
    this.types = types;
    this.elements = elements;
  }

  public synchronized static AptContext create(Elements elements, Types types, DefinitionRepository repository) {
    INSTANCE = new AptContext(elements, types, repository);
    return INSTANCE;
  }

  public synchronized static AptContext getContext() {
    if (INSTANCE == null) {
      throw new IllegalStateException("CodeGenContext has not been created, yet.");
    }
    return INSTANCE;
  }

  public Types getTypes() {
    return types;
  }

  public Elements getElements() {
    return elements;
  }

  public boolean isDeep() {
    return true;
  }

  public Set<TypeElement> getReferences() {
    return this.references;
  }
}
