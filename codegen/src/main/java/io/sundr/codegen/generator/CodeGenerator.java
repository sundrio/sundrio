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
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.apache.velocity.runtime.resource.util.StringResourceRepositoryImpl;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Set;

import static io.sundr.codegen.utils.StringUtils.loadResource;

public class CodeGenerator<M> {

    private final VelocityContext context;
    private final Writer writer;
    private final M model;
    private final String templateResource;
    private final URL templateUrl;
    private final Template template;
    private final Set<Class<? extends Directive>> directives;

    public CodeGenerator(M model, Writer writer, URL templateUrl, String templateResource, Set<Class<? extends Directive>> directives) {
        VelocityEngine engine = new VelocityEngine();

        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "string");
        engine.setProperty("string.resource.loader.class", StringResourceLoader.class.getName());
        engine.init();

        StringResourceRepository repo = StringResourceLoader.getRepository();
        try {
            repo.putStringResource("template", templateUrl != null ? loadResource(templateUrl) : loadResource(templateResource));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read template.", e);
        }

        //Load standard directives
        engine.loadDirective(ClassDirective.class.getCanonicalName());
        engine.loadDirective(MethodDirective.class.getCanonicalName());
        engine.loadDirective(FieldDirective.class.getCanonicalName());

        for (Class<? extends Directive> directive : directives) {
            engine.loadDirective(directive.getCanonicalName());
        }

        this.model = model;
        this.writer = writer;
        this.templateResource = templateResource;
        this.templateUrl = templateUrl;
        this.context = new VelocityContext();
        this.directives = directives;
        this.template = engine.getTemplate("template");
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

    public URL getTemplateUrl() {
        return templateUrl;
    }

    public Template getTemplate() {
        return template;
    }

    public Set<Class<? extends Directive>> getDirectives() {
        return directives;
    }

    public void generate() throws IOException {
        GeneratorUtils.generate(context, writer, getTemplate());
    }
}
