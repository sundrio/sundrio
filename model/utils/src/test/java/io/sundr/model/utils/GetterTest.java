/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.model.utils;

import io.sundr.model.Field;
import io.sundr.model.FieldBuilder;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class GetterTest {
  
  @Test
  public void recordGetterWithSameSuffix() {
    TypeDef rec = new TypeDefBuilder(TypeDef.forName("java.lang.Record")).build();

    Field f1 = new FieldBuilder().withName("field").withTypeRef(Types.BOOLEAN_REF).build();
    Field f2 = new FieldBuilder().withName("unfield").withTypeRef(Types.BOOLEAN_REF).build();
    
    Method m1 = new MethodBuilder().withName("field").withReturnType(Types.BOOLEAN_REF).build();
    Method m2 = new MethodBuilder().withName("unfield").withReturnType(Types.BOOLEAN_REF).build();

    TypeDef type = new TypeDefBuilder(TypeDef.forName("MyRecord"))
        .withExtendsList(rec.toReference())
        .withFields(f1, f2)
        .withMethods(m2, m1)
        .build();
        
    assertEquals(m1, Getter.find(type, f1));
    assertEquals(m2, Getter.find(type, f2));
  }
}
