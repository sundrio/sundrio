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

package io.sundr.codegen.generator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.ParserPoolImpl;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.SystemLogChute;
import org.apache.velocity.runtime.resource.ResourceCacheImpl;
import org.apache.velocity.runtime.resource.ResourceManagerImpl;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.util.introspection.UberspectImpl;

import io.sundr.codegen.velocity.directives.ClassDirective;
import io.sundr.codegen.velocity.directives.FieldDirective;
import io.sundr.codegen.velocity.directives.MethodDirective;
import io.sundr.codegen.velocity.directives.PluralizeDirective;
import io.sundr.codegen.velocity.directives.SingularizeDirective;

public class CodeGeneratorContext {

  private final VelocityEngine velocityEngine;
  private final VelocityContext velocityContext;

  public CodeGeneratorContext() {
    this(new VelocityEngine(), new VelocityContext());
  }

  public CodeGeneratorContext(VelocityEngine velocityEngine, VelocityContext velocityContext) {
    this.velocityEngine = velocityEngine;
    this.velocityContext = velocityContext;

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

      //Load standard directives
      this.velocityEngine.loadDirective(ClassDirective.class.getCanonicalName());
      this.velocityEngine.loadDirective(MethodDirective.class.getCanonicalName());
      this.velocityEngine.loadDirective(FieldDirective.class.getCanonicalName());
      //Load utility directives
      this.velocityEngine.loadDirective(PluralizeDirective.class.getCanonicalName());
      this.velocityEngine.loadDirective(SingularizeDirective.class.getCanonicalName());
    } finally {
      Thread.currentThread().setContextClassLoader(current);
    }
  }

  public VelocityEngine getVelocityEngine() {
    return velocityEngine;
  }

  public VelocityContext getVelocityContext() {
    return velocityContext;
  }
}
