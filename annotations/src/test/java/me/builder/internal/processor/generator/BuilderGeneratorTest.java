package me.builder.internal.processor.generator;

import me.builder.internal.directives.AddNestedMethodDirective;
import me.builder.internal.directives.NestedClassDirective;
import me.builder.internal.processor.BuildableProcessor;
import me.codegen.generator.CodeGenerator;
import me.codegen.generator.CodeGeneratorBuilder;
import me.codegen.generator.GeneratorUtils;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BuilderGeneratorTest {

    @Test
    public void testFluentTemplate() throws IOException {


        JavaClazz javaClazz = new JavaClazzBuilder()
                .addType()
                    .withClassName("Circle")
                    .withPackageName("my.Test")
                    .endType()
                .addConstructors()
                    .addArguments()
                        .withName("w")
                        .addType().withPackageName("java.lang").withClassName("Integer").endType()
                        .endArguments()
                    .addArguments()
                        .withName("w")
                        .addType().withPackageName("java.lang").withClassName("Integer").endType()
                        .endArguments()
                    .endConstructors()
                .build();

        File tmp = new File("/tmp");
        generate(javaClazz, tmp, "CircleFluent.java", BuildableProcessor.DEFAULT_FLUENT_TEMPLATE_LOCATION);
        generate(javaClazz, tmp, "CircleBuilder.java", BuildableProcessor.DEFAULT_BUILDER_TEMPLATE_LOCATION);

    }
    
    private static void generate(JavaClazz model, File dir, String name, String templateResource) {
        try (FileWriter fluentWriter = new FileWriter(new File(dir, name))) {
            new CodeGeneratorBuilder<JavaClazz>()
                    .withModel(model)
                    .withWriter(fluentWriter)
                    .withTemplateResource(templateResource)
                    .addToDirectives(AddNestedMethodDirective.class)
                    .addToDirectives(NestedClassDirective.class).build().generate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}