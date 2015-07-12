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
import io.sundr.dsl.annotations.Any;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.List;

public class ToTransitionClassName implements Function<AnnotationMirror, List<String>> {

    private final TypeElement ANNOTATED_TRANSITION;
    private final Element ANNOTATION_VALUE;

    public ToTransitionClassName(Elements elements) {
        ANNOTATED_TRANSITION = elements.getTypeElement(Any.class.getCanonicalName());
        ANNOTATION_VALUE = ANNOTATED_TRANSITION.getEnclosedElements().get(0);
    }

    public List<String> apply(AnnotationMirror annotationMirror) {
        List<String> classNames = new ArrayList<String>();
        if (annotationMirror.getAnnotationType().asElement().equals(ANNOTATED_TRANSITION)) {
            Object value = annotationMirror.getElementValues().get(ANNOTATION_VALUE).getValue();
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
