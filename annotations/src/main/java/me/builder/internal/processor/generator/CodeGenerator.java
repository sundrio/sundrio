package me.builder.internal.processor.generator;

import me.codegen.directives.AddNestedMethodDirective;
import me.codegen.directives.AddToListMethodDirective;
import me.codegen.directives.AddToMapMethodDirective;
import me.codegen.directives.ClassDirective;
import me.codegen.directives.GetterMethodDirective;
import me.codegen.directives.GetterToArrayMethodDirective;
import me.codegen.directives.NestedClassDirective;
import me.codegen.directives.SetterMethodDirective;
import me.codegen.directives.WithArrayMethodDirective;
import me.codegen.directives.WithMethodDirective;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.Writer;

public class CodeGenerator<T> {

    private final VelocityEngine engine;
    private final VelocityContext context;
    private final Writer writer;
    private final T model;
    private final String templateResource;
    private final Template template;

    public CodeGenerator(T model, Writer writer, String templateResource) {
        this.model = model;
        this.writer = writer;
        this.templateResource = templateResource;
        this.context = new VelocityContext();
        this.engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        engine.init();
        engine.loadDirective(SetterMethodDirective.class.getCanonicalName());
        engine.loadDirective(WithMethodDirective.class.getCanonicalName());
        engine.loadDirective(WithArrayMethodDirective.class.getCanonicalName());
        engine.loadDirective(GetterMethodDirective.class.getCanonicalName());
        engine.loadDirective(AddToListMethodDirective.class.getCanonicalName());
        engine.loadDirective(AddToMapMethodDirective.class.getCanonicalName());
        engine.loadDirective(AddNestedMethodDirective.class.getCanonicalName());
        engine.loadDirective(GetterToArrayMethodDirective.class.getCanonicalName());
        engine.loadDirective(ClassDirective.class.getCanonicalName());
        engine.loadDirective(NestedClassDirective.class.getCanonicalName());
        this.template = engine.getTemplate(templateResource);
        this.context.put("model", model);
    }

    public void generate() {
        GeneratorUtils.generate(context, writer, template);
    }
}
