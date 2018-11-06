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

import org.apache.velocity.Template;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Set;

import static io.sundr.codegen.utils.StringUtils.loadResource;

public class CodeGenerator<M> {

    private static final String TEMPLATE = "template";
    private static final String MODEL = "model";
    private static final String PARAMETERS = "parameters";
    private static final String TEMPLATE_READER_FAILURE = "Failed to read template.";

    private final CodeGeneratorContext context;
    private final Writer writer;
    private final M model;
    private final String[] parameters;
    private final String templateResource;
    private final URL templateUrl;
    private final String templateContent;
    private final Template template;
    private final Set<Class<? extends Directive>> directives;

    public CodeGenerator(CodeGeneratorContext context, M model, String[] parameters, Writer writer, URL templateUrl, String templateResource, String templateContent, Set<Class<? extends Directive>> directives) {
        this.context = context != null ? context : new CodeGeneratorContext();
        this.model = model;
        this.parameters = parameters;
        this.writer = writer;
        this.templateResource = templateResource;
        this.templateUrl = templateUrl;
        this.templateContent = templateContent;
        this.directives = directives;

        StringResourceRepository repo = StringResourceLoader.getRepository();
        try {
            repo.putStringResource(TEMPLATE, templateContent != null ? templateContent : (templateUrl != null ? loadResource(templateUrl) : loadResource(templateResource)));
        } catch (Exception e) {
            throw new RuntimeException(TEMPLATE_READER_FAILURE, e);
        }

        for (Class<? extends Directive> directive : directives) {
            context.getVelocityEngine().loadDirective(directive.getCanonicalName());
        }

        this.template = this.context.getVelocityEngine().getTemplate(TEMPLATE);
        this.context.getVelocityContext().put(MODEL, model);
        this.context.getVelocityContext().put(PARAMETERS, parameters);
    }

    public CodeGenerator(M model, String[] parameters, Writer writer, URL templateUrl, String templateResource, Set<Class<? extends Directive>> directives, String templateContent) {
        this(new CodeGeneratorContext(), model, parameters, writer, templateUrl, templateResource, templateContent, directives);
    }


    public Writer getWriter() {
        return writer;
    }

    public M getModel() {
        return model;
    }

    public String[] getParameters() {
        return parameters;
    }

    public String getTemplateResource() {
        return templateResource;
    }

    public URL getTemplateUrl() {
        return templateUrl;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public Template getTemplate() {
        return template;
    }

    public Set<Class<? extends Directive>> getDirectives() {
        return directives;
    }

    public void generate() throws IOException {
        GeneratorUtils.generate(context.getVelocityContext(), writer, getTemplate());
    }
}
