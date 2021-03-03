package io.sundr.codegen.model;

import java.lang.Object;
import java.lang.String;
import java.util.List;
import java.util.Map;

import io.sundr.builder.Editable;

public class EditableProperty extends Property implements Editable<PropertyBuilder> {

  public EditableProperty(List<AnnotationRef> annotations, TypeRef typeRef, String name, List<String> comments, int modifiers,
      Map<AttributeKey, Object> attributes) {
    super(annotations, typeRef, name, comments, modifiers, attributes);
  }

  public PropertyBuilder edit() {
    return new PropertyBuilder(this);
  }

}
