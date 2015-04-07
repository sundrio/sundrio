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

package io.sundr.dsl.internal.element.functions;

import io.sundr.Function;
import io.sundr.codegen.utils.ModelUtils;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.dsl.internal.element.functions.ToTransitionAnnotations;
import io.sundr.dsl.internal.element.functions.ToTransitionClassName;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.codegen.utils.ModelUtils.findMethodsAnnotatedWith;

public class FindTransitions implements Function<ExecutableElement, Set<ExecutableElement>> {

    private final Elements elements;
    private final Set<AnnotationMirror> whiteList;

    private final ToTransitionAnnotations TO_TRANSITION_ANNOTATIONS;
    private final ToTransitionClassName TO_TRANSITION_CLASSNAME;

    public FindTransitions(Elements elements) {
        this(elements, new LinkedHashSet<AnnotationMirror>());
    }

    public FindTransitions(Elements elements, Set<AnnotationMirror> whiteList) {
        this.elements = elements;
        this.whiteList = whiteList;
        TO_TRANSITION_ANNOTATIONS = new ToTransitionAnnotations(elements);
        TO_TRANSITION_CLASSNAME = new ToTransitionClassName(elements);
    }

    public Set<ExecutableElement> apply(ExecutableElement element) {
        Set<ExecutableElement> result = new LinkedHashSet<>();
        TypeElement classElement = ModelUtils.getClassElement(element);
        Set<AnnotationMirror> annotationTransition = TO_TRANSITION_ANNOTATIONS.apply(element);

        if (element.getAnnotation(Terminal.class) != null) {
            //Do nothing and return
        } else if (!annotationTransition.isEmpty()) {
            for (AnnotationMirror transition : annotationTransition) {
                if (whiteList.isEmpty() || whiteList.contains(transition )) {
                    for (String className : TO_TRANSITION_CLASSNAME.apply(transition)) {
                        result.addAll(findMethodsAnnotatedWithClassName(classElement, className));
                    }
                }
            }
        } else {
            result.addAll(findMethodsAnnotatedWith(classElement, Terminal.class));
        }
        result.removeAll(findMethodsAnnotatedWith(classElement, EntryPoint.class));
        result.remove(element);
        return result;
    }

    private Set<ExecutableElement> findMethodsAnnotatedWithClassName(TypeElement classElement, String className) {
        Set<ExecutableElement> result = new LinkedHashSet<>();
        TypeElement annotationType = elements.getTypeElement(className);
        for (ExecutableElement methodElement : ElementFilter.methodsIn(classElement.getEnclosedElements())) {
            if (getAnnotationMirror(methodElement, annotationType) != null) {
                result.add(methodElement);
            }
        }
        return result;
    }


    /**
     * Find and return the {@link AnnotationMirror} that matches the specified {@link TypeElement}.
     *
     * @param executableElement
     * @param typeElement
     * @return
     */
    private AnnotationMirror getAnnotationMirror(ExecutableElement executableElement, TypeElement typeElement) {
        for (AnnotationMirror mirror : executableElement.getAnnotationMirrors()) {
            {
                if (mirror.getAnnotationType().asElement().equals(typeElement)) {
                    return mirror;
                }
            }
        }
        return null;
    }
}
