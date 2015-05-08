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
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.io.Writer;

import static io.sundr.codegen.utils.StringUtils.join;

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
        JavaProperty field = null;
        //reading params
        if (node.jjtGetChild(0) != null) {
            field = (JavaProperty) node.jjtGetChild(0).value(context);
        }
        writeField(writer, field, block);
        return true;
    }

    private void writeField(Writer writer, JavaProperty field, String block) throws IOException {
        if (field != null) {
            writer.append(JavaPropertyToString.INSTANCE.apply(field));
            if (field.getType().getDefaultImplementation() != null) {
                JavaType defaultImpl = field.getType().getDefaultImplementation();
                writer.append(" = new ").append(JavaTypeToString.INSTANCE.apply(defaultImpl)).append("()");
            }
        }
        writer.append(";");
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
