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

package io.sundr.validation.internal.processor;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;

import javax.tools.JavaFileObject;

import org.junit.jupiter.api.Test;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.JavaFileObjects;

public class ValidationProcessorTest {

  @Test
  public void shouldGenerateValidatorForStaticMethod() {
    JavaFileObject validationClass = JavaFileObjects.forSourceString("test.PersonValidations",
        "package test;\n" +
            "import io.sundr.validation.annotations.Validation;\n" +
            "import io.sundr.validation.ValidationError;\n" +
            "import java.util.List;\n" +
            "import java.util.ArrayList;\n" +
            "public class PersonValidations {\n" +
            "  @Validation\n" +
            "  public static List<ValidationError> validateName(Person person) {\n" +
            "    List<ValidationError> errors = new ArrayList<>();\n" +
            "    if (person.getName() == null) {\n" +
            "      errors.add(new ValidationError(\"name\", \"must not be null\"));\n" +
            "    }\n" +
            "    return errors;\n" +
            "  }\n" +
            "}\n");

    JavaFileObject personClass = JavaFileObjects.forSourceString("test.Person",
        "package test;\n" +
            "public class Person {\n" +
            "  private String name;\n" +
            "  public String getName() { return name; }\n" +
            "  public void setName(String name) { this.name = name; }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new ValidationProcessor())
        .compile(validationClass, personClass);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.PersonValidator")
        .contentsAsUtf8String()
        .contains("public static List<ValidationError> validate(Person item)");
    assertThat(compilation)
        .generatedSourceFile("test.PersonValidator")
        .contentsAsUtf8String()
        .contains("PersonValidations.validateName(item)");
  }

  @Test
  public void shouldGenerateValidatorForInstanceMethod() {
    JavaFileObject validationClass = JavaFileObjects.forSourceString("test.PersonValidations",
        "package test;\n" +
            "import io.sundr.validation.annotations.Validation;\n" +
            "import io.sundr.validation.ValidationError;\n" +
            "import java.util.List;\n" +
            "import java.util.ArrayList;\n" +
            "public class PersonValidations {\n" +
            "  public PersonValidations() {}\n" +
            "  @Validation\n" +
            "  public List<ValidationError> validateName(Person person) {\n" +
            "    List<ValidationError> errors = new ArrayList<>();\n" +
            "    if (person.getName() == null) {\n" +
            "      errors.add(new ValidationError(\"name\", \"must not be null\"));\n" +
            "    }\n" +
            "    return errors;\n" +
            "  }\n" +
            "}\n");

    JavaFileObject personClass = JavaFileObjects.forSourceString("test.Person",
        "package test;\n" +
            "public class Person {\n" +
            "  private String name;\n" +
            "  public String getName() { return name; }\n" +
            "  public void setName(String name) { this.name = name; }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new ValidationProcessor())
        .compile(validationClass, personClass);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.PersonValidator")
        .contentsAsUtf8String()
        .contains("validateName(item)");
  }

  @Test
  public void shouldGenerateValidatorForThrowingMethod() {
    JavaFileObject validationClass = JavaFileObjects.forSourceString("test.PersonValidations",
        "package test;\n" +
            "import io.sundr.validation.annotations.Validation;\n" +
            "import io.sundr.validation.ValidationException;\n" +
            "import io.sundr.validation.ValidationError;\n" +
            "import java.util.Collections;\n" +
            "public class PersonValidations {\n" +
            "  @Validation\n" +
            "  public static Person validatePerson(Person person) {\n" +
            "    if (person.getName() == null) {\n" +
            "      throw new ValidationException(Collections.singletonList(\n" +
            "        new ValidationError(\"name\", \"must not be null\")));\n" +
            "    }\n" +
            "    return person;\n" +
            "  }\n" +
            "}\n");

    JavaFileObject personClass = JavaFileObjects.forSourceString("test.Person",
        "package test;\n" +
            "public class Person {\n" +
            "  private String name;\n" +
            "  public String getName() { return name; }\n" +
            "  public void setName(String name) { this.name = name; }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new ValidationProcessor())
        .compile(validationClass, personClass);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.PersonValidator")
        .contentsAsUtf8String()
        .contains("catch (ValidationException e)");
  }

  @Test
  public void shouldOrchestrateMultipleValidationMethods() {
    JavaFileObject validationClass = JavaFileObjects.forSourceString("test.PersonValidations",
        "package test;\n" +
            "import io.sundr.validation.annotations.Validation;\n" +
            "import io.sundr.validation.ValidationError;\n" +
            "import java.util.List;\n" +
            "import java.util.ArrayList;\n" +
            "public class PersonValidations {\n" +
            "  @Validation\n" +
            "  public static List<ValidationError> validateName(Person person) {\n" +
            "    List<ValidationError> errors = new ArrayList<>();\n" +
            "    if (person.getName() == null) {\n" +
            "      errors.add(new ValidationError(\"name\", \"must not be null\"));\n" +
            "    }\n" +
            "    return errors;\n" +
            "  }\n" +
            "  @Validation\n" +
            "  public static List<ValidationError> validateAge(Person person) {\n" +
            "    List<ValidationError> errors = new ArrayList<>();\n" +
            "    if (person.getAge() < 0) {\n" +
            "      errors.add(new ValidationError(\"age\", \"must be non-negative\"));\n" +
            "    }\n" +
            "    return errors;\n" +
            "  }\n" +
            "}\n");

    JavaFileObject personClass = JavaFileObjects.forSourceString("test.Person",
        "package test;\n" +
            "public class Person {\n" +
            "  private String name;\n" +
            "  private int age;\n" +
            "  public String getName() { return name; }\n" +
            "  public void setName(String name) { this.name = name; }\n" +
            "  public int getAge() { return age; }\n" +
            "  public void setAge(int age) { this.age = age; }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new ValidationProcessor())
        .compile(validationClass, personClass);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.PersonValidator")
        .contentsAsUtf8String()
        .contains("validateName(item)");
    assertThat(compilation)
        .generatedSourceFile("test.PersonValidator")
        .contentsAsUtf8String()
        .contains("validateAge(item)");
  }

  @Test
  public void shouldErrorWhenMethodHasNoParameters() {
    JavaFileObject validationClass = JavaFileObjects.forSourceString("test.PersonValidations",
        "package test;\n" +
            "import io.sundr.validation.annotations.Validation;\n" +
            "import io.sundr.validation.ValidationError;\n" +
            "import java.util.List;\n" +
            "public class PersonValidations {\n" +
            "  @Validation\n" +
            "  public static List<ValidationError> validatePerson() {\n" +
            "    return null;\n" +
            "  }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new ValidationProcessor())
        .compile(validationClass);

    assertThat(compilation).hadErrorContaining("must have exactly one parameter");
  }

  @Test
  public void shouldErrorWhenInstanceMethodWithoutNoArgConstructor() {
    JavaFileObject validationClass = JavaFileObjects.forSourceString("test.PersonValidations",
        "package test;\n" +
            "import io.sundr.validation.annotations.Validation;\n" +
            "import io.sundr.validation.ValidationError;\n" +
            "import java.util.List;\n" +
            "import java.util.ArrayList;\n" +
            "public class PersonValidations {\n" +
            "  private final String config;\n" +
            "  public PersonValidations(String config) { this.config = config; }\n" +
            "  @Validation\n" +
            "  public List<ValidationError> validateName(Person person) {\n" +
            "    return new ArrayList<>();\n" +
            "  }\n" +
            "}\n");

    JavaFileObject personClass = JavaFileObjects.forSourceString("test.Person",
        "package test;\n" +
            "public class Person {\n" +
            "  private String name;\n" +
            "  public String getName() { return name; }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new ValidationProcessor())
        .compile(validationClass, personClass);

    assertThat(compilation).hadErrorContaining("public no-arg constructor");
  }

  @Test
  public void shouldErrorWhenMethodHasInvalidReturnType() {
    JavaFileObject validationClass = JavaFileObjects.forSourceString("test.PersonValidations",
        "package test;\n" +
            "import io.sundr.validation.annotations.Validation;\n" +
            "public class PersonValidations {\n" +
            "  @Validation\n" +
            "  public static String validatePerson(Person person) {\n" +
            "    return null;\n" +
            "  }\n" +
            "}\n");

    JavaFileObject personClass = JavaFileObjects.forSourceString("test.Person",
        "package test;\n" +
            "public class Person {\n" +
            "  private String name;\n" +
            "  public String getName() { return name; }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new ValidationProcessor())
        .compile(validationClass, personClass);

    assertThat(compilation).hadErrorContaining("must return List<ValidationError>");
  }

  @Test
  public void shouldGenerateValidatorInSamePackageAsTargetType() {
    JavaFileObject validationClass = JavaFileObjects.forSourceString("validations.PersonValidations",
        "package validations;\n" +
            "import io.sundr.validation.annotations.Validation;\n" +
            "import io.sundr.validation.ValidationError;\n" +
            "import java.util.List;\n" +
            "import java.util.Collections;\n" +
            "import model.Person;\n" +
            "public class PersonValidations {\n" +
            "  @Validation\n" +
            "  public static List<ValidationError> validateName(Person person) {\n" +
            "    return Collections.emptyList();\n" +
            "  }\n" +
            "}\n");

    JavaFileObject personClass = JavaFileObjects.forSourceString("model.Person",
        "package model;\n" +
            "public class Person {\n" +
            "  private String name;\n" +
            "  public String getName() { return name; }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new ValidationProcessor())
        .compile(validationClass, personClass);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("model.PersonValidator");
  }
}
