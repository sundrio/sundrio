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

import io.sundr.builder.Fluent;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.Writer;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;


public class CodeGeneratorFluent<M, T extends CodeGeneratorFluent<M, T>> implements Fluent<T> {
    private CodeGeneratorContext context;
    private M model;
    private String[] parameters;
    private Writer writer;
    private String templateResource;
    private String templateContent;
    private URL templateUrl;
    private Set<Class<? extends org.apache.velocity.runtime.directive.Directive>> directives = new LinkedHashSet();


    public T withContext(CodeGeneratorContext context) {
        this.context = context;
        return (T) this;
    }

    public CodeGeneratorContext getContext() {
        return this.context;
    }

    public T withWriter(Writer writer) {
        this.writer = writer;
        return (T) this;
    }

    public Writer getWriter() {
        return this.writer;
    }



    public T withModel(M model) {
        this.model = model;
        return (T) this;
    }

    public M getModel() {
        return this.model;
    }

    public T withParameters(String... parameters) {
        this.parameters = parameters;
        return (T) this;
    }

    public String[] getParameters() {
        return this.parameters;
    }

    public T withTemplateResource(String templateResource) {
        this.templateResource = templateResource;
        return (T) this;
    }

    public String getTemplateResource() {
        return this.templateResource;
    }


    public T withTemplateContent(String templateContent) {
        this.templateContent = templateContent;
        return (T) this;
    }

    public String getTemplateContent() {
        return this.templateContent;
    }

    public T withTemplateUrl(URL templateUrl) {
        this.templateUrl = templateUrl;
        return (T) this;
    }

    public URL getTemplateUrl() {
        return this.templateUrl;
    }


    public T withDirectives(Set directives) {
        this.directives = directives;
        return (T) this;
    }

    public T addToDirectives(Class item) {
        this.directives.add(item);
        return (T) this;
    }

    public Set<Class<? extends org.apache.velocity.runtime.directive.Directive>> getDirectives() {
        return this.directives;
    }
}