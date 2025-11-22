package io.sundr.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

public class TypeDefBuilderTest {

  @Test
  public void testWithExtendsListVarArgs() {
    ClassRef baseClass = new ClassRef("java.lang.Object", 0,
        java.util.Collections.emptyList(),
        java.util.Collections.emptyMap());

    TypeDef typeDef = new TypeDefBuilder()
        .withKind(Kind.CLASS)
        .withPackageName("test")
        .withName("TestClass")
        .withExtendsList(baseClass)
        .build();

    assertNotNull(typeDef);
    assertNotNull(typeDef.getExtendsList());
    assertEquals(1, typeDef.getExtendsList().size());
    assertEquals("Object", typeDef.getExtendsList().get(0).getName());
  }

  @Test
  public void testWithExtendsListWithTypeParameters() {
    ClassRef stringRef = new ClassRef("java.lang.String", 0,
        java.util.Collections.emptyList(),
        java.util.Collections.emptyMap());

    ClassRef baseClass = new ClassRef("test.BaseClass", 0,
        java.util.Arrays.asList(stringRef),
        java.util.Collections.emptyMap());

    TypeDef typeDef = new TypeDefBuilder()
        .withKind(Kind.CLASS)
        .withPackageName("test")
        .withName("TestClass")
        .withExtendsList(baseClass)
        .build();

    assertNotNull(typeDef);
    assertNotNull(typeDef.getExtendsList());
    assertEquals(1, typeDef.getExtendsList().size());
    assertEquals("BaseClass", typeDef.getExtendsList().get(0).getName());
    assertEquals(1, typeDef.getExtendsList().get(0).getArguments().size());
  }

  @Test
  public void testToReferenceWithTypeArguments() {
    TypeParamDef t = new TypeParamDefBuilder()
        .withName("T")
        .build();

    TypeDef baseFluent = new TypeDefBuilder()
        .withKind(Kind.CLASS)
        .withPackageName("io.sundr.builder")
        .withName("BaseFluent")
        .withParameters(t)
        .build();

    ClassRef a = ClassRef.forName("test.A");

    ClassRef reference = baseFluent.toReference(Arrays.asList(a));

    assertNotNull(reference);
    assertEquals("io.sundr.builder.BaseFluent", reference.getFullyQualifiedName());
    assertEquals(1, reference.getArguments().size());
    assertEquals("A", reference.getArguments().get(0).getName());
  }
}
