package io.sundr.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FamilyTest {

  @Test
  public void testPersonBulder() {
    Family family = new FamilyBuilder()
        .withNewFather()
        .withFirstName("John")
        .withLastName("Doe")
        .endFather()
        .withNewMother()
        .withFirstName("Jane")
        .withLastName("Doe")
        .endMother()
        .addNewChild()
        .withFirstName("Jimmy")
        .withLastName("Doe")
        .endChild()
        .build();

    assertEquals("John", family.getFather().getFirstName());
  }
}
