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
import io.sundr.dsl.annotations.Keyword;
import io.sundr.dsl.internal.utils.JavaTypeUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.LinkedHashSet;
import java.util.Set;

public class ToKeywords implements Function<Element, Set<String>> {

    private final TypeElement KEYWORD;
    private final Element VALUE;


    public ToKeywords(Elements elements) {
        KEYWORD = elements.getTypeElement(Keyword.class.getCanonicalName());
        VALUE = KEYWORD.getEnclosedElements().get(0);
    }

    public Set<String> apply(Element element) {
        Set<String> keywords = new LinkedHashSet<String>();
        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
            if (mirror.getAnnotationType().asElement().equals(KEYWORD)) {
                keywords.addAll(JavaTypeUtils.toClassNames(mirror.getElementValues().get(VALUE).getValue()));
            }


            for (AnnotationMirror innerMirror : mirror.getAnnotationType().asElement().getAnnotationMirrors()) {
                if (innerMirror.getAnnotationType().asElement().equals(KEYWORD)) {
                    if (innerMirror.getElementValues().containsKey(VALUE)) {
                        keywords.addAll(JavaTypeUtils.toClassNames(innerMirror.getElementValues().get(VALUE).getValue()));
                    } else {
                        keywords.addAll(JavaTypeUtils.toClassNames(mirror.getAnnotationType().asElement().toString()));
                    }
                }
            }
        }
        return keywords;
    }

}
