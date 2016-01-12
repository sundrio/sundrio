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
 *    WITHOUT WARRANTIES OR CONDITIONS OF NONE KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.dsl.internal.element.functions;

import io.sundr.Function;
import io.sundr.dsl.annotations.None;
import io.sundr.dsl.internal.element.functions.filter.RequiresNoneOfFilter;
import io.sundr.dsl.internal.element.functions.filter.TransitionFilter;
import io.sundr.dsl.internal.utils.JavaTypeUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.LinkedHashSet;
import java.util.Set;

public class ToRequiresNoneOf implements Function<Element, TransitionFilter> {

    private final TypeElement NONE;
    private final Element CLASSES_VALUE;
    private final Element KEYWORDS_VALUE;

    public ToRequiresNoneOf(Elements elements) {
        NONE = elements.getTypeElement(None.class.getCanonicalName());
        CLASSES_VALUE = NONE.getEnclosedElements().get(0);
        KEYWORDS_VALUE = NONE.getEnclosedElements().get(1);
    }

    public TransitionFilter apply(Element element) {
        Set<String> keywords = new LinkedHashSet<String>();
        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
            if (mirror.getAnnotationType().asElement().equals(NONE)) {
                if (mirror.getElementValues().containsKey(CLASSES_VALUE)) {
                    keywords.addAll(JavaTypeUtils.toClassNames(mirror.getElementValues().get(CLASSES_VALUE).getValue()));
                }

                if (mirror.getElementValues().containsKey(KEYWORDS_VALUE)) {
                    keywords.addAll(JavaTypeUtils.toClassNames(mirror.getElementValues().get(KEYWORDS_VALUE).getValue()));
                }
            }
            //Also look for use on custom annotations
            for (AnnotationMirror innerMirror : mirror.getAnnotationType().asElement().getAnnotationMirrors()) {
                if (innerMirror.getAnnotationType().asElement().equals(NONE)) {
                    if (innerMirror.getElementValues().containsKey(CLASSES_VALUE)) {
                        keywords.addAll(JavaTypeUtils.toClassNames(innerMirror.getElementValues().get(CLASSES_VALUE).getValue()));
                    }

                    if (innerMirror.getElementValues().containsKey(KEYWORDS_VALUE)) {
                        keywords.addAll(JavaTypeUtils.toClassNames(innerMirror.getElementValues().get(KEYWORDS_VALUE).getValue()));
                    }
                }
            }
        }
        return new RequiresNoneOfFilter(keywords);
    }
}
