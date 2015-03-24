package me.dsl.internal.processor;

import me.codegen.model.JavaClazzBuilder;
import me.dsl.annotations.EntryPoint;
import me.dsl.annotations.Keyword;
import me.dsl.annotations.Transition;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("me.dsl.annotations.Dsl")
public class DslProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();
        Types types = processingEnv.getTypeUtils();

        Set<TypeElement> customAnnotations = ElementFilter.typesIn(env.getElementsAnnotatedWith(Keyword.class));
        
        for (TypeElement annotation : annotations) {
            for (Element element : env.getElementsAnnotatedWith(annotation)) {
                if (element instanceof TypeElement) {
                    TypeElement typeElement = (TypeElement) element;
                    
                    JavaClazzBuilder builder = new JavaClazzBuilder();
                    
                    

                    List<ExecutableElement> entryPoints = new ArrayList<>();
                    List<ExecutableElement> terminals = new ArrayList<>();
                    
                    for (ExecutableElement executableElement : ElementFilter.methodsIn(typeElement.getEnclosedElements())){
                        
                        boolean hasCustomAnnotation = false;
                        
                        //Process custom annotations.
                        for (AnnotationMirror mirror : executableElement.getAnnotationMirrors()) {
                            hasCustomAnnotation = customAnnotations.contains(mirror.getAnnotationType().asElement());
                        }
                        
                        if (executableElement.getAnnotation(EntryPoint.class) != null) {
                            entryPoints.add(executableElement);
                        }

                        if (executableElement.getAnnotation(Keyword.class) != null) {

                        }

                        if (executableElement.getAnnotation(Transition.class) != null) {

                        }
                        
                        if (hasCustomAnnotation) {
                            
                        }
                    }
                }
            }
        }
        


        return true;
    }

}
