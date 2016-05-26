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
import io.sundr.builder.internal.BuildableRepository;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.utils.StringUtils;

import javax.lang.model.element.Modifier;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.T;
import static io.sundr.builder.internal.utils.BuilderUtils.getInlineableConstructors;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.codegen.utils.StringUtils.singularize;

import static io.sundr.builder.internal.functions.TypeAs.*;

public enum ToMethods implements Function<Property, Set<Method>> {

   WITH_NESTED_INLINE {
        @Override
        public Set<Method> apply(Property property) {

            Set<Method> result = new LinkedHashSet<Method>();
            TypeDef def = BuilderContextManager.getContext().getRepository().getBuildable(property.getTypeRef());
            JavaType baseType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());

            JavaType typeWithUnboundedGenerics = TypeAs.REMOVE_GENERICS_BOUNDS.apply(baseType);

            for (Method constructor : getInlineableConstructors(property)) {
                String ownPrefix = property.getType().isCollection() ? "addNew" : "withNew";
                String ownName = ownPrefix + captializeFirst(property.getType().isCollection()
                        ? singularize(property.getName())
                        : property.getName());

                String delegatePrefix = property.getType().isCollection() ? "addTo" : "with";
                String delegateName = delegatePrefix + captializeFirst(property.getName());

                String args = StringUtils.join(constructor.getArguments(), new Function<Property, String>() {
                    @Override
                    public String apply(Property item) {
                        return item.getName();
                    }
                }, ", ");
                
                result.add(new MethodBuilder()
                        .addToModifiers(Modifier.PUBLIC)
                        .withReturnType(T)
                        .withArguments(constructor.getArguments())
                        .withName(ownName)
                        .withTypeParameters(baseType.getGenericTypes())
                        .addToAttributes(BODY, "return " + delegateName + "(new " + typeWithUnboundedGenerics.getSimpleName() + "(" + args + "));")
                        .build());
            }

            return result;
        }
    };


}
