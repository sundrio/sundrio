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

public class ModifierSupportBuilder extends ModifierSupportFluentImpl<ModifierSupportBuilder>
    implements VisitableBuilder<ModifierSupport, ModifierSupportBuilder> {

  ModifierSupportFluent<?> fluent;
  Boolean validationEnabled;

  public ModifierSupportBuilder() {
    this(true);
  }

  public ModifierSupportBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent) {
    this(fluent, true);
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, ModifierSupport instance) {
    this(fluent, instance, true);
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, ModifierSupport instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withModifiers(instance.getModifiers());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(ModifierSupport instance) {
    this(instance, true);
  }

  public ModifierSupportBuilder(ModifierSupport instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public EditableModifierSupport build() {
    EditableModifierSupport buildable = new EditableModifierSupport(fluent.getModifiers(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ModifierSupportBuilder that = (ModifierSupportBuilder) o;
    if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
      return false;

    if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
      return false;
    return true;
  }

}
