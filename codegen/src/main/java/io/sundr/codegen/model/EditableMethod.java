package io.sundr.codegen.model;

import java.lang.String;
import io.sundr.builder.Editable;
import java.lang.Object;
import java.util.List;
import java.util.Map;

public class EditableMethod extends Method implements Editable<MethodBuilder> {


    public EditableMethod(List<String> comments,List<AnnotationRef> annotations,List<TypeParamDef> parameters,String name,TypeRef returnType,List<Property> arguments,boolean varArgPreferred,List<ClassRef> exceptions,boolean defaultMethod,Block block,int modifiers,Map<AttributeKey,Object> attributes) { 
        super(comments, annotations, parameters, name, returnType, arguments, varArgPreferred, exceptions, defaultMethod, block, modifiers, attributes);
    }


    public MethodBuilder edit() {
        return new MethodBuilder(this);
    }

}
