package me.builder.internal.processor.generator;

import me.builder.internal.processor.model.Model;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;

public class BuilderGenerator {

    private static final String DEFAULT_FLUENT_TEMPLATE_LOCATION = "templates/fluent.vm";
    private static final String DEFAULT_BUILDER_TEMPLATE_LOCATION = "templates/builder.vm";

    private final VelocityEngine engine;
    private final VelocityContext context;
    private final File output;
    private final Model model;
    private final Template fluentTemplate;
    private final Template builderTemplate;


    public BuilderGenerator(Model model, File output) {
        this(model,
                output, 
                DEFAULT_FLUENT_TEMPLATE_LOCATION, 
                DEFAULT_BUILDER_TEMPLATE_LOCATION
        );
    }

    public BuilderGenerator(Model model, File output, String fluentTemplate, String builderTemplate) {
        this.model = model;
        this.output = output;
        this.context = new VelocityContext();
        this.engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class",ClasspathResourceLoader.class.getName());
        engine.init();
        
        this.builderTemplate = engine.getTemplate(builderTemplate);
        this.fluentTemplate = engine.getTemplate(fluentTemplate);
        this.context.put("model", model);

    }

    public void generate() {
        String pkg = model.getPackageName();
        String fluentName = model.getClassName() + "Fluent.java";
        String builderName = model.getClassName() + "Builder.java";
        
        //Create output directories
        GeneratorUtils.createPackage(output, pkg);
        File packageDir = GeneratorUtils.getPackage(output, pkg);
        GeneratorUtils.generate(context, packageDir, fluentName, fluentTemplate);
        GeneratorUtils.generate(context, packageDir, builderName, builderTemplate);
    }
}
