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

package io.sundr.builder.internal.processor;

import static io.sundr.builder.Constants.ADDITIONAL_BUILDABLES;
import static io.sundr.builder.Constants.ADDITIONAL_TYPES;
import static io.sundr.builder.Constants.BUILDABLE;
import static io.sundr.builder.Constants.EDITABLE_ENABLED;
import static io.sundr.builder.Constants.EXTERNAL_BUILDABLE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.util.Elements;

import io.sundr.builder.Constants;
import io.sundr.builder.Visitor;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.ExternalBuildables;
import io.sundr.builder.annotations.Inline;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.apt.processor.AbstractCodeGeneratingProcessor;
import io.sundr.model.Assign;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Expression;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.RichTypeDef;
import io.sundr.model.This;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.utils.TypeArguments;

public abstract class AbstractBuilderProcessor extends AbstractCodeGeneratingProcessor {

  public static final String EMPTY = "";

  void generateLocalDependenciesIfNeeded() {
    BuilderContext context = BuilderContextManager.getContext();
    try {
      if (context.getGenerateBuilderPackage() && !Constants.DEFAULT_BUILDER_PACKAGE.equals(context.getBuilderPackage())) {

        generate(context.getVisitableInterface());
        generate(context.getVisitorsClass());
        generate(context.getVisitorInterface());
        generate(context.getTypedVisitorInterface());
        generate(context.getPathAwareVisitorClass());
        generate(context.getVisitorWiretapClass());
        generate(context.getDelegatingVisitorClass());
        generate(context.getVisitorListenerInterface());

        generate(context.getVisitableBuilderInterface());
        generate(context.getVisitableMapClass());
        generate(context.getBuilderInterface());
        generate(context.getFluentInterface());
        generate(context.getBaseFluentClass());
        generate(context.getNestedInterface());
        generate(context.getEditableInterface());
      }

      if (context.isValidationEnabled() && !classExists(context.getBuilderPackage() + ".ValidationUtils")) {
        generate(context.getValidationUtils());
      }
    } catch (Exception e) {
      //
    }
  }

  boolean classExists(String c) {
    Elements elements = processingEnv.getElementUtils();
    return elements.getTypeElement(c) != null;
  }

  static TypeDef inlineableOf(BuilderContext ctx, RichTypeDef type, Inline inline) {
    final String inlineableName = !inline.name().isEmpty()
        ? inline.name()
        : inline.prefix() + type.getName() + inline.suffix();

    List<Method> constructors = new ArrayList<Method>();
    final TypeDef builderType = ClazzAs.BUILDER.apply(type);
    TypeDef inlineType = BuilderUtils.getInlineType(ctx, inline);
    TypeDef returnType = BuilderUtils.getInlineReturnType(ctx, inline, type);
    final ClassRef inlineTypeRef = inlineType.toReference(returnType.toReference());

    //Use the builder as the base of the inlineable. Just add interface and change name.
    final TypeDef shallowInlineType = new TypeDefBuilder(builderType)
        .withName(inlineableName)
        .withImplementsList(inlineTypeRef)
        .withProperties()
        .withMethods()
        .withConstructors().build();

    TypeRef functionType = Constants.FUNCTION.toReference(type.toInternalReference(), returnType.toInternalReference());

    Property builderProperty = new PropertyBuilder()
        .withTypeRef(builderType.toInternalReference())
        .withName(BUILDER)
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .build();

    Property functionProperty = new PropertyBuilder()
        .withTypeRef(functionType)
        .withName(FUNCTION)
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .build();

    Method inlineMethod = new MethodBuilder()
        .withReturnType(returnType.toInternalReference())
        .withName(inline.value())
        .withNewBlock()
        .addNewStringStatementStatement(BUILD_AND_APPLY_FUNCTION)
        .endBlock()
        .withNewModifiers().withPublic().endModifiers()
        .build();

    constructors.add(new MethodBuilder()
        .withReturnType(inlineTypeRef)
        .withName(EMPTY)
        .addNewArgument()
        .withName(FUNCTION)
        .withTypeRef(functionType)
        .and()
        .withNewModifiers().withPublic().endModifiers()
        .withNewBlock()
        .addNewStringStatementStatement(String.format(NEW_BULDER_AND_SET_FUNCTION_FORMAT, builderType.getName()))
        .endBlock()
        .build());

    constructors.add(new MethodBuilder()
        .withReturnType(inlineTypeRef)
        .withName(EMPTY)
        .addNewArgument()
        .withName(ITEM)
        .withTypeRef(type.toInternalReference())
        .and()
        .addNewArgument()
        .withName(FUNCTION)
        .withTypeRef(functionType)
        .and()
        .withNewModifiers().withPublic().endModifiers()
        .withNewBlock()
        .addNewStringStatementStatement(String.format(NEW_BULDER_WITH_ITEM_AND_SET_FUNCTION_FORMAT, builderType.getName()))
        .endBlock()
        .build());

    if (type.equals(returnType)) {
      Property item = Property.newProperty(type.toInternalReference(), ITEM);
      Property a = Property.newProperty(ClassRef.OBJECT, "a");

      constructors.add(new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(inlineTypeRef)
          .withName(EMPTY)
          .withArguments(item)
          .withNewBlock()
          .addToStatements(
              Expression.newCall("super", item.toReference()).toStatement(),
              new Assign(new This().property("builder"),
                  Expression.createNew(builderType.toInternalReference(), new This(), item.toReference()))
                      .toStatement(),
              new Assign(new This().property("function"), Expression.lamba(a, a.toReference())).toStatement())
          .endBlock()
          .build());
    }

    return new TypeDefBuilder(shallowInlineType)
        .withAnnotations()
        .withNewModifiers().withPublic().endModifiers()
        .withConstructors(constructors)
        .addToProperties(builderProperty, functionProperty)
        .addToMethods(inlineMethod)
        .accept(new Visitor<ClassRefBuilder>() {
          @Override
          public void visit(ClassRefBuilder builder) {
            List<TypeRef> updatedArguments = new ArrayList<TypeRef>();
            for (TypeRef arg : builder.buildArguments()) {
              if (arg.equals(builderType.toInternalReference())) {
                updatedArguments.add(shallowInlineType.toInternalReference());
              } else {
                updatedArguments.add(arg);
              }
            }
            builder.withArguments(updatedArguments);
          }
        }).build();
  }

