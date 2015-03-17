package me.builder.internal.processor;

import me.builder.annotations.Buildable;
import me.builder.internal.processor.generator.CodeGenerator;
import me.builder.internal.processor.generator.GeneratorUtils;
import me.builder.internal.processor.model.FluentModel;
import me.builder.internal.processor.model.Model;

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
                    ModelFactory modelFactory = new ModelFactory(elements, types, getBuildableTypes(buildables));
                    Model model = modelFactory.create((ExecutableElement) element);

                    try {
                        generateFromModel(FluentModel.wrap(model),
                                processingEnv.getFiler().createSourceFile(model.getClassName() + "Fluent", ModelUtils.getPackageElement(element)),
                                GeneratorUtils.DEFAULT_FLUENT_TEMPLATE_LOCATION);

                        generateFromModel(model,
                                processingEnv.getFiler().createSourceFile(model.getClassName() + "Builder", ModelUtils.getPackageElement(element)),
                                GeneratorUtils.DEFAULT_BUILDER_TEMPLATE_LOCATION);

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

    private void generateFromModel(Model model, JavaFileObject fileObject, String resourceName) throws IOException {
        CodeGenerator codeGenerator = new CodeGenerator(model, fileObject.openWriter(), resourceName);
        codeGenerator.generate();
    }
}
