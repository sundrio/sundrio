package me.dsl.internal.processor;

import me.codegen.Type;
import me.codegen.utils.ModelUtils;
import me.dsl.annotations.Terminal;
import me.dsl.annotations.Transition;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TransitionSort {

    private final Elements elements;
    private final Types types;

    public TransitionSort(Elements elements, Types types) {
        this.elements = elements;
        this.types = types;
    }

    public Collection<ExecutableElement> sort(Collection<ExecutableElement> elements) {
        Set<ExecutableElement> sorted = new LinkedHashSet<>();
        Set<ExecutableElement> visited = new LinkedHashSet<>();
        for (ExecutableElement e : elements) {
            visit(e, visited, sorted);
        }
        return sorted;
    }


    private void visit(ExecutableElement element, Set<ExecutableElement> visited, Set<ExecutableElement> sorted) {
        if (!visited.add(element)) {
            return;
        }
        for (ExecutableElement e : collectDependencies(element)) {
            visit(e, visited, sorted);
        }
        sorted.add(element);
    }

    public Set<ExecutableElement> collectDependencies(ExecutableElement element) {
        Set<ExecutableElement> result = new LinkedHashSet<>();
        TypeElement classElement = ModelUtils.getClassElement(element);
        TypeElement transitionElement = elements.getTypeElement(Transition.class.getCanonicalName());
        TypeElement terminalElement = elements.getTypeElement(Terminal.class.getCanonicalName());
        List<ExecutableElement> transitionMethods = ElementFilter.methodsIn(transitionElement.getEnclosedElements());

        ExecutableElement toElement = transitionMethods.get(0);
        ExecutableElement anyElement = transitionMethods.get(1);
        AnnotationMirror transition = getAnnotationMirror(element, transitionElement);

        if (element.getAnnotation(Terminal.class) != null) {
            //Do nothing and return
        } else if (transition != null) {
            Iterable toMethods = transition.getElementValues().containsKey(toElement) ? (Iterable) transition.getElementValues().get(toElement).getValue() : Collections.emptyList();
            Iterable anyMethods = transition.getElementValues().containsKey(anyElement) ? (Iterable) transition.getElementValues().get(anyElement).getValue() : Collections.emptyList();

            for (Object m : toMethods) {
                for (ExecutableElement methodElement : ElementFilter.methodsIn(classElement.getEnclosedElements())) {
                    if (m.toString().replaceAll("\"", "").equals(methodElement.getSimpleName().toString())) {
                        result.add(methodElement);
                    }
                }
            }

            for (Object m : anyMethods) {
                String annotationClassName = m.toString();
                annotationClassName = annotationClassName.substring(0, annotationClassName.lastIndexOf("."));
                TypeElement annotationType = elements.getTypeElement(annotationClassName);
                for (ExecutableElement methodElement : ElementFilter.methodsIn(classElement.getEnclosedElements())) {
                    if (getAnnotationMirror(methodElement, annotationType) != null) {
                        result.add(methodElement);
                    }
                }
            }
        } else {
            for (ExecutableElement methodElement : ElementFilter.methodsIn(classElement.getEnclosedElements())) {
                if (getAnnotationMirror(methodElement, terminalElement) != null) {
                    result.add(methodElement);
                }
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
    private final AnnotationMirror getAnnotationMirror(ExecutableElement executableElement, TypeElement typeElement) {
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
