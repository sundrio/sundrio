package io.sundr.builder.internal.checks;

import org.junit.Test;

import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.utils.Types;

/**
 * DublicateFieldCheckTest
 */
public class DuplicateFieldCheckTest {

  @Test(expected = IllegalStateException.class)
  public void shouldThrowException() {
    TypeDef def = new TypeDefBuilder()
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
        .build();
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
