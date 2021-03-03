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

package io.sundr.codegen.model;

import static io.sundr.codegen.utils.StringUtils.join;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.sundr.codegen.utils.StringUtils;

public class Method extends ModifierSupport {

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
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Method method = (Method) o;

    if (parameters != null ? !parameters.equals(method.parameters) : method.parameters != null)
      return false;
    if (name != null ? !name.equals(method.name) : method.name != null)
      return false;
    return arguments != null ? arguments.equals(method.arguments) : method.arguments == null;

  }

  @Override
  public int hashCode() {
    int result = parameters != null ? parameters.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
    return result;
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

    if (isPublic()) {
      sb.append(PUBLIC).append(SPACE);
    } else if (isProtected()) {
      sb.append(PROTECTED).append(SPACE);
    } else if (isPrivate()) {
      sb.append(PRIVATE).append(SPACE);
    } else if (isDefaultMethod()) {
      sb.append(DEFAULT).append(SPACE);
    }

    if (isSynchronized()) {
      sb.append(SYNCHRONIZED).append(SPACE);
    }

    if (isStatic()) {
      sb.append(STATIC).append(SPACE);
    }

    if (isAbstract()) {
      sb.append(ABSTRACT).append(SPACE);
    }

    if (isFinal()) {
      sb.append(FINAL).append(SPACE);
    }

    if (parameters != null && !parameters.isEmpty()) {
      sb.append(LT);
      sb.append(StringUtils.join(parameters, COMA));
      sb.append(GT);
    }

    if (name != null) {
      sb.append(returnType);
      sb.append(SPACE).append(name);
    } else {
      //This is a constructor
      sb.append(((ClassRef) returnType).getDefinition().getName());
    }

    sb.append(OP);
    if (!varArgPreferred) {
      sb.append(StringUtils.join(arguments, COMA));
    } else if (!arguments.isEmpty()) {
      List<Property> args = arguments.subList(0, arguments.size() - 1);
      Property varArg = arguments.get(arguments.size() - 1);
      sb.append(StringUtils.join(args, COMA));
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
      sb.append(SPACE).append(THROWS).append(SPACE).append(join(exceptions, COMA));
    }

    return sb.toString();
  }
}
