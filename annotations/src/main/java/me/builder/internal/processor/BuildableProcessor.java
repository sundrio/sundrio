package me.builder.internal.processor;

import me.builder.annotations.Buildable;
import me.builder.internal.processor.generator.CodeGenerator;
import me.builder.internal.processor.generator.GeneratorUtils;
import me.builder.internal.processor.model.FluentJavaClazz;
import me.codegen.model.JavaClazz;
import me.builder.internal.processor.model.JavaClazzFactory;
import me.codegen.model.JavaClazzBuilder;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@SupportedAnnotationTypes("me.builder.annotations.Buildable")
public class BuildableProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Set<Element> buildables = new HashSet<>();
        Elements elements = processingEnv.getElementUtils();
        Types types = processingEnv.getTypeUtils();


        buildables.addAll(env.getElementsAnnotatedWith(Buildable.class));

        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                if (element instanceof ExecutableElement) {
                    JavaClazzFactory modelFactory = new JavaClazzFactory(elements, types, getBuildableTypes(buildables));
                    JavaClazz clazz = modelFactory.create((ExecutableElement) element);
                    try {
                        generateFromModel(FluentJavaClazz.wrap(clazz),
                                processingEnv.getFiler().createSourceFile(clazz.getType().getClassName() + "Fluent", ModelUtils.getPackageElement(element)),
                                GeneratorUtils.DEFAULT_FLUENT_TEMPLATE_LOCATION);

                        generateFromModel(clazz,
                                processingEnv.getFiler().createSourceFile(clazz.getType().getClassName() + "Builder", ModelUtils.getPackageElement(element)),
                                GeneratorUtils.DEFAULT_BUILDER_TEMPLATE_LOCATION);

//                        new JavaClazzBuilder(clazz)
//                        generateFromModel(clazz,
//                                processingEnv.getFiler().createSourceFile(clazz.getType().getClassName() + "TestBuilder", ModelUtils.getPackageElement(element)),
//                                GeneratorUtils.DEFAULT_BUILDER_TEMPLATE_LOCATION);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }

    private Set<TypeElement> getBuildableTypes(Set<Element> elements) {
        Set<TypeElement> typeElements = new HashSet<>();
        for (Element element : elements) {
            typeElements.add(ModelUtils.getClassElement(element));
        }
        return typeElements;
    }

    private void generateFromModel(JavaClazz model, JavaFileObject fileObject, String resourceName) throws IOException {
        CodeGenerator codeGenerator = new CodeGenerator(model, fileObject.openWriter(), resourceName);
        codeGenerator.generate();
    }
}
