/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.adapter.source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.AnnotationRef;
import io.sundr.model.Argument;
import io.sundr.model.ArgumentBuilder;
import io.sundr.model.AttributeKey;
import io.sundr.model.Attributeable;
import io.sundr.model.Block;
import io.sundr.model.ClassRef;
import io.sundr.model.Field;
import io.sundr.model.FieldBuilder;
import io.sundr.model.Kind;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Modifiers;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeRef;
import io.sundr.model.utils.Types;
import io.sundr.model.visitors.context.resolver.TypeDefContextRefResolver;

public class TypeDeclarationToTypeDef implements Function<TypeDeclaration, TypeDef> {

  private static final Function<Node, String> PACKAGENAME = new NodeToPackage();
  private static final Function<Node, Set<ClassRef>> IMPORTS = new NodeToImports();
  private static final Function<AnnotationExpr, AnnotationRef> ANNOTATIONREF = new AnnotationExprToAnnotationRef();
  private static final Function<BlockStmt, Block> BLOCK = new BlockStmtToBlock();

  private final AdapterContext context;
  private final Function<TypeParameter, TypeParamDef> typeParameterToTypeParamDef;
  private final Function<ClassOrInterfaceType, TypeRef> classOrInterfaceToTypeRef;
  private final Function<Type, TypeRef> typeToTypeRef;

  public TypeDeclarationToTypeDef(AdapterContext context) {
    this.context = context;
    this.classOrInterfaceToTypeRef = new ClassOrInterfaceToTypeRef();
    this.typeToTypeRef = new TypeToTypeRef(classOrInterfaceToTypeRef);
    this.typeParameterToTypeParamDef = new TypeParameterToTypeParamDef(classOrInterfaceToTypeRef);
  }

