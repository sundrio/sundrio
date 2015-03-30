package me.dsl.internal.processor;

import me.Function;
import me.codegen.generator.CodeGeneratorBuilder;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaMethodBuilder;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;
import me.codegen.utils.ModelUtils;
import me.dsl.annotations.EntryPoint;
import me.dsl.annotations.TargetName;
import me.dsl.annotations.Terminal;
import me.dsl.internal.functions.FindTransitions;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static me.codegen.utils.StringUtils.captializeFirst;

@SupportedAnnotationTypes("me.dsl.annotations.Dsl")
public class DslProcessor extends AbstractProcessor {

    public static final String DEFAULT_TEMPLATE_LOCATION = "templates/dsl/dsl.vm";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();
        Types types = processingEnv.getTypeUtils();
        DslProcessorContext context = new DslProcessorContext(elements, types);

        for (TypeElement annotation : annotations) {
            for (Element element : env.getElementsAnnotatedWith(annotation)) {
                if (element instanceof TypeElement) {
                    TypeElement typeElement = (TypeElement) element;

                    TargetName targetName = element.getAnnotation(TargetName.class);
                    String targetInterface = targetName.value();

                    JavaType annotatedType = context.getToType().apply(element.toString());
                    JavaType targetType = new JavaTypeBuilder(annotatedType).withClassName(targetInterface).build();

                    Collection<ExecutableElement> sorted = context.getDependencyManager().sort(ElementFilter.methodsIn(typeElement.getEnclosedElements()));
                    try {
                        //1st step generate generic interface for all types.
                        generateGenericInterfaces(context, sorted);
                        
                        //2nd step create dependency graph.
                        Set<JavaMethod> methods = new LinkedHashSet<>();
                        Set<Node<ExecutableElement>> graph = createGraph(context, new ArrayList<>(sorted));
                        for (Node<ExecutableElement> node : graph) {
                            Set<ExecutableElement> tranisitionsTo = new LinkedHashSet<>();
                            for (Node<ExecutableElement> transition : node.getTransitions()) {
                                tranisitionsTo.add(transition.getItem());
                            }
                            ExecutableElement current = node.getItem();
                            JavaMethod method = context.getToMethod().apply(current);
                            TargetName targetInterfaceName = current.getAnnotation(TargetName.class);

                            String interfaceName = targetInterfaceName != null ?
                                    targetInterfaceName.value() :
                                    toInterfaceName(method.getName());

                            JavaType selfRefType = new JavaTypeBuilder()
                                    .withClassName(interfaceName)
                                    .withPackageName(targetType.getPackageName())
                                    .build();
                            
                            JavaClazz dependencyInterface = createInterfaceForElements(context, selfRefType, tranisitionsTo);
                            JavaType dependencyInterfaceType = dependencyInterface.getType();

                            JavaType entryPointType = new JavaTypeBuilder(dependencyInterfaceType)
                                    .withClassName(interfaceName)
                                    .build();

                            JavaClazz entryPointInterface = new JavaClazzBuilder(dependencyInterface)
                                    .withType(entryPointType)
                                    .build();
                            
                            generateFromModel(entryPointInterface, processingEnv);
                            methods.add(new JavaMethodBuilder(method).withReturnType(selfRefType).build());
                        }

                        //Do generate the interface
                        JavaClazz model = new JavaClazzBuilder().addType()
                                .withPackageName(ModelUtils.getPackageElement(element).toString())
                                .withClassName(targetInterface)
                                .withKind(JavaKind.INTERFACE)
                                .and()
                                .withMethods(methods)
                                .build();

                        generateFromModel(model, processingEnv);
                        
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }


    private void generateGenericInterfaces(DslProcessorContext context, Collection<ExecutableElement> elements) throws IOException {
        for (ExecutableElement current : elements) {
            if (current.getAnnotation(EntryPoint.class) != null) {
                //Let's skip entry points and focus on generic types.
                continue;
            }
            //Do generate the interface
            JavaType genericType = new JavaTypeBuilder().withClassName("T").build();
            JavaMethod method = new JavaMethodBuilder(context.getToMethod().apply(current)).withReturnType(genericType).build();

            TargetName targetInterfaceName = current.getAnnotation(TargetName.class);

            String interfaceName = targetInterfaceName != null ?
                    targetInterfaceName.value() :
                    toInterfaceName(method.getName());


            JavaClazz model = new JavaClazzBuilder().addType()
                    .withPackageName(ModelUtils.getPackageElement(current).toString())
                    .withClassName(interfaceName)
                    .addToGenericTypes(genericType)
                    .withKind(JavaKind.INTERFACE)
                    .and()
                    .addToMethods(method)
                    .build();

            generateFromModel(model, processingEnv);
        }
    }

    private Set<Node<ExecutableElement>> createGraph(DslProcessorContext context, List<ExecutableElement> elements) {
        Set<Node<ExecutableElement>> nodes = new LinkedHashSet<>();
        for (ExecutableElement executableElement : ModelUtils.filterByAnnotation(elements, EntryPoint.class)) {
            Set<AnnotationMirror> transitions = context.getToTransitionAnnotations().apply(executableElement);

            FindTransitions findTransitions = new FindTransitions(context.getElements(), transitions);
            nodes.add(createGraph(findTransitions, executableElement, new LinkedHashSet<ExecutableElement>()));
        }
        return nodes;
    }

    private Node<ExecutableElement> createGraph(Function<ExecutableElement, Set<ExecutableElement>> findTransitions, ExecutableElement current, Set<ExecutableElement> visited) {
        if (current.getAnnotation(Terminal.class) != null) {
            return new Node(current, Collections.<Node<ExecutableElement>>emptySet());
        } else {
            Set<Node<ExecutableElement>> toAdd = new LinkedHashSet<>();
            Set<ExecutableElement> dependencies = findTransitions.apply(current);
            Set<ExecutableElement> unvisited = new LinkedHashSet<>();

            for (ExecutableElement dep : dependencies) {
                if (visited.add(dep)) {
                    unvisited.add(dep);
                }
            }

            for (ExecutableElement dependency : unvisited) {
                toAdd.add(createGraph(findTransitions, dependency, visited));
            }
            return new Node(current, toAdd);
        }
    }


    /**
     * Creates a {@link JavaClazz} for the specified elements.
     * The generated class will be an empty interface that inherits all the interfaces that correspond to the specified elements.
     *
     * @param targetType         The target type.
     * @param executableElements The collection of executable elements.
     * @return
     */
    private JavaClazz createInterfaceForElements(DslProcessorContext context, JavaType targetType, Iterable<ExecutableElement> executableElements) {
        StringBuilder sb = new StringBuilder();
        Set<JavaType> interfaceTypes = new LinkedHashSet<>();

        for (ExecutableElement dependency : executableElements) {
            TargetName targetName = dependency.getAnnotation(TargetName.class);

            String className = targetName != null ?
                    targetName.value() :
                    dependency.getSimpleName().toString();

            JavaType returnType = context.getToType().apply(dependency.getReturnType().toString());

            interfaceTypes.add(new JavaTypeBuilder()
                    .withClassName(toInterfaceName(className))
                    .withPackageName(targetType.getPackageName())
                    .addToGenericTypes(returnType.toString().equals("void") ? targetType : returnType)
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
     *
     * @param model        The model of the class to generate.
     * @param fileObject   Where to save the generated class.
     * @param resourceName Which is the template to use.
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
