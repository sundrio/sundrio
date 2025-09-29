package io.sundr.utils;

import java.util.*;
import java.util.function.Function;

public final class Dependencies {

  private Dependencies() {
    // utility class, not instantiable
  }

  /** Static factory: create a new dependency tree */
  public static <T> DependencyTree<T> newTree() {
    return new DependencyTree<>();
  }

  /** Static factory with custom formatter */
  public static <T> DependencyTree<T> newTree(Function<T, String> formatter) {
    return new DependencyTree<>(formatter);
  }

  // ======================================================
  // Inner class DependencyTree<T>
  // ======================================================
  public static final class DependencyTree<T> {
    private final Map<T, LinkedHashSet<T>> graph = new LinkedHashMap<>();
    private final LinkedHashSet<T> allNodes = new LinkedHashSet<>();

    private Function<T, String> formatter = new Function<T, String>() {
      @Override
      public String apply(T t) {
        return String.valueOf(t);
      }
    };

    private DependencyTree() {
    }

    private DependencyTree(Function<T, String> formatter) {
      if (formatter != null) {
        this.formatter = formatter;
      }
    }

    /** Fluent API: add "item" with its direct dependencies. */
    @SafeVarargs
    public final DependencyTree<T> addDependency(T item, T... dependencies) {
      Objects.requireNonNull(item, "item");
      allNodes.add(item);
      graph.computeIfAbsent(item, k -> new LinkedHashSet<T>());

      if (dependencies != null) {
        for (T d : dependencies) {
          Objects.requireNonNull(d, "dependency");
          graph.get(item).add(d);
          allNodes.add(d);
          graph.computeIfAbsent(d, k -> new LinkedHashSet<T>());
        }
      }
      return this;
    }

    /** Set a custom formatter (optional). */
    public DependencyTree<T> withFormatter(Function<T, String> formatter) {
      this.formatter = (formatter != null) ? formatter : new Function<T, String>() {
        @Override
        public String apply(T t) {
          return String.valueOf(t);
        }
      };
      return this;
    }

    /** Get all nodes in the dependency tree. */
    public Set<T> getAllNodes() {
      return new LinkedHashSet<>(allNodes);
    }

    /** Render the whole dependency tree/forest */
    public String render() {
      List<T> roots = computeRoots();
      StringBuilder out = new StringBuilder();
      Set<T> rendered = new HashSet<T>();

      for (int i = 0; i < roots.size(); i++) {
        T root = roots.get(i);
        if (!rendered.add(root))
          continue;

        out.append(fmt(root)).append('\n');
        List<T> deps = new ArrayList<T>(graph.getOrDefault(root, new LinkedHashSet<T>()));
        renderChildren(out, deps, new ArrayDeque<T>(Collections.singletonList(root)), "", true);
      }

      // Fallback: no explicit roots, print everything once
      if (out.length() == 0 && !allNodes.isEmpty()) {
        for (T n : allNodes) {
          out.append(fmt(n)).append('\n');
          List<T> deps = new ArrayList<T>(graph.getOrDefault(n, new LinkedHashSet<T>()));
          renderChildren(out, deps, new ArrayDeque<T>(Collections.singletonList(n)), "", true);
        }
      }

      // trim last newline
      if (out.length() > 0 && out.charAt(out.length() - 1) == '\n') {
        out.setLength(out.length() - 1);
      }
      return out.toString();
    }

    /** Render a subtree starting from the given root */
    public String renderFrom(T root) {
      Objects.requireNonNull(root, "root");
      StringBuilder out = new StringBuilder();
      out.append(fmt(root)).append('\n');
      List<T> deps = new ArrayList<T>(graph.getOrDefault(root, new LinkedHashSet<T>()));
      renderChildren(out, deps, new ArrayDeque<T>(Collections.singletonList(root)), "", true);
      if (out.length() > 0 && out.charAt(out.length() - 1) == '\n') {
        out.setLength(out.length() - 1);
      }
      return out.toString();
    }

    // ---- internals ----
    private List<T> computeRoots() {
      LinkedHashSet<T> deps = new LinkedHashSet<T>();
      for (Map.Entry<T, LinkedHashSet<T>> e : graph.entrySet()) {
        deps.addAll(e.getValue());
      }
      LinkedHashSet<T> roots = new LinkedHashSet<T>(allNodes);
      roots.removeAll(deps);
      return new ArrayList<T>(roots);
    }

    private void renderChildren(StringBuilder out,
        List<T> children,
        Deque<T> path,
        String prefix,
        boolean parentIsLast) {
      for (int i = 0; i < children.size(); i++) {
        T child = children.get(i);
        boolean isLast = (i == children.size() - 1);

        String branch = isLast ? "\\- " : "+- ";
        String nextPrefix = prefix + (isLast ? "   " : "|  ");

        if (path.contains(child)) {
          out.append(prefix).append(branch).append(fmt(child)).append(" (cycle)").append('\n');
          continue;
        }

        out.append(prefix).append(branch).append(fmt(child)).append('\n');

        path.addLast(child);
        List<T> deps = new ArrayList<T>(graph.getOrDefault(child, new LinkedHashSet<T>()));
        renderChildren(out, deps, path, nextPrefix, isLast);
        path.removeLast();
      }
    }

    private String fmt(T t) {
      try {
        return formatter.apply(t);
      } catch (Exception e) {
        return String.valueOf(t);
      }
    }
  }
}
