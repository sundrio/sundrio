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
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.processor.JavaGeneratingProcessor;
import io.sundr.codegen.utils.ModelUtils;
import io.sundr.dsl.annotations.InterfaceName;
import io.sundr.dsl.internal.graph.Node;
import io.sundr.dsl.internal.graph.NodeContext;
import io.sundr.dsl.internal.type.functions.Generics;
import io.sundr.dsl.internal.utils.JavaTypeUtils;

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

import static io.sundr.dsl.internal.utils.JavaTypeUtils.executablesToInterfaces;

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
                    InterfaceName interfaceName = element.getAnnotation(InterfaceName.class);
                    String targetInterface = interfaceName.value();
                    Set<JavaClazz> interfacesToGenerate = new LinkedHashSet<JavaClazz>();

                    Collection<ExecutableElement> sorted = ElementFilter.methodsIn(typeElement.getEnclosedElements());
                    //1st step generate generic interface for all types.
                    Set<JavaClazz> genericInterfaces = executablesToInterfaces(context, sorted);
                    Set<JavaClazz> genericAndScopeInterfaces = context.getToScope().apply(genericInterfaces);
                    for (JavaClazz clazz : genericAndScopeInterfaces) {
                        if (!JavaTypeUtils.isEntryPoint(clazz)) {
                            interfacesToGenerate.add(clazz);
                        }
                    }

                    //2nd step create dependency graph.
                    Set<Method> methods = new LinkedHashSet<Method>();
                    Set<Node<JavaClazz>> graph = context.getToGraph().apply(genericAndScopeInterfaces);

                    for (Node<JavaClazz> root : graph) {
                        Node<JavaClazz> uncyclic = context.getToUncyclic().apply(root);
                        Node<JavaClazz> unwrapped = context.getToUnwrapped().apply(NodeContext.builder().withItem(uncyclic.getItem()).build());
                        JavaClazz current = unwrapped.getItem();

                        //If there are not transitions don't generate root interface.
                        //Just add the method with the direct return type.
                        if (unwrapped.getTransitions().isEmpty()) {
                            for (Method m : current.getMethods()) {
                                methods.add(new MethodBuilder(m).withReturnType(Generics.UNWRAP.apply(m.getReturnType())).build());
                            }
                        } else {
                            for (Method m : current.getMethods()) {
                                methods.add(new MethodBuilder(m).withReturnType(current.getType()).build());
                            }

                            interfacesToGenerate.add(context.getToRoot().apply(unwrapped));
                        }
                    }

                    //Do generate the DSL interface
                    interfacesToGenerate.add(new JavaClazzBuilder()
                            .withNewType()
                                .withPackageName(ModelUtils.getPackageElement(element).toString())
                                .withName(targetInterface)
                                .withKind(Kind.INTERFACE)
                            .and()
                            .withMethods(methods)
                            .build());

                    interfacesToGenerate.addAll(context.getClassRepository().getInterfacesToGenerate());

                    try {
                        for (JavaClazz clazz : interfacesToGenerate) {
                            generateFromClazz(clazz, DEFAULT_TEMPLATE_LOCATION);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }
}
