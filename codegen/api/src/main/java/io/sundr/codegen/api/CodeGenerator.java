/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.codegen.api;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import io.sundr.utils.Predicates;

public class CodeGenerator<T> {

  private final Class<T> type;
  private final Function<T, Writer> output;
  private final Function<T, String> identifier;
  private final Function<T, String> renderer;
  private final Predicate<T> skip;
  private final Consumer<T> onSkip;

  private final Consumer<T> ignore = t -> {
  };
  private final Set<String> generated = new HashSet<>();

  public static class Builder<T> {

    private final Class<T> type;
    private final Function<T, Writer> output;
    private final Function<T, String> identifier;
    private final Function<T, String> renderer;
    private final Predicate<T> skip;
    private final Consumer<T> onSkip;

    private Builder(Class<T> type) {
      this(type, null, null, null, null, null);
    }

    private Builder(Class<T> type, Function<T, Writer> output, Function<T, String> identifier, Function<T, String> renderer,
        Predicate<T> skip,
        Consumer<T> onSkip) {
      this.type = type;
      this.output = output;
      this.identifier = identifier;
      this.renderer = renderer;
      this.skip = skip;
      this.onSkip = onSkip;
    }

    public Builder<T> withOutput(Output<T> output) {
      return withOutput(output.getFunction());
    }

    public Builder<T> withOutput(Function<T, Writer> output) {
      return new Builder<>(type, output, identifier, renderer, skip, onSkip);
    }

    public Builder<T> withIdentifier(Identifier<T> identifier) {
      return withIdentifier(identifier.getFunction());
    }

    public Builder<T> withIdentifier(Function<T, String> identifier) {
      return new Builder<>(type, output, identifier, renderer, skip, onSkip);
    }

    public Builder<T> withRenderer(Renderer<T> renderer) {
      return withRenderer(renderer.getFunction());
    }

    public Builder<T> withRenderer(Function<T, String> renderer) {
      return new Builder<>(type, output, identifier, renderer, skip, onSkip);
    }

    public Builder<T> skipping(Predicate<T> skip) {
      return new Builder<>(type, output, identifier, renderer, skip, onSkip);
    }

    public CodeGenerator<T> build() {
      return new CodeGenerator<>(type, output, identifier, renderer, skip, onSkip);
    }

    public boolean generate(T... items) {
      return build().generate(items);
    }
  }

  private CodeGenerator(Class<T> type, Function<T, Writer> output, Function<T, String> identifier, Function<T, String> renderer,
      Predicate<T> skip, Consumer<T> onSkip) {
    this.type = type;
    this.output = output != null ? output : new SystemOutput<T>().getFunction();
    this.identifier = identifier != null ? identifier
        : Identifiers.findIdentifier(type).map(Identifier::getFunction).orElse(o -> String.valueOf(o.hashCode()));
    this.renderer = renderer != null ? renderer : Renderers.findRenderer(type).map(Renderer::getFunction).orElse(null);
    this.skip = skip != null ? skip : Predicates.distinct(t -> identifier.apply(t));
    this.onSkip = onSkip != null ? onSkip : ignore;
  }

  public static <T> Builder<T> newGenerator(Class<T> type) {
    return new Builder<T>(type);
  }

  /**
   * Generate one or more items
   * 
   * @param <T> the type of items to generate
   * @param items the items to generate
   * @return true if generation was succesful, false otherwise
   */
  public boolean generate(T... items) {
    if (renderer == null) {
      throw new IllegalStateException("Renderer should not be null.");
    }

    if (output == null) {
      throw new IllegalStateException("Output should not be null.");
    }

    //Function like skip, onSkip, writer etc may need to access the specified Identifier.
    //So, let's wrap all code that may need the identifier into a lambda and ensure that the Identifiers is accessible to the lambda
    //using Indentifier.getIdentifiers().
    return Identifiers.withIdentifier(identifier).call(() -> {
      for (T item : items) {
        String id = identifier.apply(item);
        if (skip.test(item)) {
          onSkip.accept(item);
          continue;
        }
        //Only generate each file once ...
        if (generated.contains(id)) {
          continue;
        }
        try (Writer writer = output.apply(item)) {
          writer.write(renderer.apply(item));
          generated.add(id);
        } catch (IOException e) {
          return false;
        }
      }
      return true;
    });

  }
}
