package me.builder.internal.processor;

import me.Function;
import me.builder.internal.directives.AddNestedMethodDirective;
import me.builder.internal.directives.NestedClassDirective;
import me.builder.internal.model.ToBuildableJavaProperty;
import me.builder.internal.model.ToBuildableJavaType;
import me.builder.internal.model.FluentJavaClazz;
import me.codegen.coverters.JavaClazzFunction;
import me.codegen.coverters.JavaMethodFunction;
import me.codegen.generator.CodeGeneratorBuilder;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;
import me.codegen.utils.ModelUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes("me.builder.annotations.Buildable")
public class BuildableProcessor extends AbstractProcessor {

    public static final String DEFAULT_FLUENT_TEMPLATE_LOCATION = "templates/builder/fluent.vm";
    public static final String DEFAULT_BUILDER_TEMPLATE_LOCATION = "templates/builder/builder.vm";


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();
        Types types = processingEnv.getTypeUtils();

        Function<String, JavaType> toType = new ToBuildableJavaType(elements, types);
        Function<VariableElement, JavaProperty> toProperty = new ToBuildableJavaProperty(toType);
        JavaMethodFunction toMethod = new JavaMethodFunction(toType, toProperty);
        JavaClazzFunction toClazz = new JavaClazzFunction(types, toType, toMethod, toProperty);


        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                if (element instanceof ExecutableElement) {

                    JavaClazz clazz = toClazz.apply(ModelUtils.getClassElement(element));

                    try {
                        generateFromModel(FluentJavaClazz.wrap(clazz),
                                processingEnv.getFiler().createSourceFile(clazz.getType().getClassName() + "Fluent", ModelUtils.getPackageElement(element)),
                                DEFAULT_FLUENT_TEMPLATE_LOCATION);

                        generateFromModel(clazz,
                                processingEnv.getFiler().createSourceFile(clazz.getType().getClassName() + "Builder", ModelUtils.getPackageElement(element)),
                                DEFAULT_BUILDER_TEMPLATE_LOCATION);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
                .addToDirectives(NestedClassDirective.class)
                .addToDirectives(AddNestedMethodDirective.class)
                .build()
                .generate();
    }
}
