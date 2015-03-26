package me.dsl.internal.processor;

import me.Converter;
import me.codegen.coverters.JavaClazzConverter;
import me.codegen.coverters.JavaMethodConverter;
import me.codegen.coverters.JavaPropertyConverter;
import me.codegen.coverters.JavaTypeConverter;
import me.codegen.generator.CodeGeneratorBuilder;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaMethodBuilder;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;
import me.codegen.utils.ModelUtils;
import me.dsl.annotations.TargetName;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static me.codegen.utils.StringUtils.captializeFirst;

@SupportedAnnotationTypes("me.dsl.annotations.Dsl")
public class DslProcessor extends AbstractProcessor {

    public static final String DEFAULT_TEMPLATE_LOCATION = "templates/dsl/dsl.vm";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();
        Types types = processingEnv.getTypeUtils();
        DependencyManager dependencyManager = new DependencyManager(elements, types);

        Converter<JavaType, String> typeConverter = new JavaTypeConverter(elements, true);
        Converter<JavaProperty, VariableElement> propertyConverter = new JavaPropertyConverter(typeConverter);
        JavaMethodConverter methodConverter = new JavaMethodConverter(typeConverter, propertyConverter);
        JavaClazzConverter clazzConverter = new JavaClazzConverter(types, typeConverter, methodConverter, propertyConverter);

        for (TypeElement annotation : annotations) {
            for (Element element : env.getElementsAnnotatedWith(annotation)) {
                if (element instanceof TypeElement) {
                    TypeElement typeElement = (TypeElement) element;

                    TargetName targetName = element.getAnnotation(TargetName.class);
                    String targetInterface = targetName.value();

                    JavaType annotatedType = typeConverter.covert(element.toString());
                    JavaType targetType = new JavaTypeBuilder(annotatedType).withClassName(targetInterface).build();

                    Collection<ExecutableElement> sorted = dependencyManager.sort(ElementFilter.methodsIn(typeElement.getEnclosedElements()));
                    
                    try {
                        for (ExecutableElement current : sorted) {
                            JavaMethod method = methodConverter.covert(current); 
                            TargetName targetInterfaceName = current.getAnnotation(TargetName.class);

                            String interfaceName = targetInterfaceName != null ?
                                    targetInterfaceName.value() :
                                    toInterfaceName(method.getName());
                            
                            Set<ExecutableElement> dependencies = dependencyManager.findDependencies(current);
                            
                            if (dependencies.isEmpty()) {
                                //nothing do here.
                            } else if (dependencies.size() == 1) {
                                //Depends on an existing interface
                                ExecutableElement dependency = dependencies.iterator().next();
                                String nextInterfaceName = toInterfaceName(dependency.getSimpleName().toString());
                                JavaType nextInterfaceType = new JavaTypeBuilder()
                                       .withPackageName(targetType.getPackageName())
                                       .withClassName(nextInterfaceName).build();
                                
                                method = new JavaMethodBuilder(method).withReturnType(nextInterfaceType).build();
                            } else {
                                //Generate interface for all dependencies
                                JavaClazz nextInterface = createInterfaceForElements(targetType, dependencies);
                                generateFromModel(nextInterface, processingEnv);
                                method = new JavaMethodBuilder(method).withReturnType(nextInterface.getType()).build();
                            }
                            
                            //Do generate the interface
                            JavaClazz model = new JavaClazzBuilder().addType()
                                    .withPackageName(ModelUtils.getPackageElement(current).toString())
                                    .withClassName(interfaceName)
                                    .withKind(JavaKind.INTERFACE)
                                    .and()
                                    .addToMethods(method)
                                    .build();
                            
                            generateFromModel(model, processingEnv);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }

    /**
     * Creates a {@link JavaClazz} for the specified elements. 
     * The generated class will be an empty interface that inherits all the interfaces that correspond to the specified elements.
     * @param targetType            The target type.
     * @param executableElements    The collection of executable elements.
     * @return
     */
    private JavaClazz createInterfaceForElements(JavaType targetType, Iterable<ExecutableElement> executableElements) {
        StringBuilder sb = new StringBuilder();
        Set<JavaType> interfaceTypes = new LinkedHashSet<>();
        
        for (ExecutableElement dependency : executableElements) {
            TargetName targetName = dependency.getAnnotation(TargetName.class);
            
            String className = targetName != null  ?
                    targetName.value() : 
                    dependency.getSimpleName().toString();
            
            interfaceTypes.add(new JavaTypeBuilder()
                    .withClassName(toInterfaceName(className))
                    .withPackageName(targetType.getPackageName())
                    .build());
            
            sb.append(captializeFirst(className));
        }
        
        String interfaceName = toInterfaceName(sb.toString());
        
        JavaType interfaceType = new JavaTypeBuilder()
                .withPackageName(targetType.getPackageName())
                .withClassName(interfaceName)
                .withInterfaces(interfaceTypes)
                .withKind(JavaKind.INTERFACE).build();
        
        return new JavaClazzBuilder()
                .withType(interfaceType)
                .build();
    }
    
    private void generateFromModel(JavaClazz model, ProcessingEnvironment processingEnvironment) throws IOException {
        PackageElement packageElement = processingEnvironment.getElementUtils().getPackageElement(model.getType().getPackageName());
        try {
            generateFromModel(model, processingEnv
                    .getFiler()
                    .createSourceFile(model.getType().getClassName(), packageElement), DEFAULT_TEMPLATE_LOCATION);
        } catch (Exception e) {
            //TODO: Need to avoid dublicate interfaces here.
        }
    }

    /**
     * Generates a source file from the specified {@link me.codegen.model.JavaClazz}.
     * @param model         The model of the class to generate.
     * @param fileObject    Where to save the generated class.
     * @param resourceName  Which is the template to use.
     * @throws IOException
     */
    private void generateFromModel(JavaClazz model, JavaFileObject fileObject, String resourceName) throws IOException {
            new CodeGeneratorBuilder<JavaClazz>()
                    .withModel(model)
                    .withWriter(fileObject.openWriter())
                    .withTemplateResource(resourceName)
                    .build()
                    .generate();
    }
    
    private static String toInterfaceName(String name) {
        if (name.endsWith("Interface")) {
            return name;
        }
        return captializeFirst(name) + "Interface";
    }
}
