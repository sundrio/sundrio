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

import java.util.LinkedList;
import java.util.HashSet;
import io.sundr.builder.annotations.Buildable;

@Buildable
public class Concrete {

  private final LinkedList<Thing> list;
    private final HashSet<Thing> set;

  public Concrete(LinkedList<Thing> list, HashSet<Thing> set) {
        this.list = list;
                this.set = set;
    }

    public LinkedList<Thing> getList() {
        return this.list;
    }

    public HashSet<Thing> getSet() {
      return set;
    }
}
