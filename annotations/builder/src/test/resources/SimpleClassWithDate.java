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

package testpackage;

import io.sundr.builder.annotations.Buidlable;

import java.util.List;

public class SimpleClassWithDate extends SimpleClass {

    private final Date date;

    @Buildable
    public SimpleClass(Long id, String name, List<String> tags, Boolean enabled, Date date) {
        super(id, name, tags, enabled);
        this.date=date;
    }

    public Date getDate() {
        return date;
    }
}
