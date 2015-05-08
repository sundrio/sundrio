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

import io.sundr.Function;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.utils.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;


import static io.sundr.codegen.utils.StringUtils.join;

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
        JavaMethod method = null;
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            if (node.jjtGetChild(i) != null) {
                if (!(node.jjtGetChild(i) instanceof ASTBlock)) {
                    //reading and casting inline parameters
                    if (i == 0) {
                        method = (JavaMethod) node.jjtGetChild(i).value(context);
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
        writeMethod(writer, method, block);
        return true;
    }

    private void writeMethod(Writer writer, JavaMethod method, String block) throws IOException {
        if (method != null) {

            writer.append(join(method.getModifiers(), ModifierToString.INSTANCE, " ")).append(" ");
            if (method.getTypeParameters() != null && !method.getTypeParameters().isEmpty()) {
                writer.append("<")
                        .append(StringUtils.join(method.getTypeParameters(), JavaTypeToString.INSTANCE, ", "))
                        .append("> ");
            }

            writer.append(method.getReturnType().getSimpleName())
                    .append(" ")
                    .append(method.getName())
                    .append("(")
                    .append(join(method.getArguments(), JavaPropertyToString.INSTANCE, ", "))
                    .append(")");

            writeExceptions(writer, method);
            if (!StringUtils.isNullOrEmpty(block)) {
                writer.append("{\n");
                writer.append(block).append("}\n");
            } else {
                writer.append(";");
            }
        }
    }

    private void writeExceptions(Writer writer, JavaMethod method) throws IOException {
        if (method.getExceptions().size() > 0) {
            writer.append(" throws ").append(join(method.getExceptions(), ""));
        }

    }
    //Enum Singleton
    private enum ModifierToString implements Function<Modifier, String> {
        INSTANCE;

        public String apply(Modifier modifier) {
            return modifier.name().toLowerCase();
        }
    }

    //Enum Singleton
    private enum JavaTypeToString implements Function<JavaType, String> {
        INSTANCE;

        @Override
        public String apply(JavaType item) {
            return item.getSimpleName();
        }
    }

    //Enum Singleton
    private enum JavaPropertyToString implements Function<JavaProperty, String> {
        INSTANCE;

        @Override
        public String apply(JavaProperty item) {
            StringBuilder sb = new StringBuilder();
            sb.append(join(item.getModifiers(), ModifierToString.INSTANCE, " ")).append(" ");
            sb.append(item.getType().getSimpleName()).append(" ").append(item.getName());
            return sb.toString();
        }
    }
}
