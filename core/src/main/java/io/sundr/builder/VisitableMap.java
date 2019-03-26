/*
 *      Copyright 2019 The original authors.
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class VisitableMap extends HashMap<String, List<Visitable>> implements Iterable<Visitable> {

    @Override
    public List<Visitable> get(Object key) {
        if (!containsKey(key)) {
           put(String.valueOf(key), new ArrayList());
        }
        return super.get(key);
    }

    public List<Visitable> aggregate()  {
        ArrayList<Visitable> all = new ArrayList();
        for (Collection<Visitable> list : this.values()) {
            all.addAll(list);
        }
       return all;
    }

    @Override
    public Iterator<Visitable> iterator() {
       return aggregate().iterator();
    }

    @Override
    public void forEach(Consumer<? super Visitable> action) {
        aggregate().forEach(action);
    }

    @Override
    public Spliterator<Visitable> spliterator() {
        return aggregate().spliterator();
    }

}
