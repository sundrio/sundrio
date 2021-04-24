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
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;

import io.sundr.codegen.DefinitionScope;
import io.sundr.model.Method;

public class MethodDirective extends Directive {

  @Override
  public String getName() {
    return "method";
  }

  @Override
  public int getType() {
    return BLOCK;
  }

  @Override
  public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException {
    String block = "";
    Method method = null;
    Boolean isInterface = false;
    int level = 1;
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      if (node.jjtGetChild(i) != null) {
        if (!(node.jjtGetChild(i) instanceof ASTBlock)) {
          //reading and casting inline parameters
          if (i == 0) {
            method = (Method) node.jjtGetChild(i).value(context);
          } else if (i == 1) {
            isInterface = (Boolean) node.jjtGetChild(i).value(context);
          } else if (i == 2) {
            level = (Integer) node.jjtGetChild(i).value(context);
          } else {
            break;
          }
        } else {
          //reading block content and rendering it
          StringWriter blockContent = new StringWriter();
          node.jjtGetChild(i).render(context, blockContent);

          block = blockContent.toString();
          break;
        }
      }
    }
    boolean hasBody = !method.isAbstract() && (!isInterface || method.isDefaultMethod());
    writeMethod(writer, method, block, hasBody, level);
    return true;
  }

  private void writeMethod(Writer writer, Method method, String block, Boolean hasBody, int level) throws IOException {
    if (method != null) {
      String onetab = tab(level * 4);
      String twotabs = tab((level + 1) * 4);
      writer.append(onetab).append(method.renderSignature(DefinitionScope.get()));
      List<String> lines = getLines(block);
      List<String> indentedLines = lines.stream().map(l -> twotabs + l).collect(Collectors.toList());
      if (hasBody) {
        writer.append(" {").append(System.lineSeparator());
        for (String line : indentedLines) {
          writer.append(line).append(System.lineSeparator());
        }
        writer.append(onetab).append("}").append(System.lineSeparator());
      } else {
        writer.append(";");
      }
    }
  }

  private static List<String> getLines(String block) {
    List<String> lines = Arrays.stream(block.split("\r|\n")).collect(Collectors.toList());
    return lines;
  }

  private static int getIndentationLevel(List<String> lines) {
    TreeSet<Integer> sizes = new TreeSet(lines.stream().map(MethodDirective::level).sorted().collect(Collectors.toSet()));
    int min = sizes.first();
    int max = sizes.last();
    if (Math.abs(max - min) % 4 == 0) {
      return 4;
    } else
      return 2;
  }

  private static int getIndentationBase(List<String> lines) {
    TreeSet<Integer> sizes = new TreeSet<>(lines.stream().map(String::length).sorted().collect(Collectors.toSet()));
    int min = sizes.first();
    return min;
  }

  private static int level(String line) {
    int level = 0;
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == ' ') {
        level++;
      } else {
        return level;
      }
    }
    return level;
  }

  private static String tab(int level) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < level; i++) {
      sb.append(" ");
    }
    return sb.toString();
  }
}
