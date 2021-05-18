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
import io.sundr.model.AttributeKey;
import io.sundr.model.repo.DefinitionRepository;

public class AptContext extends AdapterContext {

  private static final AttributeKey<Types> TYPES_KEY = new AttributeKey<>(Types.class);
  private static final AttributeKey<Elements> ELEMENTS_KEY = new AttributeKey<>(Elements.class);

  private static AptContext INSTANCE;
  private final Set<TypeElement> references = new HashSet<>();

  private AptContext(Elements elements, Types types, DefinitionRepository repository) {
    super(repository, createAttributes(elements, types));
  }

  private static Map<AttributeKey, Object> createAttributes(Elements elements, Types types) {
    Map<AttributeKey, Object> attributes = new HashMap<>();
    attributes.put(ELEMENTS_KEY, elements);
    attributes.put(TYPES_KEY, types);
    return attributes;
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
    return getAttribute(TYPES_KEY);
  }

  public Elements getElements() {
    return getAttribute(ELEMENTS_KEY);
  }

  public boolean isDeep() {
    return true;
  }

  public Set<TypeElement> getReferences() {
    return this.references;
  }
}
