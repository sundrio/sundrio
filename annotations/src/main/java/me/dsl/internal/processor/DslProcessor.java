package me.dsl.internal.processor;

import me.Converter;
import me.codegen.coverters.JavaClazzConverter;
import me.codegen.coverters.JavaMethodConverter;
import me.codegen.coverters.JavaPropertyConverter;
import me.codegen.coverters.JavaTypeConverter;
import me.codegen.generator.CodeGeneratorBuilder;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaMethodBuilder;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;
import me.codegen.utils.ModelUtils;
import me.dsl.annotations.Dsl;
import me.dsl.annotations.EntryPoint;
import me.dsl.annotations.Keyword;
import me.dsl.annotations.Transition;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("me.dsl.annotations.Dsl")
public class DslProcessor extends AbstractProcessor {

    public static final String DEFAULT_TEMPLATE_LOCATION = "templates/dsl/dsl.vm";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();
        Types types = processingEnv.getTypeUtils();

        Set<TypeElement> customAnnotations = ElementFilter.typesIn(env.getElementsAnnotatedWith(Keyword.class));

        Converter<JavaType, String> typeConverter = new JavaTypeConverter(elements, true);
        Converter<JavaProperty, VariableElement> propertyConverter = new JavaPropertyConverter(typeConverter);
        JavaMethodConverter methodConverter = new JavaMethodConverter(typeConverter, propertyConverter);
        JavaClazzConverter clazzConverter = new JavaClazzConverter(types, typeConverter, methodConverter, propertyConverter);

        for (TypeElement annotation : annotations) {
            for (Element element : env.getElementsAnnotatedWith(annotation)) {
                if (element instanceof TypeElement) {
                    TypeElement typeElement = (TypeElement) element;

                    Dsl dslAnnotation = element.getAnnotation(Dsl.class);
                    String targetInterface = dslAnnotation.targetInterface();

                    JavaType annotatedType = typeConverter.covert(element.toString());
                    JavaType targetType = new JavaTypeBuilder(annotatedType).withClassName(targetInterface).build();

                    JavaClazzBuilder modelBuilder = new JavaClazzBuilder().withType(targetType);

                    List<ExecutableElement> entryPoints = new ArrayList<>();
                    List<ExecutableElement> terminals = new ArrayList<>();

                    for (ExecutableElement executableElement : ElementFilter.methodsIn(typeElement.getEnclosedElements())) {

                        boolean hasCustomAnnotation = false;

                        //Process custom annotations.
                        for (AnnotationMirror mirror : executableElement.getAnnotationMirrors()) {
                            hasCustomAnnotation = customAnnotations.contains(mirror.getAnnotationType().asElement());
                        }

                        if (executableElement.getAnnotation(EntryPoint.class) != null) {
                            JavaMethod sourceMethod = methodConverter.covert(executableElement);
                            JavaMethod targetMethod = new JavaMethodBuilder()
                                    .addReturnType().withClassName("T").and().build();
                            modelBuilder.addToMethods(targetMethod);
                        }

                        if (executableElement.getAnnotation(Keyword.class) != null) {

                        }

                        if (executableElement.getAnnotation(Transition.class) != null) {

                        }

                        if (hasCustomAnnotation) {

                        }
                    }

                    JavaClazz model = modelBuilder.build();

                   // try {
                     //   generateFromModel(model,
                     //           processingEnv.getFiler().createSourceFile(model.getType().getClassName(), ModelUtils.getPackageElement(element)),
                     //           DEFAULT_TEMPLATE_LOCATION);

                   // } catch (IOException e) {
                   //     throw new RuntimeException(e);
                   // }

                }
            }
        }


        return true;
    }

    private void generateFromModel(JavaClazz model, JavaFileObject fileObject, String resourceName) throws IOException {
        new CodeGeneratorBuilder<JavaClazz>()
                .withModel(model)
                .withWriter(fileObject.openWriter())
                .withTemplateResource(resourceName)
                .build()
                .generate();
    }

}
