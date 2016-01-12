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
import io.sundr.dsl.annotations.All;
import io.sundr.dsl.internal.element.functions.filter.RequiresAllFilter;
import io.sundr.dsl.internal.element.functions.filter.TransitionFilter;
import io.sundr.dsl.internal.utils.JavaTypeUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.LinkedHashSet;
import java.util.Set;

public class ToRequiresAll implements Function<Element, TransitionFilter> {

    private final TypeElement ALL;
    private final Element CLASSES_VALUE;
    private final Element KEYWORDS_VALUE;

    public ToRequiresAll(Elements elements) {
        ALL = elements.getTypeElement(All.class.getCanonicalName());
        CLASSES_VALUE = ALL.getEnclosedElements().get(0);
        KEYWORDS_VALUE = ALL.getEnclosedElements().get(1);
    }

    public TransitionFilter apply(Element element) {
        Set<String> keywords = new LinkedHashSet<String>();
        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
            if (mirror.getAnnotationType().asElement().equals(ALL)) {
                if (mirror.getElementValues().containsKey(CLASSES_VALUE)) {
                    keywords.addAll(JavaTypeUtils.toClassNames(mirror.getElementValues().get(CLASSES_VALUE).getValue()));
                }

                if (mirror.getElementValues().containsKey(KEYWORDS_VALUE)) {
                    keywords.addAll(JavaTypeUtils.toClassNames(mirror.getElementValues().get(KEYWORDS_VALUE).getValue()));
                }
            }

            //Also look for use on custom annotations
            for (AnnotationMirror innerMirror : mirror.getAnnotationType().asElement().getAnnotationMirrors()) {
                if (innerMirror.getAnnotationType().asElement().equals(ALL)) {
                    if (innerMirror.getElementValues().containsKey(CLASSES_VALUE)) {
                        keywords.addAll(JavaTypeUtils.toClassNames(innerMirror.getElementValues().get(CLASSES_VALUE).getValue()));
                    }

                    if (innerMirror.getElementValues().containsKey(KEYWORDS_VALUE)) {
                        keywords.addAll(JavaTypeUtils.toClassNames(innerMirror.getElementValues().get(KEYWORDS_VALUE).getValue()));
                    }
                }
            }
        }
        return new RequiresAllFilter(keywords);
    }
}
