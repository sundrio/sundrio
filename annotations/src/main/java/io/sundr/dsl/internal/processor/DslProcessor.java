/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.dsl.internal.processor;

import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.processor.JavaGeneratingProcessor;
import io.sundr.codegen.utils.ModelUtils;
import io.sundr.dsl.annotations.TargetName;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.KEYWORDS;
import static io.sundr.dsl.internal.Constants.METHOD_NAME;
import static io.sundr.dsl.internal.Constants.TRANSITIONS;
import static io.sundr.dsl.internal.processor.DslUtils.createGenericInterfaces;
import static io.sundr.dsl.internal.processor.DslUtils.createTransitionInterface;

@SupportedAnnotationTypes("io.sundr.dsl.annotations.Dsl")
public class DslProcessor extends JavaGeneratingProcessor {

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
                    Set<JavaClazz> interfacesToGenerate = new LinkedHashSet<>();

                    Collection<ExecutableElement> sorted = context.getDependencyManager().sort(ElementFilter.methodsIn(typeElement.getEnclosedElements()));
                    //1st step generate generic interface for all types.
                    Set<JavaClazz> genericInterfaces = createGenericInterfaces(context, sorted);
                    for (JavaClazz clazz : genericInterfaces) {
                        if (!JavaTypeUtils.isEntryPoint(clazz)) {
                            interfacesToGenerate.add(clazz);
                        }
                    }

                    //2nd step create dependency graph.
                    Set<JavaMethod> methods = new LinkedHashSet<>();
                    Set<Node<JavaClazz>> graph = createGraph(context, genericInterfaces);
                    for (Node<JavaClazz> node : graph) {
                        JavaClazz current = node.getItem();
                        methods.add(new JavaMethodBuilder().withName((String) current.getType().getAttributes().get(METHOD_NAME))
                                .withReturnType(current.getType()).build());

                        interfacesToGenerate.addAll(createTransitionInterface(context, current, node.getTransitions()));
                    }

                    //Do generate the DSL interface
                    interfacesToGenerate.add(new JavaClazzBuilder().addType()
                            .withPackageName(ModelUtils.getPackageElement(element).toString())
                            .withClassName(targetInterface)
                            .withKind(JavaKind.INTERFACE)
                            .and()
                            .withMethods(methods)
                            .build());

                    try {
                        for (JavaClazz clazz : interfacesToGenerate) {
                            generateFromClazz(clazz, processingEnv, DEFAULT_TEMPLATE_LOCATION);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }


    private Set<Node<JavaClazz>> createGraph(DslProcessorContext context, Set<JavaClazz> clazzes) {
        Set<Node<JavaClazz>> nodes = new LinkedHashSet<>();
        for (JavaClazz clazz : clazzes) {
            if (JavaTypeUtils.isEntryPoint(clazz)) {
                Set<JavaClazz> next = new LinkedHashSet<>();
                Set<String> transitions = (Set<String>) clazz.getType().getAttributes().get(TRANSITIONS);
                for (JavaClazz candidate : clazzes) {
                    Set<String> keywords = (Set<String>) candidate.getType().getAttributes().get(KEYWORDS);
                    for (String keyword : keywords) {
                        if (transitions.contains(keyword)) {
                            next.add(candidate);
                        }
                    }
                }
                next.remove(clazz);
                nodes.add(new Node(clazz, next));
            }
        }
        return nodes;
    }
}
