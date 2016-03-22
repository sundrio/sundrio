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

import io.sundr.codegen.directives.ClassDirective;
import io.sundr.codegen.directives.FieldDirective;
import io.sundr.codegen.directives.MethodDirective;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;

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
        this.velocityEngine.init();

        //Load standard directives
        this.velocityEngine.loadDirective(ClassDirective.class.getCanonicalName());
        this.velocityEngine.loadDirective(MethodDirective.class.getCanonicalName());
        this.velocityEngine.loadDirective(FieldDirective.class.getCanonicalName());
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public VelocityContext getVelocityContext() {
        return velocityContext;
    }
}
