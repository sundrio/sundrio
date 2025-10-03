package io.sundr.utils;

import java.util.*;
import java.util.function.Function;

/**
 * Utility class for building and rendering dependency trees.
 * Provides functionality to track dependencies between items and visualize them
 * in a tree format with support for cycle detection.
 */
public final class Dependencies {

  private Dependencies() {
    // utility class, not instantiable
  }

  /**
   * Creates a new dependency tree with default formatting.
   *
   * @param <T> the type of items in the dependency tree
   * @return a new DependencyTree instance
   */
  public static <T> DependencyTree<T> newTree() {
    return new DependencyTree<>();
  }

  /**
   * Creates a new dependency tree with a custom formatter.
   *
   * @param <T> the type of items in the dependency tree
   * @param formatter function to convert items to string representation
   * @return a new DependencyTree instance with the specified formatter
   */
  public static <T> DependencyTree<T> newTree(Function<T, String> formatter) {
    return new DependencyTree<>(formatter);
  }

  // ======================================================
  // Inner class DependencyTree<T>
  // ======================================================
  /**
   * A dependency tree that tracks relationships between items and can render them
   * in a visual tree format. Supports cycle detection and custom formatting.
   *
   * @param <T> the type of items in this dependency tree
   */
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

    /**
     * Adds an item with its direct dependencies to the tree.
     *
     * @param item the item to add
     * @param dependencies the direct dependencies of the item
     * @return this DependencyTree for method chaining
     * @throws NullPointerException if item is null
     */
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

    /**
     * Sets a custom formatter for rendering items.
     *
     * @param formatter function to convert items to string representation
     * @return this DependencyTree for method chaining
     */
    public DependencyTree<T> withFormatter(Function<T, String> formatter) {
      this.formatter = (formatter != null) ? formatter : new Function<T, String>() {
        @Override
        public String apply(T t) {
          return String.valueOf(t);
        }
      };
      return this;
    }

    /**
     * Gets all nodes in the dependency tree.
     *
     * @return a set containing all nodes that have been added to this tree
     */
    public Set<T> getAllNodes() {
      return new LinkedHashSet<>(allNodes);
    }

    /**
     * Renders the entire dependency tree as a formatted string.
     * Shows all root nodes and their dependencies in tree format.
     *
     * @return a string representation of the dependency tree
     */
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

    /**
     * Renders a subtree starting from the specified root node.
     *
     * @param root the root node to start rendering from
     * @return a string representation of the subtree
     * @throws NullPointerException if root is null
     */
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
