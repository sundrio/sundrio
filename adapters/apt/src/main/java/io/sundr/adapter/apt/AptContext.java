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
import io.sundr.adapter.api.AdapterContextAware;
import io.sundr.model.AttributeKey;
import io.sundr.model.AttributeSupport;
import io.sundr.model.repo.DefinitionRepository;

public class AptContext extends AttributeSupport implements AdapterContextAware {

  private static final AttributeKey<Types> TYPES_KEY = new AttributeKey<>(Types.class);
  private static final AttributeKey<Elements> ELEMENTS_KEY = new AttributeKey<>(Elements.class);
  private static AptContext INSTANCE;

  private final AdapterContext adapterContext;
  private final Set<TypeElement> references = new HashSet<>();

  private AptContext(Elements elements, Types types, DefinitionRepository repository) {
    super(createAttributes(elements, types));
    this.adapterContext = AdapterContext.create(repository, getAttributes());
  }

  private static Map<AttributeKey, Object> createAttributes(Elements elements, Types types) {
    Map<AttributeKey, Object> attributes = new HashMap<>();
    attributes.put(ELEMENTS_KEY, elements);
    attributes.put(TYPES_KEY, types);
    return attributes;
  }

  public synchronized static AptContext create(AdapterContext adapterContext) {
    Types types = adapterContext.getAttribute(TYPES_KEY);
    Elements elements = adapterContext.getAttribute(ELEMENTS_KEY);

    if (elements == null) {
      elements = INSTANCE != null ? INSTANCE.getAttribute(ELEMENTS_KEY) : null;
      if (elements == null) {
        throw new IllegalStateException("AptContext requires javax.lang.model.util.Elements utilitiy.");
      }
    }

    if (types == null) {
      types = INSTANCE != null ? INSTANCE.getAttribute(TYPES_KEY) : null;
      if (types == null) {
        throw new IllegalStateException("AptContext requires javax.lang.model.util.Types utilitiy.");
      }
    }

    INSTANCE = new AptContext(elements, types, adapterContext.getDefinitionRepository());
    return INSTANCE;
  }

  public synchronized static AptContext create(Elements elements, Types types) {
    INSTANCE = new AptContext(elements, types, DefinitionRepository.createRepository());
    return INSTANCE;
  }

  public synchronized static AptContext create(Elements elements, Types types, DefinitionRepository repository) {
    INSTANCE = new AptContext(elements, types, repository);
    return INSTANCE;
  }

  public synchronized static AptContext getContext() {
    if (INSTANCE == null) {
      throw new IllegalStateException("AptContext has not been created, yet.");
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

  public AdapterContext getAdapterContext() {
    return adapterContext;
  }

  public DefinitionRepository getDefinitionRepository() {
    return adapterContext.getDefinitionRepository();
  }

  public Set<TypeElement> getReferences() {
    return this.references;
  }
}
