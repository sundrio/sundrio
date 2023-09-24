/*
 *      Copyright 2019 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WildcardRef extends TypeRef {

  public enum BoundKind {
    EXTENDS("? extends %s"), SUPER("? super %s");

    String format;

    BoundKind(String format) {
      this.format = format;
    }

    String getFormat() {
      return format;
    }
  }

  private final BoundKind boundKind;
  private final List<TypeRef> bounds;

  public WildcardRef() {
    this(BoundKind.EXTENDS, Collections.<TypeRef> emptyList(), Collections.<AttributeKey, Object> emptyMap());
  }

  public WildcardRef(BoundKind boundKind, List<TypeRef> bounds, Map<AttributeKey, Object> attributes) {
    super(attributes);
    this.boundKind = boundKind;
    this.bounds = bounds;
  }

  public String getName() {
    return "?";
  }

  public List<TypeRef> getBounds() {
    return bounds;
  }

  public BoundKind getBoundKind() {
    return boundKind;
  }

  public int getDimensions() {
    return 0;
  }

  public TypeRef withDimensions(int dimensions) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    if (bounds == null || bounds.isEmpty()) {
      sb.append("?");
    } else {
      sb.append(
          String.format(boundKind.format, bounds.stream().map(b -> b.render()).collect(Collectors.joining(COMA))));
    }
    return sb.toString();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (bounds == null || bounds.isEmpty()) {
      sb.append("?");
    } else {
      sb.append(String.format(boundKind.format, bounds.stream().map(Object::toString).collect(Collectors.joining(COMA))));
    }
    return sb.toString();
  }
}
