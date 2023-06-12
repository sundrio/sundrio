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

package io.sundr.adapter.testing.person;

import java.util.List;
import java.util.Optional;

public class Person {

  private String name;
  private Optional<String> middleName;
  private String lastName;
  private Type type;
  private List<Address> addresses;

  public enum Type {
    A_MINUS, A_PLUS, B_MINUS, B_PLUS, AB_MINUS, AB_PLUS, O_MINUS, O_PLUS;

    static final Type getDefault() {
      return A_PLUS;
    }
  }
}
