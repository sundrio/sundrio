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

package io.sundr.codegen.generator;

import io.sundr.codegen.directives.ClassDirective;
import io.sundr.codegen.directives.FieldDirective;
import io.sundr.codegen.directives.MethodDirective;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

public class CodeGenerator<M> {
    
    private final VelocityContext context;
    private final Writer writer;
    private final M model;
    private final String templateResource;
    private final Template template;
    private final Set<Class<? extends Directive>> directives;

    public CodeGenerator(M model, Writer writer, String templateResource, Set<Class<? extends Directive>> directives) {
        this.model = model;
        this.writer = writer;
        this.templateResource = templateResource;
        this.context = new VelocityContext();
        this.directives = directives;
        
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        engine.init();
        
        //Load standard directives
        engine.loadDirective(ClassDirective.class.getCanonicalName());
        engine.loadDirective(MethodDirective.class.getCanonicalName());
        engine.loadDirective(FieldDirective.class.getCanonicalName());

        for (Class<? extends Directive> directive :directives){
            engine.loadDirective(directive.getCanonicalName());
        }

        this.template = engine.getTemplate(templateResource);
        this.context.put("model", model);
    }
    

    public Writer getWriter() {
        return writer;
    }

    public M getModel() {
        return model;
    }

    public String getTemplateResource() {
        return templateResource;
    }

    public Set<Class<? extends Directive>> getDirectives() {
        return directives;
    }

    public void generate() throws IOException {
        GeneratorUtils.generate(context, writer, template);
    }
}
