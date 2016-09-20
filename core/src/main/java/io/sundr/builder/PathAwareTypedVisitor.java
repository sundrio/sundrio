/*
 *      Copyright 2016 The original authors.
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

package io.sundr.builder;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathAwareTypedVisitor<V> extends TypedVisitor<V> {

    private List<Object> path;
    private final PathAwareTypedVisitor<V> delegate;

    public PathAwareTypedVisitor() {
        this.path = new ArrayList<Object>();
        this.delegate = this;
    }

    public PathAwareTypedVisitor(List<Object> path) {
        this.path = path;
        this.delegate = this;
    }

    public PathAwareTypedVisitor(List<Object> path, PathAwareTypedVisitor<V> delegate) {
        this.path = path;
        this.delegate = delegate;
    }


    public PathAwareTypedVisitor<V> next(Object item) {
        List<Object> path = new ArrayList<Object>(this.path);
        path.add(item);
        return new PathAwareTypedVisitor<V>(path, this);
    }

    @Override
    public void visit(V element) {
        delegate.path = path;
        delegate.visit(element);
    }

    public Object getParent() {
        int size = path.size();
        int parentIndex = size - 2;
        if (parentIndex >= 0) {
            return path.get(parentIndex);
        } else {
            return null;
        }
    }

    public List<Object> getPath() {
        return Collections.unmodifiableList(path);
    }

    @Override
    public Class<V> getType() {
        Class<V> superType = super.getType();
        if (superType != null) {
            return superType;
        }
        return delegate.getType();
    }
}
