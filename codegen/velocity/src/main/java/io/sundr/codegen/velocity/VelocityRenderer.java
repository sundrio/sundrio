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

package io.sundr.codegen.velocity;

import static io.sundr.utils.Strings.loadResource;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Optional;
import java.util.function.Function;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.ParserPoolImpl;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.SystemLogChute;
import org.apache.velocity.runtime.resource.ResourceCacheImpl;
import org.apache.velocity.runtime.resource.ResourceManagerImpl;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.apache.velocity.util.introspection.UberspectImpl;

import io.sundr.SundrException;
import io.sundr.codegen.template.TemplateRenderer;

public class VelocityRenderer<T> extends TemplateRenderer<T> {

  private static final String TEMPLATE = "template";
  private static final String MODEL = "model";
  private static final String PARAMETERS = "parameters";

  private final StringResourceRepository repo;
  private final VelocityEngine velocityEngine;
  private final Template template;
  private final String[] parameters;

  public VelocityRenderer(String templateContent, String... parameters) {
    this.parameters = parameters;
    this.velocityEngine = new VelocityEngine();
    this.velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "string");
    this.velocityEngine.setProperty("string.resource.loader.class", StringResourceLoader.class.getName());
    //We are going to use shading so we need to make sure that the following configuration will be shade friendly...
    this.velocityEngine.setProperty(RuntimeConstants.RESOURCE_MANAGER_CLASS, ResourceManagerImpl.class.getName());
    this.velocityEngine.setProperty(RuntimeConstants.RESOURCE_MANAGER_CACHE_CLASS, ResourceCacheImpl.class.getName());
    this.velocityEngine.setProperty(RuntimeConstants.PARSER_POOL_CLASS, ParserPoolImpl.class.getName());
    this.velocityEngine.setProperty(RuntimeConstants.UBERSPECT_CLASSNAME, UberspectImpl.class.getName());
    this.velocityEngine.setProperty("runtime.log.logsystem.class", SystemLogChute.class.getName());

    ClassLoader current = Thread.currentThread().getContextClassLoader();
    try {
      Thread.currentThread().setContextClassLoader(VelocityEngine.class.getClassLoader());
      this.velocityEngine.init();

      this.repo = StringResourceLoader.getRepository();
      this.repo.putStringResource(TEMPLATE, templateContent);
      //Load standard directives
      DirectiveProviders.listDirectives().forEach(velocityEngine::loadDirective);
    } finally {
      Thread.currentThread().setContextClassLoader(current);
    }

    this.template = this.velocityEngine.getTemplate(TEMPLATE);
  }

  public static Optional<VelocityRenderer<?>> fromTemplate(String templateContent, String... parameters) {
    return Optional.of(new VelocityRenderer<>(templateContent, parameters));
  }

  public static <T> Optional<VelocityRenderer<T>> fromTemplate(String templateContent, Class<T> type, String... parameters) {
    return Optional.of(new VelocityRenderer<T>(templateContent, parameters));
  }

  public static Optional<VelocityRenderer<?>> fromTemplateUrl(URL templateUrl, String... parameters) {
    try {
      if (templateUrl == null) {
        return Optional.empty();
      }
      return fromTemplate(loadResource(templateUrl), parameters);
    } catch (IOException e) {
      throw SundrException.launderThrowable(e);
    }
  }

  public static <T> Optional<VelocityRenderer<T>> fromTemplateUrl(URL templateUrl, Class<T> type, String... parameters) {
    try {
      if (templateUrl == null) {
        return Optional.empty();
      }
      return fromTemplate(loadResource(templateUrl), type, parameters);
    } catch (IOException e) {
      throw SundrException.launderThrowable(e);
    }
  }

  public static Optional<VelocityRenderer<?>> fromTemplateResource(String templateResource, String... parameters) {
    return fromTemplateUrl(VelocityContext.class.getResource(templateResource), parameters);
  }

  public static <T> Optional<VelocityRenderer<T>> fromTemplateResource(String templateResource, Class<T> type,
      String... parameters) {
    return fromTemplateUrl(VelocityContext.class.getResource(templateResource), type, parameters);
  }

  @Override
  public Class<T> getType() {
    return (Class<T>) Class.class;
  }

  @Override
  public Function<T, String> getFunction() {
    return item -> {
      VelocityContext velocityContext = new VelocityContext();
      try (StringWriter writer = new StringWriter()) {
        velocityContext.put(MODEL, item);
        velocityContext.put(PARAMETERS, parameters);
        template.merge(velocityContext, writer);
        writer.flush();
        return writer.toString();
      } catch (IOException e) {
        return null;
      }
    };
  }
}
