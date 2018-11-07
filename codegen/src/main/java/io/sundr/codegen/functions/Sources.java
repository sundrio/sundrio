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

package io.sundr.codegen.functions;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NamedNode;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.TypeParameter;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.QualifiedNameExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import io.sundr.builder.Function;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.model.AnnotationRef;
import io.sundr.codegen.model.AnnotationRefBuilder;
import io.sundr.codegen.model.Attributeable;
import io.sundr.codegen.model.Block;
import io.sundr.codegen.model.BlockBuilder;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.PrimitiveRef;
import io.sundr.codegen.model.PrimitiveRefBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.StringStatement;
import io.sundr.codegen.model.StringStatementBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.VoidRef;
import io.sundr.codegen.model.WildcardRef;
import io.sundr.codegen.model.WildcardRefBuilder;
import io.sundr.codegen.utils.IOUtils;
import io.sundr.codegen.utils.TypeUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Sources {

    private static final String JAVA_LANG = "java.lang";
    private static final String SEPARATOR = ".";

    private static Function<Node, String> PACKAGENAME = new Function<Node, String>() {

        public String apply(Node node) {
            if (node instanceof NamedNode) {
                String name = ((NamedNode)node).getName();
                Node current = node;
                while (!(current instanceof CompilationUnit)) {
                    current = current.getParentNode();
                }

                CompilationUnit compilationUnit = (CompilationUnit) current;

                for (ImportDeclaration importDecl : compilationUnit.getImports()) {
                    NameExpr importExpr = importDecl.getName();
                    if (importExpr instanceof QualifiedNameExpr) {
                        QualifiedNameExpr qualifiedNameExpr = (QualifiedNameExpr) importExpr;
                        String className = qualifiedNameExpr.getName();
                        if (name.equals(className)) {
                            return qualifiedNameExpr.getQualifier().toString();
                        }
                    } else if (importDecl.getName().getName().endsWith(SEPARATOR + name)) {
                        String importName = importDecl.getName().getName();
                        return  importName.substring(0, importName.length() - name.length() -1);
                    }
                }

               try {
                   Class.forName(JAVA_LANG + "." + name);
                   return JAVA_LANG;
               } catch (ClassNotFoundException ex) {
                   return compilationUnit.getPackage().getPackageName();
               }
            }
            return null;
        }
    };


    private static Function<Node, Set<ClassRef>> IMPORTS = new Function<Node, Set<ClassRef>>() {

        public Set<ClassRef> apply(Node node) {
            Set<ClassRef> imports = new LinkedHashSet<ClassRef>();

            if (node instanceof NamedNode) {
                String name = ((NamedNode)node).getName();
                Node current = node;
                while (!(current instanceof CompilationUnit)) {
                    current = current.getParentNode();
                }

                CompilationUnit compilationUnit = (CompilationUnit) current;

                for (ImportDeclaration importDecl : compilationUnit.getImports()) {
                    String className = null;
                    String packageName = null;

                    NameExpr importExpr = importDecl.getName();
                    if (importExpr instanceof QualifiedNameExpr) {
                        QualifiedNameExpr qualifiedNameExpr = (QualifiedNameExpr) importExpr;
                        className = qualifiedNameExpr.getName();
                        packageName = qualifiedNameExpr.getQualifier().toString();
                    } else if (importDecl.getName().getName().endsWith(SEPARATOR + name)) {
                        String importName = importDecl.getName().getName();
                        packageName = importName.substring(0, importName.length() - name.length() -1);
                    }
                    if (className != null && !className.isEmpty()) {
                        imports.add(new ClassRefBuilder().withNewDefinition().withName(className).withPackageName(packageName).and().build());
                    }
                }
            }
            return imports;
        }
    };

    private static Function<ClassOrInterfaceType, TypeRef> CLASS_OR_TYPEPARAM_REF = new Function<ClassOrInterfaceType, TypeRef>() {
        public TypeRef apply(ClassOrInterfaceType classOrInterfaceType) {
            String boundPackage = PACKAGENAME.apply(classOrInterfaceType);
            String boundName = classOrInterfaceType.getName();

            List<TypeRef> arguments = new ArrayList<TypeRef>();
            for (Type arg :classOrInterfaceType.getTypeArgs()) {
                if (arg instanceof ReferenceType) {
                    //TODO: Need to check if this is valid for all cases...
                    ReferenceType referenceType = (ReferenceType) arg;
                    Type type = referenceType.getType();
                    int dimensions = referenceType.getArrayCount();
                    if (type instanceof ClassOrInterfaceType) {
                        TypeRef intermediateRef = CLASS_OR_TYPEPARAM_REF.apply((ClassOrInterfaceType)type);
                        if (intermediateRef instanceof ClassRef) {
                            arguments.add(new ClassRefBuilder((ClassRef) intermediateRef)
                                    .withDimensions(dimensions)
                                    .build());
                        } else if (intermediateRef instanceof TypeParamRef) {
                            arguments.add(new TypeParamRefBuilder((TypeParamRef) intermediateRef)
                                    .withDimensions(dimensions)
                                    .build());
                        } else {
                            throw new IllegalStateException("Expected class or type param reference");
                        }
                    } else {
                        String name = referenceType.toString();
                        arguments.add(new TypeParamRefBuilder()
                                .withName(name)
                                .withDimensions(dimensions)
                                .build());
                    }
                } else if (arg instanceof WildcardType) {
                    WildcardType wildcardType = (WildcardType)arg;
                    if (wildcardType.getExtends() != null) {
                        TypeRef bound = TYPEREF.apply(wildcardType.getExtends());
                        arguments.add(new WildcardRefBuilder().addToBounds(bound).build());
                    } else {
                        arguments.add(new WildcardRef());
                    }
                }
            }


            if (classOrInterfaceType.getParentNode()  == classOrInterfaceType) {
                return new TypeParamRefBuilder().withName(boundName).build();
            }

            String fqn = boundPackage + "." + boundName;
            TypeDef knownDefinition = DefinitionRepository.getRepository().getDefinition(fqn);

            if (knownDefinition != null) {
                return arguments.isEmpty()
                        ? new ClassRefBuilder().withDefinition(knownDefinition).build()
                        : knownDefinition.toReference(arguments);
            } else if (classOrInterfaceType.getTypeArgs().isEmpty() && boundName.length() == 1)  {
                //We are doing our best here to distinguish between class refs and type parameter refs.
                return new TypeParamRefBuilder().withName(boundName).build();
            } else {
                return new ClassRefBuilder()
                        .withNewDefinition()
                        .withPackageName(boundPackage)
                        .withName(boundName)
                        .endDefinition()
                        .withArguments(arguments)
                        .build();
            }
        }
    };


    private static Function<Type, TypeRef> TYPEREF = new Function<Type, TypeRef>() {
        public TypeRef apply(Type type) {
            if (type instanceof VoidType) {
                return new VoidRef();
            } else if (type instanceof WildcardType) {
                return new WildcardRef();
            } else if (type instanceof ReferenceType) {
                ReferenceType referenceType = (ReferenceType) type;
                int dimensions = referenceType.getArrayCount();
                TypeRef typeRef = TYPEREF.apply(referenceType.getType());
                if (dimensions == 0) {
                    return typeRef;
                } else if (typeRef instanceof ClassRef) {
                    return new ClassRefBuilder((ClassRef)typeRef).withDimensions(dimensions).build();
                } else if (typeRef instanceof PrimitiveRef) {
                    return new PrimitiveRefBuilder((PrimitiveRef)typeRef).withDimensions(dimensions).build();
                } else if (typeRef instanceof TypeParamRef) {
                    return new TypeParamRefBuilder((TypeParamRef)typeRef).withDimensions(dimensions).build();
                }
            } else if (type instanceof PrimitiveType) {
                PrimitiveType primitiveType = (PrimitiveType) type;
                return new PrimitiveRefBuilder().withName(primitiveType.getType().name()).build();
            } else if (type instanceof ClassOrInterfaceType) {
                return CLASS_OR_TYPEPARAM_REF.apply((ClassOrInterfaceType) type);
            }
            throw new IllegalArgumentException("Can't handle type:[" + type + "].");
        }
    };

    private static Function<TypeParameter, TypeParamDef> TYPEPARAMDEF = new Function<TypeParameter, TypeParamDef>() {

        public TypeParamDef apply(TypeParameter typeParameter) {
            List<ClassRef> bounds = new ArrayList<ClassRef>();
            for (ClassOrInterfaceType classOrInterfaceType : typeParameter.getTypeBound()) {
                bounds.add((ClassRef) CLASS_OR_TYPEPARAM_REF.apply(classOrInterfaceType));
            }
            return new TypeParamDefBuilder()
                    .withName(typeParameter.getName())
                    .withBounds(bounds)
                    .build();
        }
    };

    private static Function<AnnotationExpr, AnnotationRef> ANNOTATIONREF = new Function<AnnotationExpr, AnnotationRef>() {

        public AnnotationRef apply(AnnotationExpr annotation) {
            String name = annotation.getName().getName();
            String packageName = PACKAGENAME.apply(annotation);
            return new AnnotationRefBuilder()
                    .withNewClassRef()
                        .withNewDefinition()
                            .withName(name)
                            .withPackageName(packageName)
                        .endDefinition()
                    .endClassRef()
                    .build();
        }
    };


    private static final Function<Statement, StringStatement> STATEMENT = new Function<Statement, StringStatement>() {
        public StringStatement apply(Statement stmt) {
            return new StringStatementBuilder().withProvider(() -> stmt.toString()).build();
        }
    };

    private static final Function<BlockStmt, Block> BLOCK = new Function<BlockStmt, Block>() {
        public Block apply(BlockStmt block) {
            List<io.sundr.codegen.model.Statement> statements = new ArrayList<io.sundr.codegen.model.Statement>();
            if (block != null) {
                for (Statement stmt : block.getStmts()) {
                    statements.add(STATEMENT.apply(stmt));
                }
            }
            return new BlockBuilder().withStatements(statements).build();
        }
    };

    public static Function<TypeDeclaration, TypeDef> TYPEDEF = new Function<TypeDeclaration, TypeDef>() {

        public TypeDef apply(TypeDeclaration type) {
            if (type instanceof ClassOrInterfaceDeclaration) {
                ClassOrInterfaceDeclaration decl = (ClassOrInterfaceDeclaration) type;
                Kind kind = decl.isInterface() ? Kind.INTERFACE : Kind.CLASS;

                List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();

                List<ClassRef> extendsList = new ArrayList<ClassRef>();
                List<ClassRef> implementsList = new ArrayList<ClassRef>();
                List<Property> properties = new ArrayList<Property>();
                List<Method> methods = new ArrayList<Method>();
                List<Method> constructors = new ArrayList<Method>();

                for (TypeParameter typeParameter: decl.getTypeParameters()) {
                    parameters.add(TYPEPARAMDEF.apply(typeParameter));
                }

                for (ClassOrInterfaceType classOrInterfaceType : decl.getExtends()) {
                    extendsList.add((ClassRef) CLASS_OR_TYPEPARAM_REF.apply(classOrInterfaceType));
                }

                for (ClassOrInterfaceType classOrInterfaceType : decl.getImplements()) {
                    implementsList.add((ClassRef) CLASS_OR_TYPEPARAM_REF.apply(classOrInterfaceType));
                }

                for (BodyDeclaration bodyDeclaration : decl.getMembers()) {
                    if (bodyDeclaration instanceof FieldDeclaration) {
                        FieldDeclaration fieldDeclaration = (FieldDeclaration) bodyDeclaration;
                        for (VariableDeclarator var : fieldDeclaration.getVariables()) {
                            TypeRef typeRef = checkAgainstTypeParamRef(TYPEREF.apply(fieldDeclaration.getType()), parameters);
                            properties.add(new PropertyBuilder()
                                    .withName(var.getId().getName())
                                    .withTypeRef(typeRef)
                                    .withModifiers(fieldDeclaration.getModifiers())
                                    .addToAttributes(Attributeable.INIT,  var.getInit() != null ? var.getInit().toStringWithoutComments() : null)
                                    .build());
                        }
                    } else if (bodyDeclaration instanceof MethodDeclaration) {
                        MethodDeclaration methodDeclaration = (MethodDeclaration) bodyDeclaration;
                        List<Property> arguments = new ArrayList<Property>();
                        List<ClassRef> exceptions = new ArrayList<ClassRef>();

                        for (ReferenceType referenceType : methodDeclaration.getThrows()) {
                            TypeRef exceptionRef = TYPEREF.apply(referenceType.getType());
                            if (exceptionRef instanceof ClassRef) {
                                exceptions.add((ClassRef) exceptionRef);
                            }
                        }
                        Boolean preferVarArg = false;

                        for (Parameter parameter : methodDeclaration.getParameters()) {
                            List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
                            for (AnnotationExpr annotationExpr : parameter.getAnnotations()) {
                                annotations.add(ANNOTATIONREF.apply(annotationExpr));
                            }


                            TypeRef typeRef = TYPEREF.apply(parameter.getType());

                            if (parameter.isVarArgs()) {
                                preferVarArg = true;
                                typeRef = typeRef.withDimensions(typeRef.getDimensions() + 1);
                            }

                            arguments.add(new PropertyBuilder()
                                    .withName(parameter.getId().getName())
                                    .withTypeRef(typeRef)
                                    .withModifiers(parameter.getModifiers())
                                    .withAnnotations(annotations)
                                    .build());
                        }


                        List<TypeParamDef> typeParamDefs = new ArrayList<TypeParamDef>();
                        for (TypeParameter typeParameter : methodDeclaration.getTypeParameters()) {
                            typeParamDefs.add(TYPEPARAMDEF.apply(typeParameter));
                        }

                        TypeRef returnType = checkAgainstTypeParamRef(TYPEREF.apply(methodDeclaration.getType()), parameters);
                        methods.add(new MethodBuilder()

                                .withName(methodDeclaration.getName())
                                .withModifiers(methodDeclaration.getModifiers())
                                .withParameters(typeParamDefs)
                                .withVarArgPreferred(preferVarArg)
                                .withReturnType(returnType)
                                .withExceptions(exceptions)
                                .withArguments(arguments)
                                .withBlock(BLOCK.apply(methodDeclaration.getBody()))
                                .build());

                    } else if (bodyDeclaration instanceof ConstructorDeclaration) {
                        ConstructorDeclaration constructorDeclaration = (ConstructorDeclaration) bodyDeclaration;
                        List<Property> arguments = new ArrayList<Property>();
                        List<ClassRef> exceptions = new ArrayList<ClassRef>();

                        for (NameExpr nameExpr : constructorDeclaration.getThrows()) {
                            String name = nameExpr.getName();
                            String packageName = PACKAGENAME.apply(nameExpr);
                            exceptions.add(new ClassRefBuilder().withNewDefinition()
                                    .withName(name)
                                    .withPackageName(packageName)
                            .endDefinition().build());
                        }
                        for (Parameter parameter : constructorDeclaration.getParameters()) {
                            List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
                            for (AnnotationExpr annotationExpr : parameter.getAnnotations()) {
                                annotations.add(ANNOTATIONREF.apply(annotationExpr));
                            }
                            TypeRef typeRef = checkAgainstTypeParamRef(TYPEREF.apply(parameter.getType()), parameters);
                            arguments.add(new PropertyBuilder()
                                    .withName(parameter.getId().getName())
                                    .withTypeRef(typeRef)
                                    .withModifiers(parameter.getModifiers())
                                    .withAnnotations(annotations)
                                    .build());
                        }
                        constructors.add(new MethodBuilder()
                                .withModifiers(constructorDeclaration.getModifiers())
                                .withExceptions(exceptions)
                                .withArguments(arguments)
                                .withBlock(BLOCK.apply(constructorDeclaration.getBlock()))
                                .build());
                    }
                }

               return DefinitionRepository.getRepository().register( new TypeDefBuilder()
                        .withKind(kind)
                        .withPackageName(PACKAGENAME.apply(type))
                        .withName(decl.getName())
                        .withModifiers(type.getModifiers())
                        .withParameters(parameters)
                        .withExtendsList(extendsList)
                        .withImplementsList(implementsList)
                        .withProperties(properties)
                        .withMethods(methods)
                        .withConstructors(constructors)
                        .addToAttributes(TypeDef.ALSO_IMPORT, IMPORTS.apply(type))
                        .build());
            }
            throw new IllegalArgumentException("Unsupported TypeDeclaration:[" + type + "].");
        }

        //To be more accurate we need to check if there is a matching type parameter definition
        //and if so, return a reference to that (rather than consider it a class).
        private TypeRef checkAgainstTypeParamRef(TypeRef typeRef, Collection<TypeParamDef> parameters) {
            TypeParamDef parameterDef = TypeUtils.getParameterDefinition(typeRef, parameters);
            if (parameterDef != null) {
                return parameterDef.toReference();
            }
            return typeRef;
        }
    };


    public static Function<File, CompilationUnit> FROM_FILE_TO_COMPILATIONUNIT = new Function<File, CompilationUnit>() {

        public CompilationUnit apply(File file) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                return JavaParser.parse(fis);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to load file: [" + file.getAbsolutePath() + "] from file system.");
            } finally {
                IOUtils.closeQuietly(fis);
            }
        }
    };

    public static Function<String, CompilationUnit> FROM_CLASSPATH_TO_COMPILATIONUNIT = new Function<String, CompilationUnit>() {

        public CompilationUnit apply(String resource) {
            InputStream is = null;
            try {
                is = getClass().getClassLoader().getResourceAsStream(resource);
                return JavaParser.parse(is);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to load resource: [" + resource + "] from classpath.");
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
    };

    public static Function<InputStream, CompilationUnit> FROM_INPUTSTREAM_TO_COMPILATIONUNIT = new Function<InputStream, CompilationUnit>() {
        public CompilationUnit apply(InputStream is) {
            try {
                return JavaParser.parse(is);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to parse stream.", ex);
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
    };

    public static Function<String, TypeDef> FROM_CLASSPATH_TO_SINGLE_TYPEDEF = new Function<String, TypeDef>() {

        public TypeDef apply(String resource) {
            CompilationUnit cu = Sources.FROM_CLASSPATH_TO_COMPILATIONUNIT.apply(resource);
            TypeDeclaration typeDeclaration = cu.getTypes().get(0);
            return TYPEDEF.apply(typeDeclaration);

        }
    };

    public static Function<InputStream, TypeDef> FROM_INPUTSTEAM_TO_SINGLE_TYPEDEF = new Function<InputStream, TypeDef>() {

        public TypeDef apply(InputStream is) {
            CompilationUnit cu = Sources.FROM_INPUTSTREAM_TO_COMPILATIONUNIT.apply(is);
            TypeDeclaration typeDeclaration = cu.getTypes().get(0);
            return TYPEDEF.apply(typeDeclaration);

        }
    };

}
