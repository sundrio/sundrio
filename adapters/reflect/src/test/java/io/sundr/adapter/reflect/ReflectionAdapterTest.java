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

package io.sundr.adapter.reflect;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.testing.AbstractAdapterTest;
import io.sundr.model.repo.DefinitionRepository;

public class ReflectionAdapterTest extends AbstractAdapterTest<Class> {

  private AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());

  @Override
  public AdapterContext getContext() {
    return context;
  }

  @Override
  public Class getInput(Class type) {
    return type;
  }

}
