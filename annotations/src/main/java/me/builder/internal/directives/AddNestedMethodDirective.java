package me.builder.internal.directives;

import me.codegen.model.JavaProperty;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.Writer;

public class AddNestedMethodDirective extends Directive {
    
    private static final String FORMAT = "\n\tpublic %s %s() {\n\t\treturn new %s();\n\t}\n";

    @Override
    public String getName() {
        return "addNestedMethod";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException {
        JavaProperty property = (JavaProperty) node.jjtGetChild(0).value(context);
        String methodName = "add" + property.getNameCapitalized();
        String type = property.getNameCapitalized() + "Nested<T>";
        writer.write(String.format(FORMAT, type, methodName, type));
        return true;
    }
}
