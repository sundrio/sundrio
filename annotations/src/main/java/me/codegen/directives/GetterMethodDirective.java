package me.codegen.directives;

import me.codegen.model.JavaProperty;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.Writer;

public class GetterMethodDirective extends Directive {
    
    private static final String FORMAT = "\n\tpublic %s %s() {\n\t\treturn this.%s;\n\t}\n";
    @Override
    public String getName() {
        return "getterMethod";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        JavaProperty property = (JavaProperty) node.jjtGetChild(0).value(context);
        String prefix = property.getType().isBoolean() ? "is" : "get";
        String methodName = prefix + property.getNameCapitalized();
        String type = property.getType().getSimpleName();
        if (property.isArray()) {
            type += "[]";
        }
        String name = property.getName();
        writer.write(String.format(FORMAT, type, methodName, name));
        return true;
    }
}
