package io.sundr.model.utils;

import static io.sundr.model.utils.Collections.K;
import static io.sundr.model.utils.Collections.V;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.repo.DefinitionRepository;

// Credits to: Dusan Jakub (xRodney).
public class TypeArgumentsTest {
  public static final TypeDef MULTI_HASH_MAP = new TypeDefBuilder(TypeDef.forName("com.example.MultiHashMap"))
      .withKind(Kind.CLASS)
      .withParameters(K, V)
      .withExtendsList(Collections.HASH_MAP.toReference(K.toReference(), Collections.LIST.toReference(V.toReference())))
      .build();

  @Test
  public void shouldNotCauseStackOverflow() {
    DefinitionRepository.getRepository().register(Collections.MAP);
    DefinitionRepository.getRepository().register(Collections.HASH_MAP);
    DefinitionRepository.getRepository().register(Collections.LIST);
    DefinitionRepository.getRepository().register(Types.STRING);
    DefinitionRepository.getRepository().register(Types.INT);
    DefinitionRepository.getRepository().register(MULTI_HASH_MAP);

    ClassRef classRef = MULTI_HASH_MAP.toReference(Types.STRING_REF, Types.INT_REF);
    TypeDef typeDef = GetDefinition.of(classRef);
    TypeDef richDef = TypeArguments.apply(GetDefinition.of(classRef));

    System.out.println(typeDef.render());
    System.out.println(richDef.render());

    // This goes beyond the purpose of the tests, which is to check against SO.
    // Still, it's nice to know that the rendered code is not affected (for THIS scenario).
    assertEquals(typeDef.render(), richDef.render());
  }
}
