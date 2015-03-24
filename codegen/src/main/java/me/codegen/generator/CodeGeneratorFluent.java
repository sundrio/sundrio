package me.codegen.generator;

import me.builder.Fluent;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;


public class CodeGeneratorFluent<M, T extends CodeGeneratorFluent<M, T>> implements Fluent<T> {
    private VelocityEngine engine;

    public T withEngine(VelocityEngine engine) {
        this.engine = engine;
        return (T) this;
    }

    public VelocityEngine getEngine() {
        return this.engine;
    }

    private VelocityContext context;

    public T withContext(VelocityContext context) {
        this.context = context;
        return (T) this;
    }

    public VelocityContext getContext() {
        return this.context;
    }

    private Writer writer;

    public T withWriter(Writer writer) {
        this.writer = writer;
        return (T) this;
    }

    public Writer getWriter() {
        return this.writer;
    }

    private M model;

    public T withModel(M model) {
        this.model = model;
        return (T) this;
    }

    public M getModel() {
        return this.model;
    }

    private String templateResource;

    public T withTemplateResource(String templateResource) {
        this.templateResource = templateResource;
        return (T) this;
    }

    public String getTemplateResource() {
        return this.templateResource;
    }

    private Template template;

    public T withTemplate(Template template) {
        this.template = template;
        return (T) this;
    }

    public Template getTemplate() {
        return this.template;
    }

    private Set<Class<? extends org.apache.velocity.runtime.directive.Directive>> directives = new LinkedHashSet();

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