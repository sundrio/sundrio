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

import static io.sundr.dsl.internal.Constants.IS_GENERATED;
import static io.sundr.dsl.internal.Constants.ORIGINAL_REF;
import static io.sundr.dsl.internal.utils.TypeDefUtils.executablesToInterfaces;
import static io.sundr.model.utils.Types.modifiersToInt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import io.sundr.adapter.apt.utils.Apt;
import io.sundr.codegen.apt.processor.AbstractCodeGeneratingProcessor;
import io.sundr.dsl.annotations.InterfaceName;
import io.sundr.dsl.internal.graph.Node;
import io.sundr.dsl.internal.graph.NodeContext;
import io.sundr.dsl.internal.graph.functions.Nodes;
import io.sundr.dsl.internal.type.functions.Generics;
import io.sundr.dsl.internal.utils.TypeDefUtils;
import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.GetDefinition;

@SupportedAnnotationTypes("io.sundr.dsl.annotations.Dsl")
public class DslProcessor extends AbstractCodeGeneratingProcessor {

  public static final String DEFAULT_TEMPLATE_LOCATION = "templates/dsl/dsl.vm";

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
    Elements elements = processingEnv.getElementUtils();
    Types types = processingEnv.getTypeUtils();
    DslContext context = DslContextManager.create(elements, types);

    for (TypeElement annotation : annotations) {
      for (Element element : env.getElementsAnnotatedWith(annotation)) {
        if (element instanceof TypeElement) {
          Generics.clear();
          TypeElement typeElement = (TypeElement) element;
          InterfaceName interfaceName = element.getAnnotation(InterfaceName.class);
          String targetInterface = interfaceName.value();
          Set<TypeDef> interfacesToGenerate = new LinkedHashSet<TypeDef>();
          Collection<ExecutableElement> sorted = ElementFilter.methodsIn(typeElement.getEnclosedElements());

          //1st step generate generic interface for all types.
          Set<TypeDef> genericInterfaces = executablesToInterfaces(context, sorted);
          Set<TypeDef> genericAndScopeInterfaces = Nodes.TO_SCOPE.apply(genericInterfaces);
          for (TypeDef clazz : genericAndScopeInterfaces) {
            if (!TypeDefUtils.isEntryPoint(clazz)) {
              interfacesToGenerate.add(clazz);
            }
          }

          //2nd step create dependency graph.
          List<Method> methods = new ArrayList<Method>();
          Set<Node<TypeDef>> graph = Nodes.TO_GRAPH.apply(genericAndScopeInterfaces);

          for (Node<TypeDef> root : graph) {
            Node<TypeDef> uncyclic = Nodes.TO_UNCYCLIC.apply(root);
            Node<TypeDef> unwrapped = Nodes.TO_UNWRAPPED.apply(NodeContext.builder().withItem(uncyclic.getItem()).build());
            TypeDef current = unwrapped.getItem();

            //If there are not transitions don't generate root interface.
            //Just add the method with the direct return type.
            if (unwrapped.getTransitions().isEmpty()) {
              for (Method m : current.getMethods()) {
                TypeRef returnType = m.getReturnType();
                if (returnType instanceof ClassRef) {
                  TypeDef toUnwrap = GetDefinition.of((ClassRef) returnType);
                  methods
                      .add(new MethodBuilder(m).withReturnType(Generics.UNWRAP.apply(toUnwrap).toInternalReference()).build());
                } else if (returnType.getAttributes().containsKey(ORIGINAL_REF)) {
                  methods
                      .add(new MethodBuilder(m).withReturnType((TypeRef) returnType.getAttributes().get(ORIGINAL_REF)).build());
                } else {
                  methods.add(new MethodBuilder(m).withReturnType(returnType).build());
                }
              }
            } else {
              for (Method m : current.getMethods()) {
                methods.add(new MethodBuilder(m).withReturnType(current.toUnboundedReference()).build());
              }

              interfacesToGenerate.add(Nodes.TO_ROOT.apply(unwrapped));
            }
          }

          //Do generate the DSL interface
          interfacesToGenerate.add(new TypeDefBuilder()
              .withPackageName(Apt.getPackageElement(element).toString())
              .withName(targetInterface)
              .withKind(Kind.INTERFACE)
              .withModifiers(modifiersToInt(Modifier.PUBLIC))
              .withMethods(methods)
              .build());

          interfacesToGenerate.addAll(context.getDefinitionRepository().getDefinitions(IS_GENERATED));
          for (TypeDef clazz : interfacesToGenerate) {
            generate(clazz);
          }
        }
      }
    }
    return true;
  }
}
