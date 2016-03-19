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

package io.sundr.dsl.internal.graph.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.dsl.internal.graph.Node;
import io.sundr.dsl.internal.processor.DslRepository;
import io.sundr.dsl.internal.type.functions.Combine;
import io.sundr.dsl.internal.type.functions.Generify;
import io.sundr.dsl.internal.utils.JavaTypeUtils;

import java.util.LinkedHashSet;
import java.util.Set;
import static io.sundr.dsl.internal.Constants.IS_TRANSITION;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isTransition;

public class ToTransition implements Function<Node<JavaClazz>, JavaClazz> {

    private final DslRepository repository;

    public ToTransition(DslRepository repository) {
        this.repository = repository;
    }

    public JavaClazz apply(Node<JavaClazz> current) {
        if (current.getTransitions().isEmpty()) {
            return current.getItem();
        } else {
            JavaClazz clazz = current.getItem();
            Set<JavaClazz> toCombine = new LinkedHashSet<JavaClazz>();

            for (Node<JavaClazz> v : current.getTransitions()) {
                toCombine.add(apply(v));
            }

            JavaClazz nextClazz = toCombine.size() == 1
                    ? toCombine.iterator().next()
                    : Combine.FUNCTION.apply(Generify.FUNCTION.apply(toCombine));

            if (JavaTypeUtils.isCardinalityMultiple(clazz)) {
                //1st pass create the self ref
                JavaClazz selfRef = transition(clazz, nextClazz);
                Set<JavaClazz> toReCombine = new LinkedHashSet<JavaClazz>(toCombine);
                toReCombine.add(selfRef);
                JavaClazz reCombined = Combine.FUNCTION.apply(toReCombine);

                //2nd pass recreate the combination
                selfRef = transition(clazz, reCombined);
                toReCombine = new LinkedHashSet<JavaClazz>(toCombine);
                toReCombine.add(selfRef);
                reCombined = Combine.FUNCTION.apply(toReCombine);
                repository.register(reCombined);
                repository.register(nextClazz);
                return transition(clazz, reCombined);
            } else {
                //If we have a couple of classes to combine that are non-multiple
                // we may end up with intermediate garbage in the registry, which are masking the real thing
                if (!isTransition(nextClazz)) {
                    repository.register(nextClazz);
                }
                return transition(clazz, nextClazz);
            }
        }
    }

    public static JavaClazz transition(JavaClazz from, JavaClazz to) {
        return new JavaClazzBuilder(from)
                .withNewTypeLike(from.getType())
                    .withGenericTypes(to.getType())
                    .addToAttributes(IS_TRANSITION, true)
                .endType()
                .build();

    }
}
