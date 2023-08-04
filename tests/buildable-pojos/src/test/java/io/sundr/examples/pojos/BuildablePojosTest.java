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
package io.sundr.examples.pojos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.sundr.examples.config.ArmoredVehicle;
import io.sundr.examples.config.ArmoredVehicleBuilder;

public class BuildablePojosTest {

  @Test
  public void hasRegularPojos() {
    final Vehicle vehicle = new VehicleBuilder()
        .withNewManufacturer().withName("John Deere").endManufacturer().build();
    assertEquals("John Deere", vehicle.getManufacturer().getName());
  }

  @Test
  public void hasGeneratedPojos() {
    final Tractor vehicle = new TractorBuilder()
        .withNewManufacturer().withName("John Deere").endManufacturer()
        .withTransmission("hydrostatic")
        .build();
    assertEquals("John Deere", vehicle.getManufacturer().getName());
    assertEquals("hydrostatic", vehicle.getTransmission());
  }

  @Test
  public void hasGeneratedPojosWithOverlappingFields() {
    final ArmoredVehicle vehicle = new ArmoredVehicleBuilder()
        .withNewManufacturer().withName("Ford").endManufacturer()
        .withArmorLevel(10)
        .build();
    assertEquals("Ford", vehicle.getManufacturer().getName());
    assertEquals(10L, (long) vehicle.getArmorLevel());
  }
}
