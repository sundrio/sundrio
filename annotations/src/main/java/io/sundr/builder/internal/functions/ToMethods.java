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
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.utils.StringUtils;

import javax.lang.model.element.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.T;
import static io.sundr.builder.internal.utils.BuilderUtils.getInlineableConstructors;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.codegen.utils.StringUtils.singularize;

public enum ToMethods implements Function<JavaProperty, Set<JavaMethod>> {

   WITH_NESTED_INLINE {
        @Override
        public Set<JavaMethod> apply(JavaProperty property) {

            Set<JavaMethod> result = new LinkedHashSet<>();
            JavaClazz clazz = PropertyAs.CLASS.apply(property);
            
            for (JavaMethod constructor : getInlineableConstructors(property)) {
                String ownPrefix = property.getType().isCollection() ? "addNew" : "withNew";
                String ownName = ownPrefix + captializeFirst(property.getType().isCollection()
                        ? singularize(property.getName())
                        : property.getName());

                String delegatePrefix = property.getType().isCollection() ? "addTo" : "with";
                String delegateName = delegatePrefix + captializeFirst(property.getName());

                String args = StringUtils.join(constructor.getArguments(), new Function<JavaProperty, String>() {
                    @Override
                    public String apply(JavaProperty item) {
                        return item.getName();
                    }
                }, ", ");
                
                result.add(new JavaMethodBuilder()
                        .addToModifiers(Modifier.PUBLIC)
                        .withReturnType(T)
                        .withArguments(constructor.getArguments())
                        .withName(ownName)
                        .addToAttributes(BODY, "return " + delegateName + "(new " + clazz.getType().getSimpleName() + "(" + args + "));")
                        .build());
            }

            return result;
        }
    };


}
