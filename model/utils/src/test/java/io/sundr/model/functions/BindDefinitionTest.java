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

package io.sundr.model.functions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.repo.DefinitionRepository;

public class BindDefinitionTest {

  TypeParamDef T = new TypeParamDefBuilder().withName("T").build();

  TypeDef target = new TypeDefBuilder()
      .withKind(Kind.CLASS)
      .withPackageName("my.pkg")
      .withName("Target")
      .withParameters(T)
      // private T type;
      .addNewProperty()
      .withName("type")
      .withTypeRef(T.toReference())
      .endProperty()
      // public T getType();
      .addNewMethod()
      .withNewModifiers().withPublic().endModifiers()
      .withName("getType")
      .withReturnType(T.toReference())
      .endMethod()
      .build();

  ClassRef string = ClassRef.forName(String.class.getName());

  @Before
  public void setUp() {
    DefinitionRepository.getRepository().register(target);
  }

  @Test
  public void shouldTranslateProperty() {
    TypeDef bound = BindDefinition.of(target.toReference(string));
    assertNotNull(bound);
    assertTrue(bound.getParameters().isEmpty());
    Optional<Property> type = bound.getProperties().stream().filter(p -> "type".equals(p.getName())).findFirst();
    assertTrue(type.isPresent());
    type.ifPresent(p -> {
      assertEquals(string, p.getTypeRef());
    });
  }

  @Test
  public void shouldTranslateMethod() {
    ClassRef string = ClassRef.forName(String.class.getName());
    TypeDef bound = BindDefinition.of(target.toReference(string));
    assertNotNull(bound);
    assertTrue(bound.getParameters().isEmpty());
    Optional<Method> getType = bound.getMethods().stream().filter(p -> "getType".equals(p.getName())).findFirst();
    assertTrue(getType.isPresent());
    getType.ifPresent(p -> {
      assertEquals(string, p.getReturnType());
    });
  }
}
