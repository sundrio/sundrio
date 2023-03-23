package io.sundr.builder.internal.functions;

import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.model.utils.Collections.HASH_MAP;
import static io.sundr.model.utils.Collections.LIST;
import static io.sundr.model.utils.Collections.MAP;
import static io.sundr.model.utils.Types.STRING_REF;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import io.sundr.model.ClassRef;
import io.sundr.model.TypeParamRefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.utils.Optionals;

public class TypeAsTest {

  @Before
  public void setUp() throws Exception {
    DefinitionRepository.getRepository().register(MAP);
    DefinitionRepository.getRepository().register(LIST);
    DefinitionRepository.getRepository().register(HASH_MAP);
  }

  @Test
  public void shouldUnwrapCollection() {
    ClassRef ref = LIST.toReference(STRING_REF);
    TypeRef unwrapped = UNWRAP_COLLECTION_OF.apply(ref);
    assertEquals(unwrapped, STRING_REF);
  }

  @Test
  public void shouldUnwrapCollectionOfOptionals() {
    ClassRef optionalString = Optionals.OPTIONAL.toReference(STRING_REF);
    ClassRef ref = LIST.toReference(optionalString);
    TypeRef unwrapped = UNWRAP_COLLECTION_OF.apply(ref);
    assertEquals(unwrapped, optionalString);
  }

  @Test
  public void shouldUnwrapCollectionOfOptionalParamRef() {
    ClassRef optionalRef = Optionals.OPTIONAL.toReference(new TypeParamRefBuilder().withName("N").build());
    ClassRef ref = LIST.toReference(optionalRef);
    TypeRef unwrapped = UNWRAP_COLLECTION_OF.apply(ref);
    assertEquals(unwrapped, optionalRef);
  }
}