  @Override
  public TypeDef apply(TypeDeclaration type) {
    if (type instanceof ClassOrInterfaceDeclaration) {
      ClassOrInterfaceDeclaration decl = (ClassOrInterfaceDeclaration) type;
      Kind kind = decl.isInterface() ? Kind.INTERFACE : Kind.CLASS;

      List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();

      List<ClassRef> extendsList = new ArrayList<ClassRef>();
      List<ClassRef> implementsList = new ArrayList<ClassRef>();
      List<Field> fields = new ArrayList<Field>();
      List<Method> methods = new ArrayList<Method>();
      List<Method> constructors = new ArrayList<Method>();
      List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();

      for (AnnotationExpr annotationExpr : decl.getAnnotations()) {
        annotations.add(ANNOTATIONREF.apply(annotationExpr));
      }

      for (TypeParameter typeParameter : decl.getTypeParameters()) {
        parameters.add(typeParameterToTypeParamDef.apply(typeParameter));
      }

      for (ClassOrInterfaceType classOrInterfaceType : decl.getExtendedTypes()) {
        extendsList.add((ClassRef) classOrInterfaceToTypeRef.apply(classOrInterfaceType));
      }

      for (ClassOrInterfaceType classOrInterfaceType : decl.getImplementedTypes()) {
        implementsList.add((ClassRef) classOrInterfaceToTypeRef.apply(classOrInterfaceType));
      }

      for (BodyDeclaration bodyDeclaration : decl.getMembers()) {
        if (bodyDeclaration instanceof FieldDeclaration) {
          FieldDeclaration fieldDeclaration = (FieldDeclaration) bodyDeclaration;
          for (VariableDeclarator var : fieldDeclaration.getVariables()) {
            TypeRef fieldDeclRef = typeToTypeRef.apply(var.getType());
            TypeRef typeRef = checkAgainstTypeParamRef(fieldDeclRef, parameters);

            List<AnnotationRef> fieldAnnotations = new ArrayList<AnnotationRef>();
            for (AnnotationExpr annotationExpressions : fieldDeclaration.getAnnotations()) {
              fieldAnnotations.add(ANNOTATIONREF.apply(annotationExpressions));
            }

            fields.add(new FieldBuilder().withName(var.getNameAsString()).withTypeRef(typeRef)
                .withAnnotations(fieldAnnotations)
                .withModifiers(Modifiers.from(convertModifiers(fieldDeclaration.getModifiers())))
                .addToAttributes(Attributeable.INIT, var.getInitializer().map(e -> e.toString()).orElse(null))
                .build());
          }
        } else if (bodyDeclaration instanceof MethodDeclaration) {
          MethodDeclaration methodDeclaration = (MethodDeclaration) bodyDeclaration;
          List<Argument> arguments = new ArrayList<Argument>();
          List<ClassRef> exceptions = new ArrayList<ClassRef>();
          List<AnnotationRef> methodAnnotations = new ArrayList<AnnotationRef>();
          for (AnnotationExpr annotationExpr : methodDeclaration.getAnnotations()) {
            methodAnnotations.add(ANNOTATIONREF.apply(annotationExpr));
          }
          for (ReferenceType referenceType : methodDeclaration.getThrownExceptions()) {
            TypeRef exceptionRef = typeToTypeRef.apply(referenceType);
            if (exceptionRef instanceof ClassRef) {
              exceptions.add((ClassRef) exceptionRef);
            }
          }
          Boolean preferVarArg = false;

          for (Parameter parameter : methodDeclaration.getParameters()) {
            List<AnnotationRef> paramAnnotations = new ArrayList<AnnotationRef>();
            for (AnnotationExpr annotationExpr : parameter.getAnnotations()) {
              paramAnnotations.add(ANNOTATIONREF.apply(annotationExpr));
            }

            TypeRef typeRef = typeToTypeRef.apply(parameter.getType());

            if (parameter.isVarArgs()) {
              preferVarArg = true;
              typeRef = typeRef.withDimensions(typeRef.getDimensions() + 1);
            }

            arguments.add(new ArgumentBuilder().withName(parameter.getNameAsString()).withTypeRef(typeRef)
                .withAnnotations(paramAnnotations).build());
          }

          List<TypeParamDef> typeParamDefs = new ArrayList<TypeParamDef>();
          for (TypeParameter typeParameter : methodDeclaration.getTypeParameters()) {
            typeParamDefs.add(typeParameterToTypeParamDef.apply(typeParameter));
          }

          TypeRef returnType = checkAgainstTypeParamRef(typeToTypeRef.apply(methodDeclaration.getType()), parameters);
          methods.add(new MethodBuilder().withName(methodDeclaration.getNameAsString())
              .withDefaultMethod(methodDeclaration.isDefault())
              .withModifiers(Modifiers.from(convertModifiers(methodDeclaration.getModifiers())))
              .withParameters(typeParamDefs).withVarArgPreferred(preferVarArg).withReturnType(returnType)
              .withExceptions(exceptions).withArguments(arguments).withAnnotations(methodAnnotations)
              .withBlock(methodDeclaration.getBody().map(BLOCK).orElse(null)).build());

        } else if (bodyDeclaration instanceof ConstructorDeclaration) {
          ConstructorDeclaration constructorDeclaration = (ConstructorDeclaration) bodyDeclaration;
          List<Argument> arguments = new ArrayList<Argument>();
          List<ClassRef> exceptions = new ArrayList<ClassRef>();
          List<AnnotationRef> ctorAnnotations = new ArrayList<AnnotationRef>();
          Boolean preferVarArg = false;

          for (AnnotationExpr annotationExpr : constructorDeclaration.getAnnotations()) {
            ctorAnnotations.add(ANNOTATIONREF.apply(annotationExpr));
          }
          for (ReferenceType referenceType : constructorDeclaration.getThrownExceptions()) {
            TypeRef exceptionRef = typeToTypeRef.apply(referenceType);
            exceptions.add((ClassRef) exceptionRef);
          }
          for (Parameter parameter : constructorDeclaration.getParameters()) {
            List<AnnotationRef> ctorParamAnnotations = new ArrayList<AnnotationRef>();
            for (AnnotationExpr annotationExpr : parameter.getAnnotations()) {
              ctorParamAnnotations.add(ANNOTATIONREF.apply(annotationExpr));
            }
            TypeRef typeRef = checkAgainstTypeParamRef(typeToTypeRef.apply(parameter.getType()), parameters);

            if (parameter.isVarArgs()) {
              preferVarArg = true;
              typeRef = typeRef.withDimensions(typeRef.getDimensions() + 1);
            }

            arguments.add(new ArgumentBuilder().withName(parameter.getNameAsString()).withTypeRef(typeRef)
                .withAnnotations(ctorParamAnnotations).build());
          }
          constructors
              .add(new MethodBuilder().withModifiers(Modifiers.from(convertModifiers(constructorDeclaration.getModifiers())))
                  .withVarArgPreferred(preferVarArg).withExceptions(exceptions)
                  .withArguments(arguments).withAnnotations(ctorAnnotations)
                  .withBlock(BLOCK.apply(constructorDeclaration.getBody())).build());
        }
      }

      return context.getDefinitionRepository()
          .register(
              new TypeDefBuilder().withKind(kind).withPackageName(PACKAGENAME.apply(type)).withName(decl.getNameAsString())
                  .withModifiers(Modifiers.from(convertModifiers(type.getModifiers()))).withParameters(parameters)
                  .withExtendsList(extendsList)
                  .withImplementsList(implementsList).withFields(fields).withMethods(methods)
                  .withConstructors(constructors).withAnnotations(annotations)
                  .addToAttributes(TypeDef.ALSO_IMPORT, IMPORTS.apply(type))
                  .accept(new TypeDefContextRefResolver()).build());
    }

    if (type instanceof AnnotationDeclaration) {
      AnnotationDeclaration decl = (AnnotationDeclaration) type;
      Kind kind = Kind.ANNOTATION;
      List<Method> methods = new ArrayList<Method>();

      for (BodyDeclaration bodyDeclaration : decl.getMembers()) {
        if (bodyDeclaration instanceof AnnotationMemberDeclaration) {
          Map<AttributeKey, Object> attributes = new HashMap<>();
          AnnotationMemberDeclaration annotationMemberDeclaration = (AnnotationMemberDeclaration) bodyDeclaration;
          annotationMemberDeclaration.getDefaultValue()
              .ifPresent(v -> attributes.put(Attributeable.DEFAULT_VALUE, v.toString()));
          TypeRef returnType = typeToTypeRef.apply(annotationMemberDeclaration.getType());
          methods.add(new MethodBuilder().withName(annotationMemberDeclaration.getNameAsString())
              .withModifiers(Modifiers.from(convertModifiers(annotationMemberDeclaration.getModifiers())))
              .withReturnType(returnType)
              .withAttributes(attributes)
              .build());
        }
      }

      List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
      for (AnnotationExpr annotationExpr : decl.getAnnotations()) {
        annotations.add(ANNOTATIONREF.apply(annotationExpr));
      }

      return context.getDefinitionRepository()
          .register(
              new TypeDefBuilder().withKind(kind).withPackageName(PACKAGENAME.apply(type)).withName(decl.getNameAsString())
                  .withModifiers(Modifiers.from(convertModifiers(type.getModifiers()))).withMethods(methods)
                  .withAnnotations(annotations)
                  .addToAttributes(TypeDef.ALSO_IMPORT, IMPORTS.apply(type))
                  .accept(new TypeDefContextRefResolver()).build());
    }

    if (type instanceof EnumDeclaration) {
      EnumDeclaration decl = (EnumDeclaration) type;
      Kind kind = Kind.ENUM;

      List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
      List<ClassRef> implementsList = new ArrayList<ClassRef>();
      List<Field> fields = new ArrayList<Field>();
      List<Method> methods = new ArrayList<Method>();
      List<Method> constructors = new ArrayList<Method>();
      List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();

      for (AnnotationExpr annotationExpr : decl.getAnnotations()) {
        annotations.add(ANNOTATIONREF.apply(annotationExpr));
      }

      for (ClassOrInterfaceType classOrInterfaceType : decl.getImplementedTypes()) {
        implementsList.add((ClassRef) classOrInterfaceToTypeRef.apply(classOrInterfaceType));
      }

      // Handle enum constants as properties
      for (EnumConstantDeclaration enumConstant : decl.getEntries()) {
        List<AnnotationRef> enumAnnotations = new ArrayList<AnnotationRef>();
        for (AnnotationExpr annotationExpr : enumConstant.getAnnotations()) {
          enumAnnotations.add(ANNOTATIONREF.apply(annotationExpr));
        }

        // Create a property for each enum constant
        String enumTypeName = PACKAGENAME.apply(type) + "." + decl.getNameAsString();
        ClassRef enumType = new ClassRef(enumTypeName, 0, new ArrayList<>(), new HashMap<>());
        fields.add(new FieldBuilder().withName(enumConstant.getNameAsString())
            .withTypeRef(enumType)
            .withAnnotations(enumAnnotations)
            .build());
      }

      // Handle fields and methods without body processing to avoid converter dependency issues
      for (BodyDeclaration bodyDeclaration : decl.getMembers()) {
        if (bodyDeclaration instanceof FieldDeclaration) {
          FieldDeclaration fieldDeclaration = (FieldDeclaration) bodyDeclaration;
          for (VariableDeclarator var : fieldDeclaration.getVariables()) {
            TypeRef fieldDeclRef = typeToTypeRef.apply(var.getType());
            TypeRef typeRef = checkAgainstTypeParamRef(fieldDeclRef, parameters);

            List<AnnotationRef> fieldAnnotations = new ArrayList<AnnotationRef>();
            for (AnnotationExpr annotationExpressions : fieldDeclaration.getAnnotations()) {
              fieldAnnotations.add(ANNOTATIONREF.apply(annotationExpressions));
            }

            fields.add(new FieldBuilder().withName(var.getNameAsString()).withTypeRef(typeRef)
                .withAnnotations(fieldAnnotations)
                .withModifiers(Modifiers.from(convertModifiers(fieldDeclaration.getModifiers())))
                .build());
          }
        } else if (bodyDeclaration instanceof MethodDeclaration) {
          MethodDeclaration methodDeclaration = (MethodDeclaration) bodyDeclaration;
          List<Argument> arguments = new ArrayList<Argument>();
          List<ClassRef> exceptions = new ArrayList<ClassRef>();
          List<AnnotationRef> methodAnnotations = new ArrayList<AnnotationRef>();

          for (AnnotationExpr annotationExpr : methodDeclaration.getAnnotations()) {
            methodAnnotations.add(ANNOTATIONREF.apply(annotationExpr));
          }

          for (ReferenceType referenceType : methodDeclaration.getThrownExceptions()) {
            TypeRef exceptionRef = typeToTypeRef.apply(referenceType);
            if (exceptionRef instanceof ClassRef) {
              exceptions.add((ClassRef) exceptionRef);
            }
          }

          Boolean preferVarArg = false;
          for (Parameter parameter : methodDeclaration.getParameters()) {
            List<AnnotationRef> paramAnnotations = new ArrayList<AnnotationRef>();
            for (AnnotationExpr annotationExpr : parameter.getAnnotations()) {
              paramAnnotations.add(ANNOTATIONREF.apply(annotationExpr));
            }

            TypeRef typeRef = typeToTypeRef.apply(parameter.getType());

            if (parameter.isVarArgs()) {
              preferVarArg = true;
              typeRef = typeRef.withDimensions(typeRef.getDimensions() + 1);
            }

            arguments.add(new ArgumentBuilder().withName(parameter.getNameAsString()).withTypeRef(typeRef)
                .withAnnotations(paramAnnotations).build());
          }

          List<TypeParamDef> typeParamDefs = new ArrayList<TypeParamDef>();
          for (TypeParameter typeParameter : methodDeclaration.getTypeParameters()) {
            typeParamDefs.add(typeParameterToTypeParamDef.apply(typeParameter));
          }

          TypeRef returnType = checkAgainstTypeParamRef(typeToTypeRef.apply(methodDeclaration.getType()), parameters);
          methods.add(new MethodBuilder().withName(methodDeclaration.getNameAsString())
              .withDefaultMethod(methodDeclaration.isDefault())
              .withModifiers(Modifiers.from(convertModifiers(methodDeclaration.getModifiers())))
              .withParameters(typeParamDefs).withVarArgPreferred(preferVarArg).withReturnType(returnType)
              .withExceptions(exceptions).withArguments(arguments).withAnnotations(methodAnnotations)
              .build()); // No method body to avoid converter dependency issues
        }
      }

      return context.getDefinitionRepository()
          .register(
              new TypeDefBuilder().withKind(kind).withPackageName(PACKAGENAME.apply(type)).withName(decl.getNameAsString())
                  .withModifiers(Modifiers.from(convertModifiers(type.getModifiers()))).withParameters(parameters)
                  .withImplementsList(implementsList).withFields(fields).withMethods(methods)
                  .withConstructors(constructors).withAnnotations(annotations)
                  .addToAttributes(TypeDef.ALSO_IMPORT, IMPORTS.apply(type))
                  .accept(new TypeDefContextRefResolver()).build());
    }
    throw new IllegalArgumentException("Unsupported TypeDeclaration:[" + type + "].");
  }

