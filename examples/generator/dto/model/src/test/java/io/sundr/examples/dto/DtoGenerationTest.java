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

package io.sundr.examples.dto;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 * Test that demonstrates the DTO generation functionality.
 *
 * This test verifies that:
 * 1. The UserDto class is generated
 * 2. JPA annotations are removed from the generated DTO
 * 3. Entity references are replaced with ID fields
 */
public class DtoGenerationTest {

  @Test
  public void testUserDtoGenerated() throws Exception {
    // Verify that UserDto class was generated
    Class<?> userDtoClass = Class.forName("io.sundr.examples.dto.model.UserDto");
    assertNotNull("UserDto class should be generated", userDtoClass);

    // Create an instance to verify it's instantiable
    Object userDto = userDtoClass.newInstance();
    assertNotNull("UserDto should be instantiable", userDto);
  }

  @Test
  public void testUserDtoHasExpectedFields() throws Exception {
    Class<?> userDtoClass = Class.forName("io.sundr.examples.dto.model.UserDto");

    // Check that basic fields are present
    Field idField = userDtoClass.getDeclaredField("id");
    assertEquals("ID field should be Long", Long.class, idField.getType());

    Field firstNameField = userDtoClass.getDeclaredField("firstName");
    assertEquals("firstName field should be String", String.class, firstNameField.getType());

    Field lastNameField = userDtoClass.getDeclaredField("lastName");
    assertEquals("lastName field should be String", String.class, lastNameField.getType());

    Field emailField = userDtoClass.getDeclaredField("email");
    assertEquals("email field should be String", String.class, emailField.getType());

    Field ageField = userDtoClass.getDeclaredField("age");
    assertEquals("age field should be int", int.class, ageField.getType());
  }

  @Test
  public void testEntityReferenceReplacedWithId() throws Exception {
    Class<?> userDtoClass = Class.forName("io.sundr.examples.dto.model.UserDto");

    // Verify that Department entity reference is replaced with departmentId
    Field departmentIdField = userDtoClass.getDeclaredField("departmentId");
    assertEquals("departmentId field should be Long", Long.class, departmentIdField.getType());

    // Verify that original department field doesn't exist
    try {
      userDtoClass.getDeclaredField("department");
      fail("department field should not exist in DTO");
    } catch (NoSuchFieldException e) {
      // Expected - the department field should be replaced with departmentId
    }
  }

  @Test
  public void testAnnotationsRemoved() throws Exception {
    Class<?> userDtoClass = Class.forName("io.sundr.examples.dto.model.UserDto");

    // Verify class-level annotations are removed
    assertEquals("DTO class should have no annotations", 0, userDtoClass.getAnnotations().length);

    // Verify field annotations are removed
    Field emailField = userDtoClass.getDeclaredField("email");
    assertEquals("email field should have no annotations", 0, emailField.getAnnotations().length);

    Field firstNameField = userDtoClass.getDeclaredField("firstName");
    assertEquals("firstName field should have no annotations", 0, firstNameField.getAnnotations().length);
  }
}
