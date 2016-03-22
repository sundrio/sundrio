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
import io.sundr.dsl.annotations.Keyword;
import io.sundr.dsl.annotations.Option;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ToClasses implements Function<Element, Set<String>> {

    private final TypeElement OPTION;

    public ToClasses(Elements elements) {
        OPTION = elements.getTypeElement(Option.class.getCanonicalName());
    }

    public Set<String> apply(Element element) {
        Set<String> classes = new HashSet<String>();
        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
            for (AnnotationMirror innerMirror : mirror.getAnnotationType().asElement().getAnnotationMirrors()) {
                if (innerMirror.getAnnotationType().asElement().equals(OPTION)) {
                    classes.add(mirror.getAnnotationType().asElement().toString());
                    continue;
                }
            }
        }
        return classes;
    }
}
