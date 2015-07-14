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
import io.sundr.dsl.annotations.Any;
import io.sundr.dsl.annotations.Exclusive;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.List;

public class ToTransitionClassName implements Function<AnnotationMirror, List<String>> {

    private final TypeElement ANY;
    private final TypeElement ALL;
    private final TypeElement EXCLUSIVE;
    private final Element ANY_VALUE;
    private final Element ALL_VALUE;
    private final Element EXCLUSIVE_VALUE;

    public ToTransitionClassName(Elements elements) {
        ANY = elements.getTypeElement(Any.class.getCanonicalName());
        ALL = elements.getTypeElement(All.class.getCanonicalName());
        EXCLUSIVE = elements.getTypeElement(Exclusive.class.getCanonicalName());
        ANY_VALUE = ANY.getEnclosedElements().get(0);
        ALL_VALUE = ALL.getEnclosedElements().get(0);
        EXCLUSIVE_VALUE = EXCLUSIVE.getEnclosedElements().get(0);
    }

    public List<String> apply(AnnotationMirror annotationMirror) {
        List<String> classNames = new ArrayList<String>();
        if (annotationMirror.getAnnotationType().asElement().equals(ANY)) {
            Object value = annotationMirror.getElementValues().get(ANY_VALUE).getValue();
            if (value instanceof String) {
                classNames.add(removeSuffix((String) value));
            } else if (value instanceof List) {
                List list = (List) value;
                for (Object item : list) {
                    String str = String.valueOf(item);
                    classNames.add(removeSuffix(str));
                }
            }
            return classNames;
        } else if (annotationMirror.getAnnotationType().asElement().equals(ALL)) {
            Object value = annotationMirror.getElementValues().get(ALL_VALUE).getValue();
            if (value instanceof String) {
                classNames.add(removeSuffix((String) value));
            } else if (value instanceof List) {
                List list = (List) value;
                for (Object item : list) {
                    String str = String.valueOf(item);
                    classNames.add(removeSuffix(str));
                }
            }
            return classNames;
        } else if (annotationMirror.getAnnotationType().asElement().equals(EXCLUSIVE)) {
            Object value = annotationMirror.getElementValues().get(EXCLUSIVE_VALUE).getValue();
            if (value instanceof String) {
                classNames.add(removeSuffix((String) value));
            } else if (value instanceof List) {
                List list = (List) value;
                for (Object item : list) {
                    String str = String.valueOf(item);
                    classNames.add(removeSuffix(str));
                }
            }
            return classNames;
        }

        return null;
    }

    private static String removeSuffix(String str) {
        if (str.endsWith(".class")) {
            return str.substring(0, str.length() - 6);
        } else {
            return str;
        }

    }
}
