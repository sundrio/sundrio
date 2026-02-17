package io.sundr.builder.internal.checks;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import io.sundr.model.Kind;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.utils.Types;

/**
 * DublicateFieldCheckTest
 */
public class DuplicateFieldCheckTest {

  @Test
  public void shouldThrowException() {
    assertThrows(IllegalStateException.class, () -> new TypeDefBuilder()
        .withPackageName("my.pkg")
        .withName("SomeClass")
        .withKind(Kind.CLASS)
        .addNewField()
        .withName("myField")
        .withTypeRef(Types.STRING_REF)
        .endField()
        .addNewField()
        .withName("my_field")
        .withTypeRef(Types.STRING_REF)
        .endField()
        .accept(new DuplicateFieldCheck())
        .build());
  }

  @Test
  public void shouldNotThrowExceptionWithEnum() {
    new TypeDefBuilder()
        .withPackageName("my.pkg")
        .withName("SomeEnum")
        .withKind(Kind.ENUM)
        .addNewField()
        .withName("myField")
        .withTypeRef(Types.STRING_REF)
        .endField()
        .addNewField()
        .withName("my_field")
        .withTypeRef(Types.STRING_REF)
        .endField()
        .accept(new DuplicateFieldCheck())
        .build();
  }
}
