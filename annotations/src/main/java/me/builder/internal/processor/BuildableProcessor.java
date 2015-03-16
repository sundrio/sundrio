package me.builder.internal.processor;

import me.builder.annotations.Buildable;
import me.builder.internal.processor.generator.CodeGenerator;
import me.builder.internal.processor.generator.GeneratorUtils;
import me.builder.internal.processor.model.Model;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@SupportedAnnotationTypes("me.builder.annotations.Buildable")
public class BuildableProcessor extends AbstractProcessor {

    private Set<Element> buildableElements = new HashSet<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        buildableElements.addAll(env.getElementsAnnotatedWith(Buildable.class));

        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                if (element instanceof ExecutableElement) {
                    Model model = Analyzer.createModel((ExecutableElement) element, getBuildableTypes());
                    try {
                        generateFromModel(model,
                                processingEnv.getFiler().createSourceFile(model.getClassName() + "Fluent", Analyzer.getPackageElement(element)),
                                GeneratorUtils.DEFAULT_FLUENT_TEMPLATE_LOCATION);

                        generateFromModel(model,
                                processingEnv.getFiler().createSourceFile(model.getClassName() + "Builder", Analyzer.getPackageElement(element)),
                                GeneratorUtils.DEFAULT_BUILDER_TEMPLATE_LOCATION);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }

    private Set<TypeElement> getBuildableTypes() {
        Set<TypeElement> typeElements = new HashSet<>();
        for (Element element : buildableElements) {
            typeElements.add(Analyzer.getClassElement(element));
        }
        return typeElements;
    }

    private void generateFromModel(Model model, JavaFileObject fileObject, String resourceName) throws IOException {
        CodeGenerator codeGenerator = new CodeGenerator(model, fileObject.openWriter(), resourceName);
        codeGenerator.generate();
    }
}
