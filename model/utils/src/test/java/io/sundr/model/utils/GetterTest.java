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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import io.sundr.model.Field;
import io.sundr.model.FieldBuilder;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;

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

  @Test
  public void getterForEscapedKeywordField() {
    Field field = field("_continue", Types.STRING_REF);
    Method getter = method("getContinue", Types.STRING_REF);

    assertEquals(getter, Getter.find(type(field, getter), field));
  }

  @Test
  public void booleanGetterForEscapedKeywordField() {
    Field field = field("_native", Types.PRIMITIVE_BOOLEAN_REF);
    Method getter = method("isNative", Types.PRIMITIVE_BOOLEAN_REF);

    assertEquals(getter, Getter.find(type(field, getter), field));
  }

  @Test
  public void getterForNonAlphaField() {
    Field field = field("$ref", Types.STRING_REF);
    Method getter = method("get$ref", Types.STRING_REF);

    assertEquals(getter, Getter.find(type(field, getter), field));
  }

  @Test
  public void getterKeepingTheEscapedNameOfAFieldStartingWithADigit() {
    Field field = field("_1h", Types.STRING_REF);
    Method getter = method("get_1h", Types.STRING_REF);

    assertEquals(getter, Getter.find(type(field, getter), field));
  }

  @Test
  public void getterOfAnotherFieldWithTheSameSuffixIsIgnored() {
    Field field = field("_continue", Types.STRING_REF);
    Method other = method("getAutoContinue", Types.STRING_REF);
    Method getter = method("getContinue", Types.STRING_REF);

    assertEquals(getter, Getter.find(type(field, other, getter), field));
  }

  @Test
  public void getterClosestToTheFieldNameWinsRegardlessOfOrder() {
    Field field = field("_id", Types.STRING_REF);
    Method normalized = method("getId", Types.STRING_REF);
    Method getter = method("get_id", Types.STRING_REF);

    assertEquals(getter, Getter.find(type(field, normalized, getter), field));
    assertEquals(getter, Getter.find(type(field, getter, normalized), field));
  }

  @Test
  public void bareGetIsNotAGetterOfAFieldWithoutAlphanumericCharacters() {
    Field field = field("__", Types.STRING_REF);
    Method bare = method("get", Types.STRING_REF);
    Method getter = method("get__", Types.STRING_REF);

    assertEquals(getter, Getter.find(type(field, bare, getter), field));
    assertFalse(Getter.findOptional(type(field, bare), field).isPresent());
  }

  private static Field field(String name, TypeRef type) {
    return new FieldBuilder().withName(name).withTypeRef(type).build();
  }

  private static Method method(String name, TypeRef returnType) {
    return new MethodBuilder().withName(name).withReturnType(returnType).build();
  }

  private static TypeDef type(Field field, Method... methods) {
    return new TypeDefBuilder(TypeDef.forName("MyType"))
        .withFields(field)
        .withMethods(methods)
        .build();
  }
}
