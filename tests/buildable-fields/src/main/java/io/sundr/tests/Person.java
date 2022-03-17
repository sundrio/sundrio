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

package io.sundr.tests;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.sundr.builder.annotations.Buildable;

/**
 * A simple Person Pojo that demonstrates how to hide a field, using constructor arguments.
 */
@Buildable
public class Person {

  private final String firstName;
  private final String lastName;
  private final Date birthDate;
  private final long age;

  public Person(String firstName, String lastName, Date birthDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.age = birthDate != null
        ? TimeUnit.DAYS.convert(new Date().getTime() - birthDate.getTime(), TimeUnit.MICROSECONDS) / 365
        : 0;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public long getAge() {
    return age;
  }

}
