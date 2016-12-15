/*
 * Copyright 2015 The original authors.
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

package io.sundr.builder.internal;

import io.sundr.builder.annotations.Inline;
import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.ReplacePackage;
import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.functions.Sources;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.StringStatement;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import java.util.ArrayList;
import java.util.Arrays;

import static io.sundr.builder.Constants.INLINEABLE;
import static io.sundr.codegen.functions.Collections.SET;
import static io.sundr.builder.Constants.T;
import static io.sundr.builder.Constants.T_REF;
import static io.sundr.builder.Constants.VALIDATE_SNIPPET;
import static io.sundr.builder.Constants.VOID;
import static io.sundr.codegen.model.Attributeable.ALSO_IMPORT;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;

public class BuilderContext {

    private static final String VALIDATE_BODY_TEXT = loadResourceQuietly(VALIDATE_SNIPPET);

    private final Elements elements;
    private final Types types;
    private final CodegenContext codegenContext;

    private final TypeDef functionClass;
    private final TypeDef predicateClass;
    private final TypeDef baseFluentClass;
    private final TypeDef fluentInterface;
    private final TypeDef builderInterface;
    private final TypeDef nestedInterface;
    private final TypeDef editableInterface;
    private final TypeDef visitableInterface;
    private final TypeDef visitableBuilderInterface;
    private final TypeDef visitorInterface;
    private final TypeDef typedVisitorInterface;
    private final TypeDef pathAwareVisitorClass;
    private final TypeDef functionInterface;
    private final TypeDef inlineableBase;
    private final TypeDef validationUtils;
    private final Boolean generateBuilderPackage;
    private final Boolean validationEnabled;
    private final String builderPackage;
    private final Inline[] inlineables;
    private final BuildableRepository buildableRepository;

    
    public BuilderContext(Elements elements, Types types, Boolean generateBuilderPackage, Boolean validationEnabled, String builderPackage, Inline... inlineables) {
        this.elements = elements;
        this.types = types;
        this.validationEnabled = validationEnabled;
        this.codegenContext = CodegenContext.create(elements, types);
        this.generateBuilderPackage = generateBuilderPackage;
        this.builderPackage = builderPackage;
        this.inlineables = inlineables;

        buildableRepository = new BuildableRepository();

        ClassTo.TYPEDEF.apply(ArrayList.class);

        functionClass = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Function.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();


        predicateClass = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Predicate.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        visitorInterface = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Visitor.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        typedVisitorInterface = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/TypedVisitor.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        pathAwareVisitorClass = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/PathAwareTypedVisitor.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        functionInterface  = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Function.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        visitableInterface = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Visitable.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();
        
        builderInterface = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Builder.java"))
                .withPackageName(builderPackage)
                .build();

        fluentInterface = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Fluent.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();


        baseFluentClass  = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/BaseFluent.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        nestedInterface = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Nested.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        editableInterface = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Editable.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        visitableBuilderInterface = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/VisitableBuilder.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        inlineableBase = new TypeDefBuilder(Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/Inlineable.java"))
                .accept(new ReplacePackage("io.sundr.builder", builderPackage))
                .build();

        validationUtils = new TypeDefBuilder()
                .withName("ValidationUtils")
                .withPackageName(builderPackage)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC, Modifier.FINAL))
                .addNewMethod()
                    .withName("validate")
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
                    .withReturnType(VOID)
                    .withParameters(T)
                    .addNewArgument()
                        .withTypeRef(T_REF)
                        .withName("item")
                    .endArgument()
                    .withNewBlock()
                        .withStatements(new StringStatement(VALIDATE_BODY_TEXT))
                    .endBlock()
                .addToAttributes(ALSO_IMPORT, Arrays.<ClassRef>asList(
                        SET.toReference(),
                        new ClassRefBuilder().withNewDefinition().withPackageName("javax.validation").withName("Validator").and().build(),
                        new ClassRefBuilder().withNewDefinition().withPackageName("javax.validation").withName("ValidatorFactory").and().build(),
                        new ClassRefBuilder().withNewDefinition().withPackageName("javax.validation").withName("Validation").and().build(),
                        new ClassRefBuilder().withNewDefinition().withPackageName("javax.validation").withName("ValidationException").and().build(),
                        new ClassRefBuilder().withNewDefinition().withPackageName("javax.validation").withName("ConstraintViolation").and().build(),
                        new ClassRefBuilder().withNewDefinition().withPackageName("javax.validation").withName("ConstraintViolationException").and().build()
                        ))
                .endMethod()
                .build();
    }

    public Elements getElements() {
        return elements;
    }

    public Types getTypes() {
        return types;
    }

    public Boolean getGenerateBuilderPackage() {
        return generateBuilderPackage;
    }

    public Boolean isValidationEnabled() {
        return validationEnabled;
    }

    public String getBuilderPackage() {
        return builderPackage;
    }


    public TypeDef getFunctionClass() {
        return functionClass;
    }

    public TypeDef getPredicateClass() {
        return predicateClass;
    }

    public TypeDef getBaseFluentClass() {
        return baseFluentClass;
    }

    public TypeDef getFluentInterface() {
        return fluentInterface;
    }

    public TypeDef getFunctionInterface() {
        return functionInterface;
    }

    public TypeDef getBuilderInterface() {
        return builderInterface;
    }

    public TypeDef getNestedInterface() {
        return nestedInterface;
    }

    public TypeDef getEditableInterface() {
        return editableInterface;
    }

    public TypeDef getVisitableInterface() {
        return visitableInterface;
    }

    public TypeDef getVisitableBuilderInterface() {
        return visitableBuilderInterface;
    }

    public TypeDef getVisitorInterface() {
        return visitorInterface;
    }

    public TypeDef getTypedVisitorInterface() {
        return typedVisitorInterface;
    }

    public TypeDef getPathAwareVisitorClass() {
        return pathAwareVisitorClass;
    }

    public TypeDef getInlineableBase() {
        return inlineableBase;
    }


    public Boolean getValidationEnabled() {
        return validationEnabled;
    }

    public TypeDef getInlineableInterface(Inline inline) {
        return new TypeDefBuilder(inlineableBase)
                .withKind(Kind.INTERFACE)
                .withPackageName(builderPackage)
                .withName(inline.prefix() + (!inline.name().isEmpty() ? inline.name() : INLINEABLE.getName()) + inline.suffix())
                .withParameters(INLINEABLE.getParameters())
                .addNewMethod()
                .withReturnType(TypeUtils.newTypeParamRef("T"))
                .withName(inline.value())
                .and()
                .build();
    }

    public Inline[] getInlineables() {
        return inlineables;
    }

    public TypeDef getValidationUtils() {
        return validationUtils;
    }

    public BuildableRepository getBuildableRepository() {
        return buildableRepository;
    }

    public DefinitionRepository getDefinitionRepository() {
        return codegenContext.getDefinitionRepository();
    }
}
