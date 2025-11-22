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

package io.sundr.builder.internal.functions;

import static io.sundr.builder.Constants.BUILDABLE_ENABLED;
import static io.sundr.builder.Constants.DESCENDANT_OF;
import static io.sundr.builder.Constants.GENERATED;
import static io.sundr.builder.Constants.ORIGIN_TYPEDEF;
import static io.sundr.model.utils.Collections.IS_COLLECTION;
import static io.sundr.model.utils.Collections.IS_MAP;
import static io.sundr.utils.Strings.compact;
import static io.sundr.utils.Strings.deCapitalizeFirst;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

import io.sundr.FunctionFactory;
import io.sundr.builder.annotations.FilterDescendants;
import io.sundr.builder.annotations.IgnoreDescendants;
import io.sundr.builder.internal.BuildableRepository;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.visitors.InitEnricher;
import io.sundr.model.AnnotationRef;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Field;
import io.sundr.model.FieldBuilder;
import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.Assignable;
import io.sundr.model.functions.GetDefinition;

public class Descendants {

  private static final String VALUE = "value";

  public static final Function<TypeDef, Set<TypeDef>> BUILDABLE_DECENDANTS = FunctionFactory
      .cache(new Function<TypeDef, Set<TypeDef>>() {
        @Override
        public Set<TypeDef> apply(TypeDef item) {
          if (item.equals(TypeDef.OBJECT)) {
            return new LinkedHashSet<TypeDef>();
          }

          Set<TypeDef> result = new LinkedHashSet<TypeDef>();
          BuilderContext ctx = BuilderContextManager.getContext();
          BuildableRepository repository = ctx.getBuildableRepository();

          for (TypeDef type : repository.getBuildables()) {

            if (type.getKind() == Kind.CLASS && !type.isAbstract() && isDescendant(type, item) && !type.equals(item)
                && !type.hasAttribute(GENERATED)) {
              result.add(type);
            }
          }
          return result;
        }
      });

  /**
   * Find all buildable descendant equivalents of a field.
   */
  public static Function<Field, Set<Field>> PROPERTY_BUILDABLE_DESCENDANTS = FunctionFactory
      .wrap(new Function<Field, Set<Field>>() {
        @Override
        public Set<Field> apply(Field field) {
          Set<Field> result = new LinkedHashSet<Field>();
          if (isNestingIgnored(field)) {
            return result;
          }

          TypeRef baseType = field.getTypeRef();
          TypeDef origin = field.getAttribute(ORIGIN_TYPEDEF);

          if (IS_COLLECTION.apply(baseType)) {
            TypeRef unwrapped = TypeAs.UNWRAP_COLLECTION_OF.apply(baseType);
            if (unwrapped instanceof ClassRef) {
              ClassRef candidate = (ClassRef) unwrapped;

              addDescendents(field, result, origin, candidate,
                  ref -> new ClassRefBuilder((ClassRef) baseType).withArguments(ref).build());
            }
          } else if (IS_MAP.apply(baseType)) {
            TypeRef unwrapped = TypeAs.UNWRAP_MAP_VALUE_OF.apply(baseType);
            if (unwrapped instanceof ClassRef) {
              ClassRef candidate = (ClassRef) unwrapped;

              addDescendents(field, result, origin, candidate, ref -> new ClassRefBuilder((ClassRef) baseType)
                  .withArguments(TypeAs.UNWRAP_MAP_KEY_OF.apply(baseType), ref).build());
            }
          } else if (io.sundr.model.utils.Types.isOptional(baseType)) {
            // Handle Optional<T> types - unwrap and check if the contained type is buildable
            TypeRef unwrapped = TypeAs.UNWRAP_OPTIONAL_OF.apply(baseType);
            if (unwrapped instanceof ClassRef) {
              ClassRef candidate = (ClassRef) unwrapped;

              addDescendents(field, result, origin, candidate,
                  ref -> new ClassRefBuilder((ClassRef) baseType).withArguments(ref).build());
            }
          } else if (baseType instanceof ClassRef) {
            ClassRef candidate = (ClassRef) baseType;

            addDescendents(field, result, origin, candidate, Function.identity());
          }
          return result;
        }

        private void addDescendents(Field field, Set<Field> result, TypeDef origin, ClassRef candidate,
            Function<ClassRef, ClassRef> typeFunction) {
          Map<String, Boolean> nameConflicts = new HashMap<>();

          LinkedHashMap<TypeDef, ClassRef> decendents = new LinkedHashMap<>();

          for (TypeDef descendant : BUILDABLE_DECENDANTS.apply(GetDefinition.of(candidate))) {
            ClassRef descendantRef = new ClassRefBuilder(descendant.toInternalReference())
                .build();

            if (isNestingFiltered(field, descendantRef)) {
              continue;
            } else if (origin != null && (origin.getName().equals(descendant.getName())
                && !origin.getPackageName().equals(descendant.getPackageName()))) {
              //We don't want to have a class that references a descendant with the same name in an other package. It's an extreme case and will not work.
              continue;
            }

            decendents.put(descendant, descendantRef);
            String compactName = compact(deCapitalizeFirst(descendant.getName()), field.getNameCapitalized());
            nameConflicts.merge(compactName, false, (b1, b2) -> true);
          }

          for (Map.Entry<TypeDef, ClassRef> entry : decendents.entrySet()) {
            String propertyName = compact(deCapitalizeFirst(entry.getKey().getName()), field.getNameCapitalized());
            if (nameConflicts.get(propertyName)) {
              propertyName = deCapitalizeFirst(entry.getKey().getName()) + field.getNameCapitalized();
            }
            result.add(new FieldBuilder(field)
                .withName(propertyName)
                .withTypeRef(typeFunction.apply(entry.getValue()))
                .addToAttributes(DESCENDANT_OF, field)
                .addToAttributes(BUILDABLE_ENABLED, true)
                .accept(new InitEnricher())
                .build());
          }
        }
      });

  /**
   * Checks if a type is an descendant of an other type
   *
   * @param item The base type.
   * @param candidate The candidate type.
   * @return true if candidate is a descendant of base type.
   */
  public static boolean isDescendant(TypeDef item, TypeDef candidate) {
    if (item == null || candidate == null) {
      return false;
    } else if (Assignable.isAssignable(candidate).from(item)) {
      return true;
    }
    return false;
  }

  public static boolean isNestingIgnored(Field field) {
    for (AnnotationRef ref : field.getAnnotations()) {
      if (ref.getClassRef().getFullyQualifiedName().equals(IgnoreDescendants.class.getName())) {
        return true;
      }
    }
    return false;
  }

  public static boolean isNestingFiltered(Field field, ClassRef classRef) {
    for (AnnotationRef ref : field.getAnnotations()) {
      if (ref.getClassRef().getFullyQualifiedName().equals(FilterDescendants.class.getName())) {
        Map<String, Object> parameters = ref.getParameters();
        Object value = parameters == null ? null : parameters.get(VALUE);
        if (value instanceof String && field.getTypeRef() instanceof ClassRef) {
          Pattern p = Pattern.compile((String) value);
          if (p.matcher(classRef.getFullyQualifiedName()).matches()) {
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }

}
