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

import io.sundr.codegen.model.Method;
import io.sundr.codegen.utils.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

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
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            if (node.jjtGetChild(i) != null) {
                if (!(node.jjtGetChild(i) instanceof ASTBlock)) {
                    //reading and casting inline parameters
                    if (i == 0) {
                        method = (Method) node.jjtGetChild(i).value(context);
                    } else if (i == 1) {
                        isInterface = (Boolean) node.jjtGetChild(i).value(context);
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
        writeMethod(writer, method, block, isInterface);
        return true;
    }

    private void writeMethod(Writer writer, Method method, String block, Boolean isInterface) throws IOException {
        if (method != null) {
            writer.append(method.toString());
            if (!isInterface && !StringUtils.isNullOrEmpty(block)) {
                writer.append("{\n");
                writer.append(block).append("}\n");
            } else {
                writer.append(";");
            }
        }
    }
}
