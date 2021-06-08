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

import static io.sundr.utils.Strings.loadResource;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.function.Function;

import org.stringtemplate.v4.*;

import io.sundr.SundrException;
import io.sundr.codegen.template.TemplateRenderer;

public class StringTemplateRenderer<T> extends TemplateRenderer<T> {

  private static final String TEMPLATE = "template";
  private static final String MODEL = "model";
  private static final String PARAMETERS = "parameters";

  private final String templateContent;
  private final String[] parameters;

  public StringTemplateRenderer(String templateContent, String... parameters) {
    this.templateContent = templateContent;
    this.parameters = parameters;
  }

  public static Optional<StringTemplateRenderer<?>> fromTemplate(String templateContent, String... parameters) {
    return Optional.of(new StringTemplateRenderer<>(templateContent, parameters));
  }

  public static Optional<StringTemplateRenderer<?>> fromTemplateUrl(URL templateUrl, String... parameters) {
    try {
      if (templateUrl == null) {
        return Optional.empty();
      }
      return fromTemplate(loadResource(templateUrl), parameters);
    } catch (IOException e) {
      throw SundrException.launderThrowable(e);
    }
  }

  public static Optional<StringTemplateRenderer<?>> fromTemplateResource(String templateResource, String... parameters) {
    return fromTemplateUrl(StringTemplateRenderer.class.getResource(templateResource), parameters);
  }

  @Override
  public Class<T> getType() {
    return (Class<T>) Class.class;
  }

  @Override
  public Function<T, String> getFunction() {
    return item -> {
      ST st = new ST(templateContent, '$', '$');
      System.out.println("MODEL:" + item);
      st.add(MODEL, item);
      st.add(PARAMETERS, parameters);
      return st.render();
    };
  }
}
