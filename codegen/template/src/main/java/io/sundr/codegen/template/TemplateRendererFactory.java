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
import java.util.Arrays;

import io.sundr.codegen.template.utils.Templates;

public abstract class TemplateRendererFactory {

  private static final String[] EMPTY = {};

  public abstract <T> TemplateRenderer<T> create(Class<T> type, URL templateUrl, String... parameters);

  /**
   * Get the supported template extensions.
   * 
   * @return an array with the supported extensions (without the dot).
   */
  public String[] getTemplateExtensions() {
    return EMPTY;
  }

  /**
   * Check if the renderer supports the specified template.
   * 
   * @param content The template content.
   * @return true if content is supported, false otherwise.
   */
  public boolean accepts(URL templateUrl) {
    return Templates.getExtension(templateUrl).filter(e -> Arrays.asList(getTemplateExtensions()).contains(e)).isPresent();
  }

}
