package me.builder.internal.processor.generator;

import me.builder.internal.processor.model.Model;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.Writer;

public class CodeGenerator {

    private final VelocityEngine engine;
    private final VelocityContext context;
    private final Writer writer;
    private final Model model;
    private final String templateResource;
    private final Template template;

    public CodeGenerator(Model model, Writer writer, String templateResource) {
        this.model = model;
        this.writer = writer;
        this.templateResource = templateResource;
        this.context = new VelocityContext();
        this.engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init();

        this.template = engine.getTemplate(templateResource);
        this.context.put("model", model);
    }

    public void generate() {
        GeneratorUtils.generate(context, writer, template);
    }
}
