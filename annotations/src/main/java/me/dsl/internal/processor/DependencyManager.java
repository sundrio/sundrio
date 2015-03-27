package me.dsl.internal.processor;

import me.codegen.utils.ModelUtils;
import me.dsl.annotations.AnnotationTransition;
import me.dsl.annotations.EntryPoint;
import me.dsl.annotations.Terminal;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static me.codegen.utils.ModelUtils.findMethodsAnnotatedWith;

public class DependencyManager {

    private final Elements elements;
    private final Types types;

    private final TypeElement ANNOTATTED_TRANSITION;
    private final TypeElement TERMINAL;

    public DependencyManager(Elements elements, Types types) {
        this.elements = elements;
        this.types = types;
        this.ANNOTATTED_TRANSITION = elements.getTypeElement(AnnotationTransition.class.getCanonicalName());
        this.TERMINAL = elements.getTypeElement(Terminal.class.getCanonicalName());
    }

    public Set<ExecutableElement> sort(Collection<ExecutableElement> elements) {
        Set<ExecutableElement> sorted = new LinkedHashSet<>();
        Set<ExecutableElement> visited = new LinkedHashSet<>();
        for (ExecutableElement e : elements) {
            visit(e, visited, sorted);
        }
        return sorted;
    }


    public void visit(ExecutableElement element, Set<ExecutableElement> visited, Set<ExecutableElement> sorted) {
        if (!visited.add(element)) {
            return;
        }
        for (ExecutableElement e : findDependencies(element)) {
            visit(e, visited, sorted);
        }
        sorted.add(element);
    }

    public Set<ExecutableElement> findDependencies(ExecutableElement element) {
        Set<ExecutableElement> result = new LinkedHashSet<>();
        TypeElement classElement = ModelUtils.getClassElement(element);

        Element annotationValue = ANNOTATTED_TRANSITION.getEnclosedElements().get(0);
        List<AnnotationMirror> transitions = getTransitionMirror(element);
        if (element.getAnnotation(Terminal.class) != null) {
            //Do nothing and return
        } else if (!transitions.isEmpty()) {
            for (AnnotationMirror transition : transitions) {
                if (transition.getAnnotationType().asElement().equals(ANNOTATTED_TRANSITION)) {
                    String annotationClassName = String.valueOf(transition.getElementValues().get(annotationValue).getValue());
                    result.addAll(findMethodsAnnotatedWithClassName(classElement, annotationClassName));
                }
            }
        } else {
            result.addAll(findMethodsAnnotatedWith(classElement, Terminal.class));
        }
        result.removeAll(findMethodsAnnotatedWith(classElement, EntryPoint.class));
        result.remove(element);
        return result;
    }

    private List<AnnotationMirror> getTransitionMirror(Element element) {
        List<AnnotationMirror> annotationMirrors = new ArrayList<>();
        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
            if (mirror.getAnnotationType().asElement().equals(elements.getTypeElement(AnnotationTransition.class.getCanonicalName()))) {
                annotationMirrors.add(mirror);
            }
            //Also look for use on custom annotations
            for (AnnotationMirror innerMirror : mirror.getAnnotationType().asElement().getAnnotationMirrors()) {
                if (innerMirror.getAnnotationType().asElement().equals(elements.getTypeElement(AnnotationTransition.class.getCanonicalName()))) {
                    annotationMirrors.add(innerMirror);
                }
            }
        }
        return annotationMirrors;
    }

    private Set<ExecutableElement> findMethodsAnnotatedWithClassName(TypeElement classElement, String className) {
        Set<ExecutableElement> result = new LinkedHashSet<>();
        TypeElement annotationType = elements.getTypeElement(className);
        for (ExecutableElement methodElement : ElementFilter.methodsIn(classElement.getEnclosedElements())) {
            if (getAnnotationMirror(methodElement, annotationType) != null) {
                result.add(methodElement);
            }
        }
        return result;
    }


    /**
     * Find and return the {@link AnnotationMirror} that matches the specified {@link TypeElement}.
     *
     * @param executableElement
     * @param typeElement
     * @return
     */
    private AnnotationMirror getAnnotationMirror(ExecutableElement executableElement, TypeElement typeElement) {
        for (AnnotationMirror mirror : executableElement.getAnnotationMirrors()) {
            {
                if (mirror.getAnnotationType().asElement().equals(typeElement)) {
                    return mirror;
                }
            }
        }
        return null;
    }
}
