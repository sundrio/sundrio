package me.dependency;

import me.Function;

import java.util.LinkedHashSet;
import java.util.Set;

public class TopologicalSort<T> {

    private final Function<T, Set<T>> collectDepenedencies;

    public TopologicalSort(Function<T, Set<T>> collectDepenedencies) {
        this.collectDepenedencies = collectDepenedencies;
    }

    public Set<T> sort(Iterable<T> items) {
        Set<T> sorted = new LinkedHashSet<>();
        Set<T> visited = new LinkedHashSet<>();
        for (T e : items) {
            visit(e, visited, sorted);
        }
        return sorted;
    }


    public void visit(T item, Set<T> visited, Set<T> sorted) {
        if (!visited.add(item)) {
            return;
        }
        for (T t : collectDepenedencies.apply(item)) {
            visit(t, visited, sorted);
        }
        sorted.add(item);
    }
    
    public Set<T> collectDependencies(T item) {
        return collectDepenedencies.apply(item);
    }
}
