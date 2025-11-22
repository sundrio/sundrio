package io.sundr.adapter.source.change;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.source.utils.Sources;
import io.sundr.model.Field;
import io.sundr.model.Method;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;

/**
 * Tool for detecting changes between versions of source files or TypeDef objects.
 */
public class ChangeDetector {

  /**
   * Compares two TypeDef objects and returns the changes.
   */
  public static ChangeSet compare(TypeDef oldTypeDef, TypeDef newTypeDef) {
    Set<Change<Method>> methodChanges = compareMethodsAsSets(oldTypeDef, newTypeDef);
    Set<Change<Field>> fieldChanges = comparePropertiesAsSets(oldTypeDef, newTypeDef);

    return new ChangeSet(oldTypeDef, newTypeDef, methodChanges, fieldChanges);
  }

  /**
   * Compares two source files and returns the changes.
   */
  public static ChangeSet compare(Path oldFile, Path newFile) throws IOException {
    AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());

    TypeDef oldTypeDef = readTypeDefFromFile(oldFile, context);
    TypeDef newTypeDef = readTypeDefFromFile(newFile, context);

    return compare(oldTypeDef, newTypeDef);
  }

  /**
   * Compares old TypeDef with new source file.
   */
  public static ChangeSet compare(TypeDef oldTypeDef, Path newFile) throws IOException {
    AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());
    TypeDef newTypeDef = readTypeDefFromFile(newFile, context);

    return compare(oldTypeDef, newTypeDef);
  }

  /**
   * Compares old source file with new TypeDef.
   */
  public static ChangeSet compare(Path oldFile, TypeDef newTypeDef) throws IOException {
    AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());
    TypeDef oldTypeDef = readTypeDefFromFile(oldFile, context);

    return compare(oldTypeDef, newTypeDef);
  }

  private static Set<Change<Method>> compareMethodsAsSets(TypeDef oldTypeDef, TypeDef newTypeDef) {
    Set<Change<Method>> changes = new HashSet<>();

    // Handle null TypeDefs
    Map<String, Method> oldMethods = oldTypeDef != null ? oldTypeDef.getMethods().stream()
        .collect(Collectors.toMap(ChangeDetector::createMethodSignature, method -> method)) : Collections.emptyMap();

    Map<String, Method> newMethods = newTypeDef != null ? newTypeDef.getMethods().stream()
        .collect(Collectors.toMap(ChangeDetector::createMethodSignature, method -> method)) : Collections.emptyMap();

    // Find added methods
    for (Map.Entry<String, Method> entry : newMethods.entrySet()) {
      if (!oldMethods.containsKey(entry.getKey())) {
        changes.add(Change.added(entry.getValue()));
      }
    }

    // Find removed methods
    for (Map.Entry<String, Method> entry : oldMethods.entrySet()) {
      if (!newMethods.containsKey(entry.getKey())) {
        changes.add(Change.removed(entry.getValue()));
      }
    }

    // Find modified methods (same signature but different implementation)
    for (Map.Entry<String, Method> entry : oldMethods.entrySet()) {
      String signature = entry.getKey();
      Method oldMethod = entry.getValue();
      Method newMethod = newMethods.get(signature);

      if (newMethod != null && !methodsEqual(oldMethod, newMethod)) {
        changes.add(Change.modified(oldMethod, newMethod));
      }
    }

    return changes;
  }

  private static Set<Change<Field>> comparePropertiesAsSets(TypeDef oldTypeDef, TypeDef newTypeDef) {
    Set<Change<Field>> changes = new HashSet<>();

    // Handle null TypeDefs
    Map<String, Field> oldFields = oldTypeDef != null ? oldTypeDef.getFields().stream()
        .collect(Collectors.toMap(Field::getName, field -> field)) : Collections.emptyMap();

    Map<String, Field> newFields = newTypeDef != null ? newTypeDef.getFields().stream()
        .collect(Collectors.toMap(Field::getName, field -> field)) : Collections.emptyMap();

    // Find added fields
    for (Map.Entry<String, Field> entry : newFields.entrySet()) {
      if (!oldFields.containsKey(entry.getKey())) {
        changes.add(Change.added(entry.getValue()));
      }
    }

    // Find removed fields
    for (Map.Entry<String, Field> entry : oldFields.entrySet()) {
      if (!newFields.containsKey(entry.getKey())) {
        changes.add(Change.removed(entry.getValue()));
      }
    }

    // Find modified fields
    for (Map.Entry<String, Field> entry : oldFields.entrySet()) {
      String name = entry.getKey();
      Field oldField = entry.getValue();
      Field newField = newFields.get(name);

      if (newField != null && !fieldsEqual(oldField, newField)) {
        changes.add(Change.modified(oldField, newField));
      }
    }

    return changes;
  }

  private static String createMethodSignature(Method method) {
    StringBuilder signature = new StringBuilder();
    signature.append(method.getName()).append("(");

    method.getArguments().forEach(arg -> {
      signature.append(arg.getTypeRef().toString()).append(",");
    });

    signature.append(")");
    return signature.toString();
  }

  private static boolean methodsEqual(Method method1, Method method2) {
    // Compare method implementation details
    if (!Objects.equals(method1.getReturnType(), method2.getReturnType())) {
      return false;
    }

    if (!Objects.equals(method1.getModifiers(), method2.getModifiers())) {
      return false;
    }

    // Compare method body/block using render() method, ignoring comments and formatting
    // This is a pragmatic approach since Block objects may not have proper equals() implementation
    String block1Str = method1.getBlock() != null ? normalizeCode(method1.getBlock().render()) : null;
    String block2Str = method2.getBlock() != null ? normalizeCode(method2.getBlock().render()) : null;

    if (!Objects.equals(block1Str, block2Str)) {
      return false;
    }

    return true;
  }

  private static boolean fieldsEqual(Field field1, Field field2) {
    if (!Objects.equals(field1.getTypeRef(), field2.getTypeRef())) {
      return false;
    }

    if (!Objects.equals(field1.getModifiers(), field2.getModifiers())) {
      return false;
    }

    return true;
  }

  private static TypeDef readTypeDefFromFile(Path filePath, AdapterContext context) throws IOException {
    try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
      return Sources.readTypeDefFromStream(fis, context);
    }
  }

  /**
   * Normalizes code by removing comments and normalizing all formatting/whitespace.
   * This allows comparison to focus on actual code changes rather than stylistic differences.
   */
  private static String normalizeCode(String code) {
    if (code == null) {
      return null;
    }

    // Remove single-line comments (//)
    code = code.replaceAll("//.*?(?=\n|$)", "");

    // Remove multi-line comments (/* ... */) including nested ones
    code = code.replaceAll("/\\*.*?\\*/", "");

    // Normalize all whitespace: replace any whitespace sequence with single space
    code = code.replaceAll("\\s+", " ");

    // Remove leading/trailing whitespace
    code = code.trim();

    return code;
  }
}
