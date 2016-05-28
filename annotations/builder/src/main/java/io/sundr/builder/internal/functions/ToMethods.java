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

package io.sundr.builder.internal.functions;

import io.sundr.Function;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.T;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.builder.internal.utils.BuilderUtils.getInlineableConstructors;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.codegen.utils.StringUtils.singularize;

import static io.sundr.builder.internal.functions.CollectionTypes.IS_COLLECTION;

public enum ToMethods implements Function<Property, Set<Method>> {

   WITH_NESTED_INLINE {

        public Set<Method> apply(Property property) {
            Set<Method> result = new LinkedHashSet<Method>();
            TypeRef unwrappedType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
            TypeDef baseType = BuilderContextManager.getContext().getBuildableRepository().getBuildable(unwrappedType);

            for (Method constructor : getInlineableConstructors(property)) {
                boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
                String ownPrefix = isCollection ? "addNew" : "withNew";
                String ownName = ownPrefix + captializeFirst(isCollection
                        ? singularize(property.getName())
                        : property.getName());

                String delegatePrefix = IS_COLLECTION.apply(property.getTypeRef()) ? "addTo" : "with";
                String delegateName = delegatePrefix + captializeFirst(property.getName());

                String args = StringUtils.join(constructor.getArguments(), new Function<Property, String>() {
                    public String apply(Property item) {
                        return item.getName();
                    }
                }, ", ");


                result.add(new MethodBuilder()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withReturnType(new TypeParamRefBuilder().withName(T.getName()).build())
                        .withArguments(constructor.getArguments())
                        .withName(ownName)
                        //TODO: decide how to roll. Use sets or lists?
                        .withParameters(new LinkedHashSet<TypeParamDef>(baseType.getParameters()))
                        .addToAttributes(BODY, "return " + delegateName + "(new " + baseType.getName() + "(" + args + "));")
                        .build());
            }

            return result;
        }
    };


}
