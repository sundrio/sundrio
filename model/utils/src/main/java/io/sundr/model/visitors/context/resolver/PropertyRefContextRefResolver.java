package io.sundr.model.visitors.context.resolver;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import io.sundr.builder.Visitable;
import io.sundr.builder.Visitor;
import io.sundr.model.ClassRef;
import io.sundr.model.ContextRef;
import io.sundr.model.Expression;
import io.sundr.model.PropertyRefFluent;

/**
 * Visitor that resolves static class references where the scope of a PropertyRef is a ContextRef.
 * Converts PropertyRef(name="fieldName", scope=ContextRef("ClassName"))
 * to PropertyRef(name="fieldName", scope=ClassRef("fully.qualified.ClassName"))
 */
public class PropertyRefContextRefResolver implements Visitor<PropertyRefFluent<?>> {

  private final Set<ClassRef> imports;
  private final String currentPackage;

  public PropertyRefContextRefResolver(Set<String> imports, String currentPackage) {
    this.currentPackage = currentPackage;
    this.imports = imports.stream().map(ClassRef::forName).collect(Collectors.toSet());
  }

  @Override
  public void visit(PropertyRefFluent<?> fluent) {
    if (!(fluent instanceof Visitable)) {
      return;
    }

    Expression scope = fluent.buildScope();
    if (scope instanceof ContextRef) {
      ContextRef contextRef = (ContextRef) scope;
      String className = contextRef.getName();

      resolveClassName(className).ifPresent(resolvedClass -> {
        fluent.withScope(resolvedClass);
      });
    }
  }

  private Optional<ClassRef> resolveClassName(String className) {
    // First check explicit imports
    for (ClassRef importedClass : imports) {
      if (importedClass.getName().equals(className)) {
        return Optional.of(importedClass);
      }
    }

    // Check for java.lang classes (implicitly imported)
    if (isJavaLangClass(className)) {
      return Optional.of(ClassRef.forName("java.lang." + className));
    }

    // If the class name starts with uppercase, try resolving in current package
    if (Character.isUpperCase(className.charAt(0))) {
      String fullyQualifiedName = currentPackage != null && !currentPackage.isEmpty()
          ? currentPackage + "." + className
          : className;
      return Optional.of(ClassRef.forName(fullyQualifiedName));
    }

    return Optional.empty();
  }

  private boolean isJavaLangClass(String className) {
    // Common java.lang classes that are automatically imported
    return className.equals("System") || className.equals("String") || className.equals("Object") ||
        className.equals("Class") || className.equals("Thread") || className.equals("Math") ||
        className.equals("Integer") || className.equals("Long") || className.equals("Double") ||
        className.equals("Float") || className.equals("Boolean") || className.equals("Character") ||
        className.equals("Byte") || className.equals("Short") || className.equals("Number") ||
        className.equals("Throwable") || className.equals("Exception") || className.equals("RuntimeException") ||
        className.equals("Error") || className.equals("ClassLoader") || className.equals("Package") ||
        className.equals("Process") || className.equals("ProcessBuilder") || className.equals("Runtime") ||
        className.equals("SecurityManager") || className.equals("StringBuilder") || className.equals("StringBuffer");
  }
}
