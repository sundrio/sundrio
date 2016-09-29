package io.sundr.codegen.model;

import io.sundr.builder.Editable;
import java.util.Map;
import java.lang.String;
import java.lang.Object;

public class EditableAnnotationRef extends AnnotationRef implements Editable<AnnotationRefBuilder>{


    public EditableAnnotationRef(ClassRef classRef,Map<String,Object> parameters,Map<String,Object> attributes){
            super(classRef, parameters, attributes);
    }

    public AnnotationRefBuilder edit(){
            return new AnnotationRefBuilder(this);
    }




}
