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

import io.sundr.codegen.model.TypeDef;
import java.io.StringWriter;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import java.io.IOException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.context.InternalContextAdapter;
import java.io.Writer;
import org.apache.velocity.runtime.parser.node.Node;
import io.sundr.codegen.functions.Pluralize;


public class PluralizeDirective extends Directive {
    
    private static final String PLURALIZE = "pluralize";

    @Override
    public String getName() {
        return PLURALIZE;
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException {
        String word = null;
        if (node.jjtGetChild(0) != null) {
            word = String.valueOf(node.jjtGetChild(0).value(context));
        }

        //truncate and write result to writer
        writer.write(Pluralize.FUNCTION.apply(word));
        return true;
    }

}
