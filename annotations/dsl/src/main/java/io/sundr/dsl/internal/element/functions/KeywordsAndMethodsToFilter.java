/*
 * Copyright 2016 The original authors.
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
import io.sundr.dsl.internal.element.functions.filter.TransitionFilter;
import io.sundr.dsl.internal.utils.JavaTypeUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class KeywordsAndMethodsToFilter implements Function<Element, TransitionFilter> {

    final TypeElement ELEMENT;
    final Element CLASSES_VALUE;
    final Element KEYWORDS_VALUE;
    final Element METHODS_VALUE;


    public KeywordsAndMethodsToFilter(Elements elements, String annotationClassName) {
        this.ELEMENT = elements.getTypeElement(annotationClassName);
        CLASSES_VALUE = ELEMENT.getEnclosedElements().get(0);
        KEYWORDS_VALUE = ELEMENT.getEnclosedElements().get(1);
        METHODS_VALUE = ELEMENT.getEnclosedElements().get(2);

    }

    public TransitionFilter apply(Element element) {
        Set<String> classes = new LinkedHashSet<String>();
        Set<String> keywords = new LinkedHashSet<String>();
        Set<String> methods = new LinkedHashSet<String>();

        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
            if (mirror.getAnnotationType().asElement().equals(ELEMENT)) {
                addToSet(mirror, CLASSES_VALUE, classes);
                addToSet(mirror, KEYWORDS_VALUE, keywords);
                addToSet(mirror, METHODS_VALUE, methods);
            }

            //Also look for use on custom annotations
            for (AnnotationMirror innerMirror : mirror.getAnnotationType().asElement().getAnnotationMirrors()) {
                if (innerMirror.getAnnotationType().asElement().equals(ELEMENT)) {
                    addToSet(mirror, CLASSES_VALUE, classes);
                    addToSet(mirror, KEYWORDS_VALUE, keywords);
                    addToSet(mirror, METHODS_VALUE, methods);
                }
            }
        }
        return create(classes, keywords, methods);
    }

    void addToSet(AnnotationMirror mirror, Element element, Set<String> target) {
        if (mirror.getElementValues().containsKey(element)) {
            target.addAll(JavaTypeUtils.toClassNames(mirror.getElementValues().get(element).getValue()));
        }
    }

    Boolean getBoolean(AnnotationMirror mirror, Element element) {
        if (mirror.getElementValues().containsKey(element)) {
            return (Boolean) mirror.getElementValues().get(element).getValue();
        }
        return false;
    }

    public abstract TransitionFilter create(Set<String> classes, Set<String> keywords, Set<String> methods);
}
