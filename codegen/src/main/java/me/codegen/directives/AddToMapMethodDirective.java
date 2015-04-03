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

public class AddToMapMethodDirective extends Directive {

    private static final String FORMAT = "\n\tpublic T %s(%s key, %s value) {\n\t\tthis.%s.put(key, value);\n\t\treturn (T) this;\n\t}\n";
    @Override
    public String getName() {
        return "addToMapMethod";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException {
        JavaProperty property = (JavaProperty) node.jjtGetChild(0).value(context);
        String type = property.getType().getClassName();
        if (property.getType().isCollection() && type.equals("Map")) {
            String keyType = property.getType().getGenericTypes()[0].getClassName();
            String valueType = property.getType().getGenericTypes()[1].getClassName();
            String methodName = "add" + property.getNameCapitalized();
            String name = property.getName();
            writer.write(String.format(FORMAT, methodName, keyType,  valueType, name));
        }
        return true;
    }
}
