package me.dsl.internal.processor;

import me.dependency.TopologicalSort;
import me.dsl.internal.functions.ListTransitions;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class DependencyManager extends TopologicalSort<ExecutableElement> {
    
    public DependencyManager(Elements elements, Types types) {
        super(new ListTransitions(elements, types));
    }
}
