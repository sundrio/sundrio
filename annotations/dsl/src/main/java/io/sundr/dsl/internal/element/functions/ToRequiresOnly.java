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
 *    WITHOUT WARRANTIES OR CONDITIONS OF ONLY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.dsl.internal.element.functions;

import io.sundr.dsl.annotations.Only;
import io.sundr.dsl.internal.element.functions.filter.RequiresOnlyFilter;
import io.sundr.dsl.internal.element.functions.filter.TransitionFilter;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import java.util.HashSet;
import java.util.Set;

public class ToRequiresOnly extends KeywordsAndMethodsToFilter {

    private final Element OR_NONE_VALUE;

    public ToRequiresOnly(Elements elements) {
        super(elements, Only.class.getCanonicalName());
        OR_NONE_VALUE = ELEMENT.getEnclosedElements().get(3);
    }

    public TransitionFilter apply(Element element) {
        Set<String> classes = new HashSet<String>();
        Set<String> keywords = new HashSet<String>();
        Set<String> methods = new HashSet<String>();
        Boolean explicit = false;
        Boolean orNone = false;

        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
                if (mirror.getAnnotationType().asElement().equals(ELEMENT)) {
                    explicit = true;
                    addToSet(mirror, CLASSES_VALUE, classes);
                    addToSet(mirror, KEYWORDS_VALUE, keywords);
                    addToSet(mirror, METHODS_VALUE, methods);
                    orNone = getBoolean(mirror, OR_NONE_VALUE);
                }

                //Also look for use on custom annotations
                for (AnnotationMirror innerMirror : mirror.getAnnotationType().asElement().getAnnotationMirrors()) {
                    if (innerMirror.getAnnotationType().asElement().equals(ELEMENT)) {
                        addToSet(innerMirror, CLASSES_VALUE, classes);
                        addToSet(innerMirror, KEYWORDS_VALUE, keywords);
                        addToSet(innerMirror, METHODS_VALUE, methods);
                        orNone = getBoolean(innerMirror, OR_NONE_VALUE);
                    }
                }
        }
        return new RequiresOnlyFilter(classes, keywords, methods, explicit, orNone);
    }

    @Override
    public TransitionFilter create(Set<String> classes, Set<String> keywords, Set<String> methods) {
        return new RequiresOnlyFilter(classes, keywords, methods, false, false);
    }
}
