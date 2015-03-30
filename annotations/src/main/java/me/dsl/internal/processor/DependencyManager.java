package me.dsl.internal.processor;

import me.dependency.TopologicalSort;
import me.dsl.internal.functions.FindTransitions;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.Elements;

public class DependencyManager extends TopologicalSort<ExecutableElement> {
    
    public DependencyManager(Elements elements) {
        super(new FindTransitions(elements));
    }
}
