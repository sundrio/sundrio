package io.sundr.model.visitors.context.resolver;

import static io.sundr.model.utils.Resolvers.createResolutionMap;

import java.util.Map;

import io.sundr.builder.Visitable;
import io.sundr.builder.Visitor;
import io.sundr.model.Expression;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeDefFluent;

/**
 * Visitor that visits TypeDefs and registers a ContextRefResolverVisitor for each of the TypeDef properties.
 */
public class TypeDefContextRefResolver implements Visitor<TypeDefFluent<?>> {

  @Override
  public void visit(TypeDefFluent<?> fluent) {
    if (fluent instanceof Visitable<?>) {
      Visitable<?> visitable = (Visitable<?>) fluent;
      Map<String, Expression> resolutionMap = createResolutionMap(fluent.buildProperties());
      visitable.accept(new LocalVariableResolver(resolutionMap));

      visitable.accept(new MethodArgumentsResolver(resolutionMap));
      visitable.accept(new ScopeResolver(resolutionMap));
      visitable.accept(new MethodCallArgumentsResolver(resolutionMap));
    }

    if (fluent instanceof TypeDefBuilder) {
      TypeDefBuilder builder = (TypeDefBuilder) fluent;
      TypeDef typeDef = builder.build();
      builder.accept(new StaticMethodContextRefResolver(typeDef.getImports(), typeDef.getPackageName()));
      builder.accept(new PropertyRefContextRefResolver(typeDef.getImports(), typeDef.getPackageName()));
    }
  }
}
