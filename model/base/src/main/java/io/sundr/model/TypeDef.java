/*
 * Copyright 2016 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.model;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TypeDef extends ModifierSupport implements Renderable, Nameable, Annotatable, Commentable, Mappable<TypeDef> {

  public static TypeDef OBJECT = new TypeDef(JAVA_LANG_OBJECT);
  public static TypeDef ENUM = new TypeDef(JAVA_LANG_ENUM);

  public static ClassRef OBJECT_REF = OBJECT.toReference();
  public static ClassRef ENUM_REF = ENUM.toReference();

  private final Kind kind;
  private final String packageName;
  private final String name;

  private final List<String> comments;
  private final List<AnnotationRef> annotations;
  private final List<ClassRef> extendsList;
  private final List<ClassRef> implementsList;
  private final List<TypeParamDef> parameters;

  private final List<Property> properties;
  private final List<Method> constructors;
  private final List<Method> methods;
  private final String outerTypeName;
  private final List<TypeDef> innerTypes;

  public TypeDef(Kind kind, String packageName, String name, List<String> comments, List<AnnotationRef> annotations,
      List<ClassRef> extendsList, List<ClassRef> implementsList, List<TypeParamDef> parameters, List<Property> properties,
      List<Method> constructors, List<Method> methods, String outerTypeName, List<TypeDef> innerTypes, int modifiers,
      Map<AttributeKey, Object> attributes) {
    super(modifiers, attributes);
    this.kind = kind != null ? kind : Kind.CLASS;
    this.packageName = packageName;
    this.name = name;
    this.comments = comments;
    this.annotations = annotations;
    this.extendsList = extendsList;
    this.implementsList = implementsList;
    this.parameters = parameters;
    this.properties = properties;
    this.methods = methods;
    this.outerTypeName = outerTypeName;
    //Adapters should be added last as the depend on previous values
    this.innerTypes = setOuterType(innerTypes, this);
    this.constructors = adaptConstructors(constructors, this);
  }

  protected TypeDef(String fullyQualifiedName) {
    super(Modifier.PUBLIC, Collections.emptyMap());
    this.kind = Kind.CLASS;
    this.name = Nameable.getClassName(fullyQualifiedName);
    this.packageName = Nameable.getPackageName(fullyQualifiedName);
    this.comments = Collections.emptyList();
    this.annotations = Collections.emptyList();
    this.extendsList = Collections.emptyList();
    this.implementsList = Collections.emptyList();
    this.parameters = Collections.emptyList();
    this.properties = Collections.emptyList();
    this.constructors = Collections.emptyList();
    this.methods = Collections.emptyList();
    this.innerTypes = Collections.emptyList();
    this.outerTypeName = Nameable.getOuterTypeName(fullyQualifiedName);
  }

  public static TypeDef forName(String fullyQualifiedName) {
    return new TypeDef(fullyQualifiedName);
  }

  /**
   * The method adapts constructor method to the current class. It unsets any name that may be
   * presetn in the method. It also sets as a return type a reference to the current type.
   */
  private static List<Method> adaptConstructors(List<Method> methods, TypeDef target) {
    List<Method> adapted = new ArrayList<Method>();
    for (Method m : methods) {
      adapted.add(new Method(m.getComments(), m.getAnnotations(), m.getParameters(), /* constructors don't have names */ null,
          target.toUnboundedReference(), m.getArguments(), m.isVarArgPreferred(), m.getExceptions(), false, m.getBlock(),
          m.getModifiers(), m.getAttributes()));
    }
    return adapted;
  }

  private static List<TypeDef> setOuterType(List<TypeDef> types, TypeDef outer) {
    if (types == null) {
      return Collections.emptyList();
    }

    List<TypeDef> updated = new ArrayList<TypeDef>();
    for (TypeDef typeDef : types) {
      if (outer.getFullyQualifiedName().equals(typeDef.getOuterTypeName())) {
        updated.add(typeDef);
      } else {
        updated.add(new TypeDef(typeDef.getKind(), outer.getPackageName(), typeDef.getName(), typeDef.getComments(),
            typeDef.getAnnotations(), typeDef.getExtendsList(), typeDef.getImplementsList(), typeDef.getParameters(),
            typeDef.getProperties(), typeDef.getConstructors(), typeDef.getMethods(), outer.getFullyQualifiedName(),
            typeDef.getInnerTypes(), typeDef.getModifiers(), typeDef.getAttributes()));
      }
    }
    return updated;
  }

  /**
   * Returns the fully qualified name of the type.
   */
  public String getFullyQualifiedName() {
    StringBuilder sb = new StringBuilder();
    if (packageName != null && !packageName.isEmpty() && (outerTypeName == null || outerTypeName.isEmpty())) {
      sb.append(getPackageName()).append(".");
    }

    if (outerTypeName != null) {
      sb.append(outerTypeName).append(".");
    }
    sb.append(getName());

    return sb.toString();
  }

  public Kind getKind() {
    return kind;
  }

  public List<String> getComments() {
    return comments;
  }

  public List<AnnotationRef> getAnnotations() {
    return annotations;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getName() {
    return name;
  }

  public List<ClassRef> getExtendsList() {
    return extendsList;
  }

  public List<ClassRef> getImplementsList() {
    return implementsList;
  }

  public List<TypeParamDef> getParameters() {
    return parameters;
  }

  public List<Property> getProperties() {
    return properties;
  }

  public List<Method> getConstructors() {
    return constructors;
  }

  public List<Method> getMethods() {
    return methods;
  }

  public String getOuterTypeName() {
    return outerTypeName;
  }

  public List<TypeDef> getInnerTypes() {
    return innerTypes;
  }

  public boolean isClass() {
    return kind == Kind.CLASS;
  }

  public boolean isInterface() {
    return kind == Kind.INTERFACE;
  }

  public boolean isEnum() {
    return kind == Kind.ENUM || (kind == Kind.CLASS && extendsList.contains(ENUM_REF));
  }

  public boolean isAnnotation() {
    return kind == Kind.ANNOTATION;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    TypeDef typeDef = (TypeDef) o;

    if (packageName != null ? !packageName.equals(typeDef.packageName) : typeDef.packageName != null)
      return false;
    if (outerTypeName != null ? !outerTypeName.equals(typeDef.outerTypeName) : typeDef.outerTypeName != null)
      return false;
    return name != null ? name.equals(typeDef.name) : typeDef.name == null;

  }

  @Override
  public int hashCode() {
    int result = packageName != null ? packageName.hashCode() : 0;
    result = 31 * result + (outerTypeName != null ? outerTypeName.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  /**
   * Creates a {@link ClassRef} for the current definition with the specified arguments.
   * 
   * @param arguments The arguments to be passed to the reference.
   * @return a {@link ClassRef} for the currnet type, with the specified type arguments
   */
  public ClassRef toReference(TypeRef... arguments) {
    return toReference(Arrays.asList(arguments));
  }

  /**
   * Creates a {@link ClassRef} for the current definition with the specified arguments.
   *
   * @param arguments The arguments to be passed to the reference.
   * @return a {@link ClassRef} for the currnet type, with the specified type arguments
   */
  public ClassRef toReference(List<TypeRef> arguments) {
    List<TypeRef> actualArguments = new ArrayList<TypeRef>();
    for (int i = 0; i < parameters.size(); i++) {
      if (i < arguments.size()) {
        actualArguments.add(arguments.get(i));
      } else {
        actualArguments.add(new WildcardRef());
      }
    }
    return new ClassRef(this.getFullyQualifiedName(), 0, actualArguments, getAttributes());
  }

  /**
   * Creates a {@link ClassRef} for the current definition with the specified arguments.
   *
   * @param arguments The arguments to be passed to the reference.
   * @return a {@link ClassRef} for the currnet type, with the specified type arguments
   */
  public ClassRef toReference(Collection<TypeRef> arguments) {
    return toReference(new ArrayList<>(arguments));
  }

  /**
   * Creates a {@link ClassRef} for internal use inside the scope of the type (methods, properties
   * etc). It uses as arguments the same 'letters' as the parameters definition.
   * 
   * @return a {@link ClassRef} for the currnet type, using the type parameter names as as arguments.
   */
  public ClassRef toInternalReference() {
    List<TypeRef> arguments = new ArrayList<TypeRef>();
    for (TypeParamDef parameter : parameters) {
      arguments.add(parameter.toReference());
    }

    return new ClassRef(getFullyQualifiedName(), 0, arguments, getAttributes());
  }

  /**
   * Creates a {@link ClassRef} without bounds.
   * 
   * @return a {@link ClassRef} for the currnet type, with the specified type arguments
   */
  public ClassRef toUnboundedReference() {
    return new ClassRef(getFullyQualifiedName(), 0, Collections.emptyList(), getAttributes());
  }

  public Set<String> getImports() {
    final Set<String> imports = new LinkedHashSet<String>();
    for (ClassRef ref : getReferenceMap().values()) {
      if (ref.getPackageName() == null ||
          ref.getPackageName().isEmpty() ||
          ref.getPackageName().equals(packageName) ||
          ref.getName().equals(name)) {
        continue;
      } else if (ref.getFullyQualifiedName().startsWith("com.ibm.jit.JITHelpers")
          || ref.getFullyQualifiedName().equals("java.lang.StringCompressionFlag")) {
        // When using openj9 these imports leak into the generated code, causing issues. Let's ignore them
        continue;
      } else {
        imports.add(ref.getFullyQualifiedName());
      }
    }
    return imports;
  }

  /**
   * Create a mapping from class name to {@link ClassRef}.
   */
  private Map<String, ClassRef> getReferenceMap() {
    Map<String, ClassRef> mapping = new HashMap<String, ClassRef>();
    List<ClassRef> refs = getReferences();

    //It's best to have predictable order, so that we can generate uniform code.
    Collections.sort(refs, new Comparator<ClassRef>() {
      @Override
      public int compare(ClassRef o1, ClassRef o2) {
        return o1.getFullyQualifiedName().compareTo(o2.getFullyQualifiedName());
      }
    });

    for (ClassRef ref : refs) {
      String key = ref.getName();
      if (!mapping.containsKey(key)) {
        mapping.put(key, ref);
      }
    }
    return mapping;
  }

  public List<ClassRef> getReferences() {
    final List<ClassRef> refs = new ArrayList<ClassRef>();

    for (AnnotationRef a : annotations) {
      refs.addAll(a.getReferences());
    }

    for (ClassRef i : implementsList) {
      refs.addAll(i.getReferences());
    }

    for (ClassRef e : extendsList) {
      refs.addAll(e.getReferences());
    }

    for (Property property : properties) {
      refs.addAll(property.getReferences());
    }

    for (Method method : constructors) {
      refs.addAll(method.getReferences());
    }

    for (Method method : methods) {
      refs.addAll(method.getReferences());
    }

    for (TypeParamDef typeParamDef : parameters) {
      for (ClassRef bound : typeParamDef.getBounds()) {
        refs.addAll(bound.getReferences());
      }
    }

    for (TypeDef innerType : innerTypes) {
      refs.addAll(innerType.getReferences());
    }

    if (getAttributes().containsKey(ALSO_IMPORT)) {
      Object obj = getAttributes().get(ALSO_IMPORT);
      if (obj instanceof ClassRef) {
        refs.add((ClassRef) obj);
      } else if (obj instanceof Collection) {
        refs.addAll((Collection<? extends ClassRef>) obj);
      }
    }
    return refs;
  }

  public String renderDefinition() {
    StringBuilder sb = new StringBuilder();
    renderDefinition(sb);
    return sb.toString();
  }

  public void renderDefinition(StringBuilder sb) {
    renderModifiers(sb);

    sb.append(kind.name().toLowerCase()).append(SPACE);
    sb.append(name);

    if (parameters != null && !parameters.isEmpty()) {
      sb.append(LT);
      sb.append(parameters.stream().map(p -> p.render(this)).collect(Collectors.joining(COMA)));
      sb.append(GT);
    }

    if (extendsList != null && !extendsList.isEmpty()
        && (extendsList.size() != 1 || !extendsList.contains(OBJECT.toReference()))) {
      sb.append(SPACE).append(EXTENDS).append(SPACE);
      sb.append(extendsList.stream().map(e -> e.render(this)).collect(Collectors.joining(COMA)));
    }

    if (implementsList != null && !implementsList.isEmpty()) {
      sb.append(SPACE).append(IMPLEMENTS).append(SPACE);
      sb.append(implementsList.stream().map(i -> i.render(this)).collect(Collectors.joining(COMA)));
    }
  }

  @Override
  public String render() {
    StringBuilder tb = new StringBuilder(); // Top StringBuffer: (package and imports).
    StringBuilder sb = new StringBuilder();
    String indent = outerTypeName == null ? "  " : "    ";
    String halfIndent = outerTypeName == null ? "" : "  ";

    // We only need to render those for the outermost type
    if (outerTypeName == null) {
      tb.append("package ").append(getPackageName()).append(SEMICOLN).append(NEWLINE);
      tb.append(NEWLINE);
      for (String i : getImports()) {
        tb.append("import ").append(i).append(SEMICOLN).append(NEWLINE);
      }
    }

    if (comments != null && !comments.isEmpty()) {
      sb.append(renderComments(SPACE));
    }

    if (annotations != null && !annotations.isEmpty()) {
      sb.append(renderAnnotations(indent));
    }

    renderDefinition(sb);
    sb.append(OB).append(NEWLINE).append(indent);

    if (kind != Kind.INTERFACE) {
      for (Method constructors : getConstructors()) {
        sb.append(constructors.renderComments(indent));
        sb.append(constructors.renderAnnotations(indent));
        sb.append(constructors.render(this));
        sb.append(NEWLINE).append(indent);
      }

      for (Property field : getProperties()) {
        sb.append(field.renderComments(indent));
        sb.append(field.renderAnnotations(indent));
        sb.append(field.render(this));
        if (field.getAttribute(INIT) != null) {
          sb.append(" = ").append(field.getDefaultValue());
        }

        sb.append(SEMICOLN).append(NEWLINE).append(indent);
      }
    }

    for (Method method : getMethods()) {
      sb.append(method.renderComments(indent));
      sb.append(method.renderAnnotations(indent));
      sb.append(method.render(this));
      sb.append(NEWLINE).append(indent);
    }

    for (TypeDef innerType : innerTypes) {
      sb.append(innerType.render());
      sb.append(NEWLINE).append(indent);
    }

    sb.append(NEWLINE).append(halfIndent).append(CB);
    String top = tb.toString();
    String content = sb.toString();
    for (ClassRef ref : getReferences()) {
      if (top.contains("import " + ref.getFullyQualifiedName() + ";") || ref.getPackageName().equals(getPackageName())) {
        content = content.replaceFirst(Pattern.quote(ref.getFullyQualifiedName()), ref.getName());
      }
    }
    return top + content;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (AnnotationRef annotationRef : annotations) {
      sb.append(annotationRef.toString()).append(SPACE);
    }

    renderModifiers(sb);

    sb.append(kind.name().toLowerCase()).append(SPACE);
    sb.append(name);

    if (parameters != null && !parameters.isEmpty()) {
      sb.append(LT);
      sb.append(parameters.stream().map(Object::toString).collect(Collectors.joining(COMA)));
      sb.append(GT);
    }

    if (extendsList != null && !extendsList.isEmpty()
        && (extendsList.size() != 1 || !extendsList.contains(OBJECT.toReference()))) {
      sb.append(SPACE).append(EXTENDS).append(SPACE);
      sb.append(extendsList.stream().map(Object::toString).collect(Collectors.joining(COMA)));
    }

    if (implementsList != null && !implementsList.isEmpty()) {
      sb.append(SPACE).append(IMPLEMENTS).append(SPACE);
      sb.append(implementsList.stream().map(Object::toString).collect(Collectors.joining(COMA)));
    }
    return sb.toString();
  }
}
