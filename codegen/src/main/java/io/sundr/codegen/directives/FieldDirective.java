/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.codegen.directives;

import static io.sundr.model.Attributeable.INIT;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import io.sundr.codegen.Constants;
import io.sundr.codegen.DefinitionScope;
import io.sundr.model.Property;

public class FieldDirective extends Directive {

  @Override
  public String getName() {
    return "field";
  }

  @Override
  public int getType() {
    return LINE;
  }

  @Override
  public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException {
    String block = "";
    Property field = null;
    //reading params
    if (node.jjtGetChild(0) != null) {
      field = (Property) node.jjtGetChild(0).value(context);
    }
    writeField(writer, field, block);
    return true;
  }

  private void writeField(Writer writer, Property field, String block) throws IOException {
    if (field != null) {
      writer.append(field.render(DefinitionScope.get()));

      if (field.getAttribute(INIT) != null) {
        writer.append(" = ").append(getDefaultValue(field));
      }
    }
    writer.append(";\n");
  }

  private String getDefaultValue(Property field) {
    Object value = field.getAttribute(INIT);

    if (Constants.STRING_REF.equals(field.getTypeRef()) && !String.valueOf(value).startsWith("\"")) {
      return "\"" + value + "\"";
    } else {
      return String.valueOf(value);
    }
  }
}
