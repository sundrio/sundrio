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

package io.sundr.examples.validation;

import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class AddressValidationTest {

    @Test(expected = ConstraintViolationException.class)
    public void testWithNullStreet() {
        Address address = new AddressBuilder().build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testWithZeroNumber() {
        Address address = new AddressBuilder().withStreet("Sesame").withNumber(0).build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testWithAlphanumericZipCode() {
        Address address = new AddressBuilder().withStreet("Sesame")
                .withNumber(1)
                .withZipCode("abcd")
                .build();
    }


    @Test(expected = ConstraintViolationException.class)
    public void testWithLongZipCode() {
        Address address = new AddressBuilder().withStreet("Sesame")
                .withNumber(1)
                .withZipCode("1234567")
                .build();
    }

    @Test
    public void testWithValid() {
        Address address = new AddressBuilder().withStreet("Sesame")
                .withNumber(1)
                .withZipCode("1234")
                .build();
    }

}
