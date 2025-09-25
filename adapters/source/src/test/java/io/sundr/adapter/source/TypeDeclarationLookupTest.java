package io.sundr.adapter.source;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.javaparser.ast.body.TypeDeclaration;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.TypeLookup;
import io.sundr.model.TypeDef;

public class TypeDeclarationLookupTest {

  @Test
  public void testTypeDeclarationLookup() {
    TypeLookup lookup = new TypeDeclarationLookup();
    Optional<TypeDeclaration> typeDeclaration = lookup.forName("io.sundr.adapter.source.Project");
    assertTrue(typeDeclaration.isPresent());
  }

  @Test
  public void testTypeLookup() {
    Optional<TypeDef> def = TypeLookup.lookup("io.sundr.adapter.source.Project", AdapterContext.getContext());
    assertTrue(def.isPresent());
  }
}
