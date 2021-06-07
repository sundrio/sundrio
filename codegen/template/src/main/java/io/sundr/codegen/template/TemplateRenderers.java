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

package io.sundr.codegen.template;

import java.net.URL;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TemplateRenderers {

  public static <T> Optional<TemplateRenderer<T>> getTemplateRenderer(Class<T> type, URL templateUrl, String... parameters) {
    return stream().filter(f -> f.accepts(templateUrl)).map(f -> f.create(type, templateUrl, parameters)).findFirst();
  }

  private static Stream<TemplateRendererFactory> stream() {
    return StreamSupport.stream(
        ServiceLoader.load(TemplateRendererFactory.class, TemplateRendererFactory.class.getClassLoader()).spliterator(), false);
  }
}
