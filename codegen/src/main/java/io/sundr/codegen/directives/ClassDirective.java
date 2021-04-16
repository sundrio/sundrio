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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;

import io.sundr.codegen.model.TypeDef;

public class ClassDirective extends Directive {

  private static final String MEMBER_OF = "MEMBER_OF";
  private static final String ABSTRACT = " abstract ";
  private static final String STATIC = " static ";
  private static final String FINAL = " final ";
  private static final String EXTENDS = " extends ";
  private static final String IMPLEMENTS = " implements ";
  private static final String PUBLIC = "public ";
  private static final String CLASS = "class";
  private static final String SPACE = " ";
  private static final String COMMA = ",";
  private static final String NEWLINE = "\n";
  private static final String BRACKETS_LR = "[]";

  private static final String SQUIGGLE_L = "{";
  private static final String SQUIGGLE_R = "}";

  private static final String LT = "<";
  private static final String GT = ">";

  @Override
  public String getName() {
    return CLASS;
  }

  @Override
  public int getType() {
    return BLOCK;
  }

  @Override
  public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException {
    String block = "";
    TypeDef clazz = null;
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      if (node.jjtGetChild(i) != null) {
        if (!(node.jjtGetChild(i) instanceof ASTBlock)) {
          //reading and casting inline parameters
          if (i == 0) {
            clazz = (TypeDef) node.jjtGetChild(i).value(context);
          } else {
            break;
          }
        } else {
          //reading block content and rendering it
          try {
            StringWriter blockContent = new StringWriter();
            node.jjtGetChild(i).render(context, blockContent);

            block = blockContent.toString();
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        }
      }
    }
    writeClazz(writer, clazz, block);
    return true;
  }

  private void writeClazz(Writer writer, TypeDef type, String block) throws IOException {
    if (type != null) {
      writer.append(type.render());
      writer.append(block).append(NEWLINE);
    }
  }
}
