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

package io.sundr.examples.mockito;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A service with a wide creation signature: exactly the kind of method whose Mockito
 * stubbing degenerates into a wall of {@code Mockito.any()} calls.
 * <p>
 * Note that this interface carries no sundrio annotation at all; the mock DSL for it is
 * requested by the {@code MockTargets} marker in the test sources.
 */
public interface OrchestratorTemplateService {

  String create(String id, String name, boolean overwrite, TemplateSpec spec, String owner,
      Map<String, String> labels, List<String> tags);

  Optional<TemplateSpec> find(String id);

  void delete(String id);

  String render(String id);

  String render(TemplateSpec spec);

  String render(String id, Map<String, String> parameters);

  String export(String id) throws IOException;
}
