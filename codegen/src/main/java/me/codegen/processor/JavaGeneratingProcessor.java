package me.codegen.processor;

import me.codegen.generator.CodeGeneratorBuilder;
import me.codegen.model.JavaClazz;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.PackageElement;
import javax.tools.JavaFileObject;
import java.io.IOException;

public abstract class JavaGeneratingProcessor extends AbstractProcessor {

    /**
     * Generates a source file from the specified {@link me.codegen.model.JavaClazz}.*
     * @param model                     The model of the class to generate.
     * @param processingEnvironment     The processing environment
     * @param resourceName              The template to use.
     * @throws IOException
     */
    public void generateFromClazz(JavaClazz model, ProcessingEnvironment processingEnvironment, String resourceName) throws IOException {
        PackageElement packageElement = processingEnvironment.getElementUtils().getPackageElement(model.getType().getPackageName());
        try {
            generateFromClazz(model, processingEnv
                    .getFiler()
                    .createSourceFile(model.getType().getClassName(), packageElement), resourceName);
        } catch (Exception e) {
            //TODO: Need to avoid dublicate interfaces here.
        }
    }

    /**
     * Generates a source file from the specified {@link me.codegen.model.JavaClazz}.
     *
     * @param model        The model of the class to generate.
     * @param fileObject   Where to save the generated class.
     * @param resourceName The template to use.
     * @throws IOException
     */
   public void generateFromClazz(JavaClazz model, JavaFileObject fileObject, String resourceName) throws IOException {
        new CodeGeneratorBuilder<JavaClazz>()
                .withModel(model)
                .withWriter(fileObject.openWriter())
                .withTemplateResource(resourceName)
                .build()
                .generate();
    }
}
