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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Method extends ModifierSupport implements Renderable, Commentable, Annotatable {

  public static final String DEFAULT = "default";

  private final List<String> comments;
  private final List<AnnotationRef> annotations;
  private final List<TypeParamDef> parameters;
  private final String name;
  private final TypeRef returnType;
  private final List<Property> arguments;
  private final boolean varArgPreferred;
  private final List<ClassRef> exceptions;
  private final boolean defaultMethod;
  private final Block block;

  public Method(List<String> comments, List<AnnotationRef> annotations, List<TypeParamDef> parameters, String name,
      TypeRef returnType, List<Property> arguments, boolean varArgPreferred, List<ClassRef> exceptions, boolean defaultMethod,
      Block block, int modifiers, Map<AttributeKey, Object> attributes) {
    super(modifiers, attributes);
    this.comments = comments != null ? comments : Collections.<String> emptyList();
    this.annotations = annotations;
    this.parameters = parameters;
    this.name = name;
    this.returnType = returnType;
    this.arguments = arguments;
    this.varArgPreferred = varArgPreferred;
    this.exceptions = exceptions;
    this.defaultMethod = defaultMethod;
    this.block = block;
  }

  public List<String> getComments() {
    return comments;
  }

  public List<AnnotationRef> getAnnotations() {
    return annotations;
  }

  public boolean isVarArgPreferred() {
    return varArgPreferred;
  }

  public List<TypeParamDef> getParameters() {
    return parameters;
  }

  public String getName() {
    return name;
  }

  public TypeRef getReturnType() {
    return returnType;
  }

  public List<Property> getArguments() {
    return arguments;
  }

  public List<ClassRef> getExceptions() {
    return exceptions;
  }

  public boolean isDefaultMethod() {
    return this.defaultMethod;
  }

  public Block getBlock() {
    return block;
  }

  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new LinkedHashSet<ClassRef>();

    for (AnnotationRef annotationRef : annotations) {
      refs.addAll(annotationRef.getReferences());
    }

    if (returnType instanceof ClassRef) {
      ClassRef classRef = (ClassRef) returnType;
      refs.addAll(classRef.getReferences());
    }

    for (Property argument : arguments) {
      refs.addAll(argument.getReferences());
    }

    for (ClassRef e : exceptions) {
      refs.addAll(e.getReferences());
    }

    for (AnnotationRef a : getAnnotations()) {
      refs.addAll(a.getClassRef().getReferences());
    }

    for (TypeParamDef typeParamDef : parameters) {
      for (ClassRef bound : typeParamDef.getBounds()) {
        refs.addAll(bound.getReferences());
      }
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Method other = (Method) obj;
    if (arguments == null) {
      if (other.arguments != null)
        return false;
    } else if (!arguments.equals(other.arguments))
      return false;
    if (exceptions == null) {
      if (other.exceptions != null)
        return false;
    } else if (!exceptions.equals(other.exceptions))
      return false;
    if (modifiers != other.modifiers)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (parameters == null) {
      if (other.parameters != null)
        return false;
    } else if (!parameters.equals(other.parameters))
      return false;
    if (returnType == null) {
      if (other.returnType != null)
        return false;
    } else if (!returnType.equals(other.returnType))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((arguments == null) ? 0 : arguments.hashCode());
    result = prime * result + ((exceptions == null) ? 0 : exceptions.hashCode());
    result = prime * result + modifiers;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
    result = prime * result + ((returnType == null) ? 0 : returnType.hashCode());
    return result;
  }

  public String renderSignature(TypeDef enclosingType) {
    StringBuilder sb = new StringBuilder();
    renderSignature(sb, enclosingType);
    return sb.toString();
  }

  public void renderSignature(StringBuilder sb, TypeDef enclosingType) {
    if (isDefaultMethod()) {
      sb.append(DEFAULT).append(SPACE);
    } else {
      renderModifiers(sb);
    }

    if (parameters != null && !parameters.isEmpty()) {
      sb.append(LT);
      sb.append(parameters.stream().map(p -> p.render(enclosingType)).collect(Collectors.joining(COMA)));
      sb.append(GT);
    }

    if (name != null) {
      sb.append(returnType.render(enclosingType));
      sb.append(SPACE).append(name);
    } else if (enclosingType != null
        && enclosingType.getFullyQualifiedName().equals(((ClassRef) returnType).getFullyQualifiedName())) {
      //This is a constructor for a top-level class
      sb.append(enclosingType.getName());
    } else {
      //This is a constructor
      String fqcn = ((ClassRef) returnType).getFullyQualifiedName();
      String className = fqcn.substring(fqcn.lastIndexOf(".") + 1);
      sb.append(className);
    }

    sb.append(OP);
    if (!varArgPreferred) {
      sb.append(arguments.stream().map(a -> (String) a.withoutModiers().render(enclosingType))
          .collect(Collectors.joining(COMA)));
    } else if (!arguments.isEmpty()) {
      List<Property> args = arguments.subList(0, arguments.size() - 1);
      Property varArg = arguments.get(arguments.size() - 1);
      sb.append(args.stream().map(a -> a.render(enclosingType)).collect(Collectors.joining(COMA)));
      if (!args.isEmpty()) {
        sb.append(COMA);
      }
      if (varArg.getTypeRef().getDimensions() == 1) {
        sb.append(varArg.getTypeRef().withDimensions(0)).append(VARARG).append(SPACE);
      } else {
        sb.append(varArg.getTypeRef().render(enclosingType)).append(SPACE);
      }
      sb.append(varArg.getName());
    }
    sb.append(CP);

    if (exceptions != null && !exceptions.isEmpty()) {
      sb.append(SPACE).append(THROWS).append(SPACE)
          .append(exceptions.stream().map(e -> e.render(enclosingType)).collect(Collectors.joining(COMA)));
    }
  }

  /**
   *
   * @return
   */
  @Override
  public String render(TypeDef enclosingType) {
    StringBuilder sb = new StringBuilder();
    renderSignature(sb, enclosingType);
    boolean renderBody = isDefaultMethod() || (enclosingType != null && enclosingType.getKind() != Kind.INTERFACE);

    if (renderBody) {
      String indent = enclosingType != null && enclosingType.getOuterTypeName() != null ? "    " : "  ";
      sb.append(SPACE).append(OB).append(NEWLINE);
      if (getBlock() != null) {
        sb.append(getBlock().getStatements().stream().map(s -> indent + "  " + s + NEWLINE).collect(Collectors.joining()));
      }
      sb.append(indent).append(CB);
    } else {
      sb.append(SEMICOLN);
    }
    return sb.toString();
  }

  /**
   *
   * @return
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    if (!comments.isEmpty()) {
      sb.append(NEWLINE).append(OC).append(NEWLINE);
      for (String c : comments) {
        sb.append(SPACE).append(STAR).append(SPACE).append(c).append(NEWLINE);
      }
      sb.append(SPACE).append(CC).append(NEWLINE);
    }

    for (AnnotationRef annotationRef : annotations) {
      sb.append(annotationRef.toString()).append(SPACE);
    }

    if (isDefaultMethod()) {
      sb.append(DEFAULT).append(SPACE);
    } else {
      renderModifiers(sb);
    }

    if (parameters != null && !parameters.isEmpty()) {
      sb.append(LT);
      sb.append(parameters.stream().map(Object::toString).collect(Collectors.joining(COMA)));
      sb.append(GT);
    }

    if (name != null) {
      sb.append(returnType);
      sb.append(SPACE).append(name);
    } else {
      //This is a constructor
      String fqcn = ((ClassRef) returnType).getFullyQualifiedName();
      String className = fqcn.substring(fqcn.lastIndexOf(".") + 1);
      sb.append(className);
    }

    sb.append(OP);
    if (!varArgPreferred) {
      sb.append(arguments.stream().map(a -> (String) a.withoutModiers().toString())
          .collect(Collectors.joining(COMA)));
    } else if (!arguments.isEmpty()) {
      List<Property> args = arguments.subList(0, arguments.size() - 1);
      Property varArg = arguments.get(arguments.size() - 1);
      sb.append(args.stream().map(Object::toString).collect(Collectors.joining(COMA)));
      if (!args.isEmpty()) {
        sb.append(COMA);
      }
      if (varArg.getTypeRef().getDimensions() == 1) {
        sb.append(varArg.getTypeRef().withDimensions(0)).append(VARARG).append(SPACE);
      } else {
        sb.append(varArg.getTypeRef()).append(SPACE);
      }
      sb.append(varArg.getName());
    }
    sb.append(CP);

    if (exceptions != null && !exceptions.isEmpty()) {
      sb.append(SPACE).append(THROWS).append(SPACE)
          .append(exceptions.stream().map(Object::toString).collect(Collectors.joining(COMA)));
    }

    return sb.toString();
  }
}
