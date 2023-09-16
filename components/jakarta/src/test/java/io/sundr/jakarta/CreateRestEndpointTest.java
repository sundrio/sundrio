package io.sundr.jakarta;

import org.junit.Test;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.Adapters;
import io.sundr.model.TypeDef;

public class CreateRestEndpointTest {

  @Test
  public void testCreateRestEndpoint() {
    TypeDef def = Adapters.adaptType(MyEntity.class, AdapterContext.getContext());
    TypeDef result = CreateRestEndpoint.fromEntity(def);
    System.out.println(result.render());
  }
}
