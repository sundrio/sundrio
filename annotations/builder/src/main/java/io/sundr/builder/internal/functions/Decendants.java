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

import io.sundr.CachingFunction;
import io.sundr.Function;
import io.sundr.builder.Constants;
import io.sundr.builder.internal.BuildableRepository;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.TypeElement;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.builder.Constants.DESCENDANT_OF;
import static io.sundr.codegen.utils.StringUtils.deCaptializeFirst;
import static io.sundr.builder.internal.utils.BuilderUtils.BUILDABLE;

public class Decendants {

    public static final Function<JavaType, Set<JavaType>> BUILDABLE_DECENDANTS = CachingFunction.wrap(new Function<JavaType, Set<JavaType>>() {
        public Set<JavaType> apply(JavaType item) {
            if (item.getFullyQualifiedName().equals(Constants.OBJECT.getFullyQualifiedName())) {
                return new LinkedHashSet<JavaType>();
            }

            Set<JavaType> result = new LinkedHashSet<JavaType>();
            BuilderContext ctx = BuilderContextManager.getContext();
            BuildableRepository repository = ctx.getRepository();
            for (TypeElement element : repository.getBuildables()) {
                JavaClazz clazz = ctx.getTypeElementToJavaClazz().apply(element);
                JavaType type = clazz.getType();
                if (isDescendant(type, item)) {
                    result.add(type);
                }
            }
            return result;
        }
    });


    /**
     * Find all buildable descendant equivalents of a property.
     *
     * @param property
     * @return
     */
    public static Function<JavaProperty, Set<JavaProperty>> PROPERTY_BUILDABLE_ANCESTORS = CachingFunction.wrap(new Function<JavaProperty, Set<JavaProperty>>() {
        public Set<JavaProperty> apply(JavaProperty property) {
            Set<JavaProperty> result = new LinkedHashSet<JavaProperty>();
            JavaType baseType = property.getType();

            if (baseType.isCollection()) {
                JavaType candidate = TypeAs.UNWRAP_COLLECTION_OF.apply(baseType);
                for (JavaType descendant : BUILDABLE_DECENDANTS.apply(candidate)) {
                    JavaType collectionType = new JavaTypeBuilder(baseType).withGenericTypes(new JavaType[]{descendant}).build();
                    String propertyName = deCaptializeFirst(descendant.getClassName()) + property.getNameCapitalized();
                    result.add(new JavaPropertyBuilder(property)
                            .withName(propertyName)
                            .withType(collectionType)
                            .addToAttributes(DESCENDANT_OF, property)
                            .addToAttributes(BUILDABLE, true)
                            .build());
                }
            } else {
                for (JavaType descendant : BUILDABLE_DECENDANTS.apply(baseType)) {
                    String propertyName = descendant.getSimpleName() + property.getNameCapitalized();
                    result.add(new JavaPropertyBuilder(property)
                            .withName(propertyName)
                            .withType(descendant)
                            .addToAttributes(DESCENDANT_OF, property)
                            .addToAttributes(BUILDABLE, true)
                            .build());
                }
            }
            return result;
        }
    });


    /**
     * Checks if a type is an descendant of an other type
     *
     * @param item      The base type.
     * @param candidate The candidate type.
     * @return true if candidate is a descendant of base type.
     */
    public static boolean isDescendant(JavaType item, JavaType candidate) {
        if (item == null || candidate == null) {
            return false;
        } else if (item.getFullyQualifiedName().equals(candidate.getFullyQualifiedName())) {
            return true;
        } else if (isDescendant(item.getSuperClass(), candidate)) {
            return true;
        } else {
            for (JavaType interfaceType : item.getInterfaces()) {
                if (isDescendant(interfaceType, candidate)) {
                    return true;
                }
            }
            return false;
        }
    }

}
