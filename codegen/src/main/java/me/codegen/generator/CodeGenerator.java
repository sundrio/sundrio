package me.codegen.generator;

import me.codegen.directives.AddToListMethodDirective;
import me.codegen.directives.AddToMapMethodDirective;
import me.codegen.directives.ClassDirective;
import me.codegen.directives.GetterMethodDirective;
import me.codegen.directives.GetterToArrayMethodDirective;
import me.codegen.directives.SetterMethodDirective;
import me.codegen.directives.WithArrayMethodDirective;
import me.codegen.directives.WithMethodDirective;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.Writer;
import java.util.Set;

public class CodeGenerator<M> {

    private final VelocityEngine engine;
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
        this.engine = new VelocityEngine();
        this.directives = directives;
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        engine.init();
        
        //Load standard directives
        engine.loadDirective(SetterMethodDirective.class.getCanonicalName());
        engine.loadDirective(WithMethodDirective.class.getCanonicalName());
        engine.loadDirective(WithArrayMethodDirective.class.getCanonicalName());
        engine.loadDirective(GetterMethodDirective.class.getCanonicalName());
        engine.loadDirective(AddToListMethodDirective.class.getCanonicalName());
        engine.loadDirective(AddToMapMethodDirective.class.getCanonicalName());
        engine.loadDirective(GetterToArrayMethodDirective.class.getCanonicalName());
        engine.loadDirective(ClassDirective.class.getCanonicalName());

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

    public void generate() {
        GeneratorUtils.generate(context, writer, template);
    }
}
