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

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

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
    assertNotNull(userDtoClass, "UserDto class should be generated");

    // Create an instance to verify it's instantiable
    Object userDto = userDtoClass.newInstance();
    assertNotNull(userDto, "UserDto should be instantiable");
  }

  @Test
  public void testUserDtoHasExpectedFields() throws Exception {
    Class<?> userDtoClass = Class.forName("io.sundr.examples.dto.model.UserDto");

    // Check that basic fields are present
    Field idField = userDtoClass.getDeclaredField("id");
    assertEquals(Long.class, idField.getType(), "ID field should be Long");

    Field firstNameField = userDtoClass.getDeclaredField("firstName");
    assertEquals(String.class, firstNameField.getType(), "firstName field should be String");

    Field lastNameField = userDtoClass.getDeclaredField("lastName");
    assertEquals(String.class, lastNameField.getType(), "lastName field should be String");

    Field emailField = userDtoClass.getDeclaredField("email");
    assertEquals(String.class, emailField.getType(), "email field should be String");

    Field ageField = userDtoClass.getDeclaredField("age");
    assertEquals(int.class, ageField.getType(), "age field should be int");
  }

  @Test
  public void testEntityReferenceReplacedWithId() throws Exception {
    Class<?> userDtoClass = Class.forName("io.sundr.examples.dto.model.UserDto");

    // Verify that Department entity reference is replaced with departmentId
    Field departmentIdField = userDtoClass.getDeclaredField("departmentId");
    assertEquals(Long.class, departmentIdField.getType(), "departmentId field should be Long");

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
    assertEquals(0, userDtoClass.getAnnotations().length, "DTO class should have no annotations");

    // Verify field annotations are removed
    Field emailField = userDtoClass.getDeclaredField("email");
    assertEquals(0, emailField.getAnnotations().length, "email field should have no annotations");

    Field firstNameField = userDtoClass.getDeclaredField("firstName");
    assertEquals(0, firstNameField.getAnnotations().length, "firstName field should have no annotations");
  }
}
