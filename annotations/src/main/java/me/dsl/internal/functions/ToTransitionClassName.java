package me.dsl.internal.functions;

import me.Function;
import me.dsl.annotations.AnnotationTransition;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

public class ToTransitionClassName implements Function<AnnotationMirror, String> {

    private final TypeElement ANNOTATED_TRANSITION;
    private final Element ANNOTATION_VALUE;

    public ToTransitionClassName(Elements elements) {
        this.ANNOTATED_TRANSITION = elements.getTypeElement(AnnotationTransition.class.getCanonicalName());
        this.ANNOTATION_VALUE = ANNOTATED_TRANSITION.getEnclosedElements().get(0);
    }

    public String apply(AnnotationMirror annotationMirror) {
        if (annotationMirror.getAnnotationType().asElement().equals(ANNOTATED_TRANSITION)) {
            String annotationClassName = String.valueOf(annotationMirror.getElementValues().get(ANNOTATION_VALUE).getValue());
            return annotationClassName;
        }
        return null;
    }
}
