package me.builder.internal.processor.generator;

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
                .addConstructor()
                    .addArguments()
                        .withName("w")
                        .addType().withPackageName("java.lang").withClassName("Integer").endType()
                        .endArguments()
                    .addArguments()
                        .withName("w")
                        .addType().withPackageName("java.lang").withClassName("Integer").endType()
                        .endArguments()
                    .endConstructor()
                .build();

        File tmp = new File("/tmp");
        generate(javaClazz, tmp, "CircleFluent.java", GeneratorUtils.DEFAULT_FLUENT_TEMPLATE_LOCATION);
        generate(javaClazz, tmp, "CircleBuilder.java", GeneratorUtils.DEFAULT_BUILDER_TEMPLATE_LOCATION);
        
    }
    
    private static void generate(JavaClazz model, File dir, String name, String templateResource) {
        try (FileWriter fluentWriter = new FileWriter(new File(dir, name))) {
            CodeGenerator generator = new CodeGenerator(model, fluentWriter, templateResource);
            generator.generate();    
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}