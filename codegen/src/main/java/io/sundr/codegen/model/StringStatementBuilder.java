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

package io.sundr.codegen.model;

import io.sundr.builder.VisitableBuilder;

public class StringStatementBuilder extends StringStatementFluentImpl<StringStatementBuilder>
    implements VisitableBuilder<StringStatement, StringStatementBuilder> {

  StringStatementFluent<?> fluent;
  Boolean validationEnabled;

  public StringStatementBuilder() {
    this(true);
  }

  public StringStatementBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent) {
    this(fluent, true);
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, StringStatement instance) {
    this(fluent, instance, true);
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, StringStatement instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withProvider(instance.getProvider());
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(StringStatement instance) {
    this(instance, true);
  }

  public StringStatementBuilder(StringStatement instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withProvider(instance.getProvider());
    this.validationEnabled = validationEnabled;
  }

  public EditableStringStatement build() {
    EditableStringStatement buildable = new EditableStringStatement(fluent.getProvider());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    StringStatementBuilder that = (StringStatementBuilder) o;
    if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
      return false;

    if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
      return false;
    return true;
  }

}
