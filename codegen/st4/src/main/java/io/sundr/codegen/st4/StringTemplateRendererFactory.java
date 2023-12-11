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

package io.sundr.codegen.st4;

import java.net.URL;

import io.sundr.codegen.template.TemplateRenderer;
import io.sundr.codegen.template.TemplateRendererFactory;
import io.sundr.codegen.template.utils.Templates;

public class StringTemplateRendererFactory extends TemplateRendererFactory {

  private static final String[] SUPPORTED_EXTENSIONS = new String[] { "st" };

  @Override
  public String[] getTemplateExtensions() {
    return SUPPORTED_EXTENSIONS;
  }

  @Override
  public <T> TemplateRenderer<T> create(Class<T> type, URL templateUrl, String... parameters) {
    return new StringTemplateRenderer<>(Templates.read(templateUrl), parameters);
  }
}
