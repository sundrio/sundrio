package io.sundr.builder.internal.checks;

import org.junit.Test;

import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.utils.Types;

/**
 * DublicatePropertyCheckTest
 */
public class DuplicatePropertyCheckTest {

  @Test(expected = IllegalStateException.class)
  public void shouldThrowException() {
    TypeDef def = new TypeDefBuilder()
        .withPackageName("my.pkg")
        .withName("SomeClass")
        .withKind(Kind.CLASS)
        .addNewProperty()
        .withName("myProperty")
        .withTypeRef(Types.STRING_REF)
        .endProperty()
        .addNewProperty()
        .withName("my_property")
        .withTypeRef(Types.STRING_REF)
        .endProperty()
        .accept(new DuplicatePropertyCheck())
        .build();
  }

  @Test
  public void shouldNotThrowExceptionWithEnum() {
    new TypeDefBuilder()
        .withPackageName("my.pkg")
        .withName("SomeEnum")
        .withKind(Kind.ENUM)
        .addNewProperty()
        .withName("myProperty")
        .withTypeRef(Types.STRING_REF)
        .endProperty()
        .addNewProperty()
        .withName("my_property")
        .withTypeRef(Types.STRING_REF)
        .endProperty()
        .accept(new DuplicatePropertyCheck())
        .build();
  }
}