  //To be more accurate we need to check if there is a matching type parameter definition
  //and if so, return a reference to that (rather than consider it a class).
  private TypeRef checkAgainstTypeParamRef(TypeRef typeRef, Collection<TypeParamDef> parameters) {
    TypeParamDef parameterDef = Types.getParameterDefinition(typeRef, parameters);
    if (parameterDef != null) {
      return parameterDef.toReference();
    }
    return typeRef;
  }

  private static int convertModifiers(NodeList<Modifier> modifiers) {
    int result = 0;
    for (Modifier modifier : modifiers) {
      switch (modifier.getKeyword()) {
        case PUBLIC:
          result |= java.lang.reflect.Modifier.PUBLIC;
          break;
        case PRIVATE:
          result |= java.lang.reflect.Modifier.PRIVATE;
          break;
        case PROTECTED:
          result |= java.lang.reflect.Modifier.PROTECTED;
          break;
        case STATIC:
          result |= java.lang.reflect.Modifier.STATIC;
          break;
        case FINAL:
          result |= java.lang.reflect.Modifier.FINAL;
          break;
        case ABSTRACT:
          result |= java.lang.reflect.Modifier.ABSTRACT;
          break;
        case SYNCHRONIZED:
          result |= java.lang.reflect.Modifier.SYNCHRONIZED;
          break;
        case NATIVE:
          result |= java.lang.reflect.Modifier.NATIVE;
          break;
        case TRANSIENT:
          result |= java.lang.reflect.Modifier.TRANSIENT;
          break;
        case VOLATILE:
          result |= java.lang.reflect.Modifier.VOLATILE;
          break;
        case STRICTFP:
          result |= java.lang.reflect.Modifier.STRICT;
          break;
        default:
          break;
      }
    }
    return result;
  }
}
