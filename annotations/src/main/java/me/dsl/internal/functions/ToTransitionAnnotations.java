package me.dsl.internal.functions;

import me.Function;
import me.dsl.annotations.AnnotationTransition;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.LinkedHashSet;
import java.util.Set;

public class ToTransitionAnnotations implements Function<ExecutableElement, Set<AnnotationMirror>> {

    private final TypeElement ANNOTATED_TRANSITION;


    public ToTransitionAnnotations(Elements elements) {
        ANNOTATED_TRANSITION = elements.getTypeElement(AnnotationTransition.class.getCanonicalName());
    }

    public Set<AnnotationMirror> apply(ExecutableElement element) {
        Set<AnnotationMirror> annotationMirrors = new LinkedHashSet<>();
        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
            if (mirror.getAnnotationType().asElement().equals(ANNOTATED_TRANSITION)) {
                annotationMirrors.add(mirror);
            }
            //Also look for use on custom annotations
            for (AnnotationMirror innerMirror : mirror.getAnnotationType().asElement().getAnnotationMirrors()) {
                if (innerMirror.getAnnotationType().asElement().equals(ANNOTATED_TRANSITION)) {
                    annotationMirrors.add(innerMirror);
                }
            }
        }
        return annotationMirrors;
    }
}
