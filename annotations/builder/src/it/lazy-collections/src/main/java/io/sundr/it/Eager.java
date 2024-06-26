
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

package io.sundr.it;

import java.util.List;
import java.util.Set;
import io.sundr.builder.annotations.Buildable;

@Buildable(lazyCollectionInitEnabled=false)
public class Eager {

    private final List<String> list;
    private final Set<String> set;

  public Eager(List<String> list, Set<String> set) {
        this.list = list;
        this.set = set;
    }

    public List<String> getList() {
        return this.list;
    }

    public Set<String> getSet() {
      return set;
    }
}
