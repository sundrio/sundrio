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

package io.sundr.builder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BaseFluent<F extends Fluent<F>> implements Fluent<F>, Visitable<F> {

    private static final String VISIT = "visit";

    public final List<Visitable> _visitables = new ArrayList<Visitable>();

    public static <T> ArrayList<T> build(List<? extends Builder<? extends T>> list) {
        ArrayList<T> result = new ArrayList<T>();
        for (Builder<? extends T> builder : list) {
            result.add(builder.build());
        }
        return result;
    }

    public static <T> List<T> build(Set<? extends Builder<? extends T>> list) {
        List<T> result = new ArrayList<T>();
        for (Builder<? extends T> builder : list) {
            result.add(builder.build());
        }
        return result;
    }

    public static <T> ArrayList<T> aggregate(List<? extends T> ...lists) {
        ArrayList<T> result = new ArrayList<T>();

        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    public static <T> LinkedHashSet<T> aggregate(Set<? extends T> ...sets) {
        LinkedHashSet<T> result = new LinkedHashSet<T>();

        for (Set<? extends T> set : sets) {
            result.addAll(set);
        }
        return result;
    }


    private static <V, F> Boolean canVisit(V visitor, F fluent) {
        if (visitor instanceof TypedVisitor) {
            if (!((TypedVisitor) visitor).getType().isAssignableFrom(fluent.getClass())) {
                return false;
            }
        }

        if (visitor instanceof PathAwareTypedVisitor) {
            PathAwareTypedVisitor pathAwareTypedVisitor = (PathAwareTypedVisitor) visitor;
            Class parentType = pathAwareTypedVisitor.getParentType();
            Class actaulParentType = pathAwareTypedVisitor.getActualParentType();
            if (!parentType.isAssignableFrom(actaulParentType)) {
                return false;
            }
        }

        return hasCompatibleVisitMethod(visitor, fluent);
    }

    /**
     * Checks if the specified visitor has a visit method compatible with the specified fluent.
     * @param visitor
     * @param fluent
     * @param <V>
     * @param <F>
     * @return
     */
    private static <V,F> Boolean hasCompatibleVisitMethod(V visitor, F fluent) {
        for (Method method : visitor.getClass().getMethods()) {
            if (!method.getName().equals(VISIT) || method.getParameterTypes().length != 1) {
                continue;
            }
            Class visitorType = method.getParameterTypes()[0];
            if (visitorType.isAssignableFrom(fluent.getClass())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public F accept(Visitor visitor) {
        if (visitor instanceof PathAwareTypedVisitor) {
            return acceptPathAware((PathAwareTypedVisitor) visitor);
        } else {
            return acceptInternal(visitor);
        }
    }

    private F acceptInternal(Visitor visitor) {
        for (Visitable visitable : _visitables) {
            visitable.accept(visitor);
        }

        if (canVisit(visitor, this)) {
            visitor.visit(this);
        }
        return (F) this;
    }


    private F acceptPathAware(PathAwareTypedVisitor pathAwareTypedVisitor) {
        return acceptInternal(pathAwareTypedVisitor.next(this));
    }
}
