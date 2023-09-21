package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class TypeDefBuilder extends TypeDefFluent<TypeDefBuilder> implements VisitableBuilder<TypeDef, TypeDefBuilder> {
  public TypeDefBuilder() {
    this(false);
  }

  public TypeDefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent) {
    this(fluent, false);
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent, TypeDef instance) {
    this(fluent, instance, false);
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent, TypeDef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withKind(instance.getKind());
      fluent.withPackageName(instance.getPackageName());
      fluent.withName(instance.getName());
      fluent.withComments(instance.getComments());
      fluent.withAnnotations(instance.getAnnotations());
      fluent.withExtendsList(instance.getExtendsList());
      fluent.withImplementsList(instance.getImplementsList());
      fluent.withParameters(instance.getParameters());
      fluent.withProperties(instance.getProperties());
      fluent.withConstructors(instance.getConstructors());
      fluent.withMethods(instance.getMethods());
      fluent.withOuterTypeName(instance.getOuterTypeName());
      fluent.withInnerTypes(instance.getInnerTypes());
      fluent.withModifiers(instance.getModifiers());
      fluent.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  public TypeDefBuilder(TypeDef instance) {
    this(instance, false);
  }

  public TypeDefBuilder(TypeDef instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withKind(instance.getKind());
      this.withPackageName(instance.getPackageName());
      this.withName(instance.getName());
      this.withComments(instance.getComments());
      this.withAnnotations(instance.getAnnotations());
      this.withExtendsList(instance.getExtendsList());
      this.withImplementsList(instance.getImplementsList());
      this.withParameters(instance.getParameters());
      this.withProperties(instance.getProperties());
      this.withConstructors(instance.getConstructors());
      this.withMethods(instance.getMethods());
      this.withOuterTypeName(instance.getOuterTypeName());
      this.withInnerTypes(instance.getInnerTypes());
      this.withModifiers(instance.getModifiers());
      this.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  TypeDefFluent<?> fluent;
  Boolean validationEnabled;

  public TypeDef build() {
    TypeDef buildable = new TypeDef(fluent.getKind(), fluent.getPackageName(), fluent.getName(), fluent.getComments(),
        fluent.buildAnnotations(), fluent.buildExtendsList(), fluent.buildImplementsList(), fluent.buildParameters(),
        fluent.buildProperties(), fluent.buildConstructors(), fluent.buildMethods(), fluent.getOuterTypeName(),
        fluent.buildInnerTypes(), fluent.buildModifiers(), fluent.getAttributes());
    return buildable;
  }

}
