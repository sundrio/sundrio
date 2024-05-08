/*
 *      Copyright 2017 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.builder.internal.visitors;

import static io.sundr.builder.Constants.DESCENDANTS;
import static io.sundr.builder.Constants.DESCENDANT_OF;
import static io.sundr.builder.Constants.INIT_EXPRESSION;
import static io.sundr.builder.Constants.INIT_EXPRESSION_FUNCTION;
import static io.sundr.builder.Constants.LAZY_COLLECTIONS_INIT_ENABLED;
import static io.sundr.builder.Constants.LAZY_MAP_INIT_ENABLED;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.model.Attributeable.ALSO_IMPORT;
import static io.sundr.model.Attributeable.INIT;
import static io.sundr.model.Attributeable.INIT_FUNCTION;
import static io.sundr.model.Attributeable.LAZY_INIT;
import static io.sundr.model.utils.Types.isAbstract;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import io.sundr.builder.Visitor;
import io.sundr.builder.internal.functions.Construct;
import io.sundr.builder.internal.functions.ToConstructExpression;
import io.sundr.builder.internal.functions.TypeAs;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Expression;
import io.sundr.model.Kind;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.utils.Collections;
import io.sundr.model.utils.Optionals;
import io.sundr.model.utils.Types;

public class InitEnricher implements Visitor<PropertyBuilder> {

  @Override
  public void visit(PropertyBuilder builder) {
    TypeRef typeRef = builder.buildTypeRef();
    TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_OPTIONAL_OF)
        .apply(typeRef);
    boolean isBuildable = isBuildable(unwrapped);
    boolean hasDescendants = false;

    if (!(typeRef instanceof ClassRef)) {
      return;
    }

    Property parent = (Property) builder.getAttributes().get(DESCENDANT_OF);
    if (parent != null) {
      typeRef = parent.getTypeRef();
      unwrapped = TypeAs.combine(TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_OPTIONAL_OF).apply(typeRef);
    } else if (builder.getAttributes().containsKey(DESCENDANTS)
        && !((Collection) builder.getAttributes().get(DESCENDANTS)).isEmpty()) {
      hasDescendants = true;
    }

    List<TypeRef> arguments = ((ClassRef) typeRef).getArguments();

    TypeRef targetType = unwrapped;
    if (isBuildable || hasDescendants) {
      ClassRef unwarppedClassRef = (unwrapped instanceof ClassRef) ? (ClassRef) unwrapped : null;

      targetType = isAbstract(unwarppedClassRef) || GetDefinition.of(unwarppedClassRef).getKind() == Kind.INTERFACE
          ? TypeAs.VISITABLE_BUILDER_Q_REF.apply(unwarppedClassRef)
          : TypeAs.BUILDER_REF.apply(unwarppedClassRef);
    }

    boolean isArray = Types.isArray(typeRef);
    boolean isSet = Types.isSet(typeRef);
    boolean isAbstractSet = isSet && Types.isAbstract(typeRef);
    boolean isList = Types.isList(typeRef);
    boolean isAbstractList = isList && Types.isAbstract(typeRef);
    boolean isMap = Types.isMap(typeRef);
    boolean isAbstractMap = isMap && Types.isAbstract(typeRef);
    boolean isOptional = Types.isOptional(typeRef);
    boolean isOptionalInt = Types.isOptionalInt(typeRef);
    boolean isOptionalDouble = Types.isOptionalDouble(typeRef);
    boolean isOptionalLong = Types.isOptionalLong(typeRef);

    boolean lazyCollectionsInitEnabled = builder.getAttributes().containsKey(LAZY_COLLECTIONS_INIT_ENABLED)
        && (Boolean) builder.getAttributes().get(LAZY_COLLECTIONS_INIT_ENABLED);
    boolean lazyMapInitEnabled = builder.getAttributes().containsKey(LAZY_MAP_INIT_ENABLED)
        && (Boolean) builder.getAttributes().get(LAZY_MAP_INIT_ENABLED);

    if (isArray || isList) {
      ClassRef listRef = isArray || isAbstractList
          ? Collections.ARRAY_LIST.toReference(targetType)
          : new ClassRefBuilder((ClassRef) typeRef).withArguments(targetType).withDimensions(0).build();

      TypeDef listDef = new TypeDefBuilder(TypeDef.forName(listRef.getFullyQualifiedName()))
          .addNewConstructor()
          .endConstructor()
          .addNewConstructor()
          .addNewArgument()
          .withTypeRef(Collections.LIST.toReference(targetType))
          .withName("l")
          .endArgument()
          .endConstructor()
          .build();

      builder.addToAttributes(LAZY_INIT, "new " + listRef + "()")
          .addToAttributes(INIT, lazyCollectionsInitEnabled ? null : builder.getAttributes().get(LAZY_INIT))
          .addToAttributes(INIT_EXPRESSION, lazyCollectionsInitEnabled ? null : Expression.createNew(listRef))
          .addToAttributes(INIT_FUNCTION, new Construct(listDef, targetType))
          .addToAttributes(INIT_EXPRESSION_FUNCTION, new ToConstructExpression(listDef, targetType))
          .addToAttributes(ALSO_IMPORT, Arrays.asList(targetType, listRef));
    } else if (isSet) {
      ClassRef setRef = isAbstractSet
          ? Collections.LINKED_HASH_SET.toReference(targetType)
          : new ClassRefBuilder((ClassRef) typeRef).withArguments(targetType).build();

      TypeDef setDef = new TypeDefBuilder(TypeDef.forName(setRef.getFullyQualifiedName()))
          .addNewConstructor()
          .endConstructor()
          .addNewConstructor()
          .addNewArgument()
          .withTypeRef(Collections.SET.toReference(targetType))
          .withName("s")
          .endArgument()
          .endConstructor()
          .build();

      builder.addToAttributes(LAZY_INIT, "new " + setRef + "()")
          .addToAttributes(INIT, lazyCollectionsInitEnabled ? null : builder.getAttributes().get(LAZY_INIT))
          .addToAttributes(INIT_EXPRESSION, lazyCollectionsInitEnabled ? null : Expression.createNew(setRef))
          .addToAttributes(INIT_FUNCTION, new Construct(setDef, unwrapped))
          .addToAttributes(INIT_EXPRESSION_FUNCTION, new ToConstructExpression(setDef, targetType))
          .addToAttributes(ALSO_IMPORT, Arrays.asList(targetType, setRef));
    } else if (isMap) {
      ClassRef mapRef = isAbstractMap
          ? Collections.LINKED_HASH_MAP.toReference(arguments)
          : new ClassRefBuilder((ClassRef) typeRef).withArguments(arguments).build();

      TypeDef mapDef = new TypeDefBuilder(TypeDef.forName(mapRef.getFullyQualifiedName()))
          .addNewConstructor()
          .endConstructor()
          .addNewConstructor()
          .addNewArgument()
          .withTypeRef(Collections.MAP.toReference(arguments))
          .withName("m")
          .endArgument()
          .endConstructor()
          .build();

      builder.addToAttributes(LAZY_INIT, "new " + mapRef + "()")
          .addToAttributes(INIT, lazyMapInitEnabled ? null : builder.getAttributes().get(LAZY_INIT))
          .addToAttributes(INIT_EXPRESSION, lazyMapInitEnabled ? null : Expression.createNew(mapRef))
          .addToAttributes(INIT_FUNCTION, new Construct(mapDef, arguments))
          .addToAttributes(INIT_EXPRESSION_FUNCTION, new ToConstructExpression(mapDef, targetType))
          .addToAttributes(ALSO_IMPORT, Arrays.asList(targetType, mapRef));
    } else if (isOptional) {
      final ClassRef ref = new ClassRefBuilder(Optionals.OPTIONAL.toReference()).withArguments(java.util.Collections.EMPTY_LIST)
          .build();
      builder.addToAttributes(INIT, "Optional.empty()")
          .addToAttributes(INIT_EXPRESSION, Expression.call(Optional.class, "empty"))
          .addToAttributes(INIT_FUNCTION, new Construct(Optionals.OPTIONAL, targetType, "of"))
          .addToAttributes(INIT_EXPRESSION_FUNCTION, new ToConstructExpression(Optionals.OPTIONAL, targetType, "of"))
          .addToAttributes(ALSO_IMPORT, Arrays.asList(targetType, ref));
    } else if (isOptionalDouble) {
      final ClassRef ref = Optionals.OPTIONAL_DOUBLE.toReference();
      builder.addToAttributes(INIT, "OptionalDouble.empty()")
          .addToAttributes(INIT_EXPRESSION, Expression.call(OptionalDouble.class, "empty"))
          .addToAttributes(INIT_FUNCTION, new Construct(Optionals.OPTIONAL_DOUBLE, targetType, "of"))
          .addToAttributes(INIT_EXPRESSION_FUNCTION, new ToConstructExpression(Optionals.OPTIONAL_DOUBLE, targetType, "of"))
          .addToAttributes(ALSO_IMPORT, Arrays.asList(targetType, ref));
    } else if (isOptionalInt) {
      final ClassRef ref = Optionals.OPTIONAL_INT.toReference();
      builder.addToAttributes(INIT, "OptionalInt.empty()")
          .addToAttributes(INIT_EXPRESSION, Expression.call(OptionalInt.class, "empty"))
          .addToAttributes(INIT_FUNCTION, new Construct(Optionals.OPTIONAL_INT, targetType, "of"))
          .addToAttributes(INIT_EXPRESSION_FUNCTION, new ToConstructExpression(Optionals.OPTIONAL_INT, targetType, "of"))
          .addToAttributes(ALSO_IMPORT, Arrays.asList(targetType, ref));
    } else if (isOptionalLong) {
      final ClassRef ref = Optionals.OPTIONAL_LONG.toReference();
      builder.addToAttributes(INIT, "OptionalLong.empty()")
          .addToAttributes(INIT_EXPRESSION, Expression.call(OptionalLong.class, "empty"))
          .addToAttributes(INIT_FUNCTION, new Construct(Optionals.OPTIONAL_LONG, targetType, "of"))
          .addToAttributes(INIT_EXPRESSION_FUNCTION, new ToConstructExpression(Optionals.OPTIONAL_LONG, targetType, "of"))
          .addToAttributes(ALSO_IMPORT, Arrays.asList(targetType, ref));
    }
  }
}
