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

package io.sundr.adapter.api;

import java.util.HashMap;
import java.util.Map;

import io.sundr.model.AttributeKey;
import io.sundr.model.AttributeSupport;
import io.sundr.model.repo.DefinitionRepository;

public class AdapterContext extends AttributeSupport implements AdapterContextAware {

  protected static AdapterContext INSTANCE;

  private final DefinitionRepository definitionRepository;

  private AdapterContext(DefinitionRepository definitionRepository) {
    this(definitionRepository, new HashMap<>());
  }

  private AdapterContext(DefinitionRepository definitionRepository, Map<AttributeKey, Object> attributes) {
    super(attributes);
    this.definitionRepository = definitionRepository;
  }

  public synchronized static AdapterContext create(DefinitionRepository definitionRepository) {
    return create(definitionRepository, new HashMap<>());
  }

  public synchronized static AdapterContext create(DefinitionRepository definitionRepository,
      Map<AttributeKey, Object> attributes) {
    INSTANCE = new AdapterContext(definitionRepository, attributes);
    return INSTANCE;
  }

  public synchronized static AdapterContext getContext() {
    if (INSTANCE != null) {
      return INSTANCE;
    }
    return create(DefinitionRepository.getRepository());
  }

  public DefinitionRepository getDefinitionRepository() {
    return definitionRepository;
  }

  public AdapterContext getAdapterContext() {
    return this;
  }
}