  public void generateBuildables(BuilderContext ctx, Set<TypeDef> buildables) {
    int total = ctx.getBuildableRepository().getBuildables().size();
    int count = 0;
    for (TypeDef typeDef : buildables) {
      RichTypeDef richTypeDef = TypeArguments.apply(typeDef);
      double percentage = 100d * (count++) / total;
      if (typeDef.isInterface() || typeDef.isAnnotation()) {
        continue;
      }
      System.err.printf("\033[2K%3d%% Generating: %s\r", Math.round(percentage), typeDef.getFullyQualifiedName());
      generate(ClazzAs.FLUENT.apply(richTypeDef));
      if (typeDef.isAbstract()) {
        continue;
      }

      if (!typeDef.isFinal() && typeDef.getAttributes().containsKey(EDITABLE_ENABLED)
          && (Boolean) typeDef.getAttributes().get(EDITABLE_ENABLED)) {
        generate(ClazzAs.EDITABLE_BUILDER.apply(richTypeDef));
        generate(ClazzAs.EDITABLE.apply(richTypeDef));
      } else {
        generate(ClazzAs.BUILDER.apply(richTypeDef));
      }

      Buildable buildable = typeDef.getAttribute(BUILDABLE);
      ExternalBuildables externalBuildables = typeDef.getAttribute(EXTERNAL_BUILDABLE);
      if (buildable != null) {
        for (final Inline inline : buildable.inline()) {
          generate(inlineableOf(ctx, richTypeDef, inline));
        }
      } else if (externalBuildables != null) {
        for (final Inline inline : externalBuildables.inline()) {
          generate(inlineableOf(ctx, richTypeDef, inline));
        }
      }
    }
  }

  /**
   * Returns true if pojos where generated.
   *
   * @param builderContext The builder context.
   * @param buildables The set of buildables.
   */
  public void generatePojos(BuilderContext builderContext, Set<TypeDef> buildables) {
    Set<TypeDef> additonalBuildables = new HashSet<>();
    Set<TypeDef> additionalTypes = new HashSet<>();
    for (TypeDef typeDef : buildables) {
      RichTypeDef richTypeDef = TypeArguments.apply(typeDef);
      if (typeDef.isInterface() || typeDef.isAnnotation()) {
        typeDef = ClazzAs.POJO.apply(richTypeDef);
        builderContext.getDefinitionRepository().register(typeDef);
        builderContext.getBuildableRepository().register(typeDef);
        generate(typeDef);
        additonalBuildables.add(typeDef);

        if (typeDef.hasAttribute(ADDITIONAL_BUILDABLES)) {
          for (TypeDef also : typeDef.getAttribute(ADDITIONAL_BUILDABLES)) {
            builderContext.getDefinitionRepository().register(also);
            builderContext.getBuildableRepository().register(also);
            generate(also);
            additonalBuildables.add(also);
          }
        }

        if (typeDef.hasAttribute(ADDITIONAL_TYPES)) {
          for (TypeDef also : typeDef.getAttribute(ADDITIONAL_TYPES)) {
            builderContext.getDefinitionRepository().register(also);
            generate(also);
            additionalTypes.add(also);
          }
        }
      }
    }
    generateBuildables(builderContext, additonalBuildables);
  }

  private static final String BUILDER = "builder";
  private static final String FUNCTION = "function";
  private static final String ITEM = "item";

  private static final String NEW_BUILDER_AND_EMTPY_FUNCTION_FORMAT = "super(item);this.builder=new %s(this, item);this.function=new %s;";
  private static final String NEW_BULDER_AND_SET_FUNCTION_FORMAT = "super();this.builder=new %s(this);this.function=function;";
  private static final String NEW_BULDER_WITH_ITEM_AND_SET_FUNCTION_FORMAT = "super(item);this.builder=new %s(this, item);this.function=function;";
  private static final String BUILD_AND_APPLY_FUNCTION = " return function.apply(builder.build());";

}
