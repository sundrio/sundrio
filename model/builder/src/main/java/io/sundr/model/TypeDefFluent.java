package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class TypeDefFluent<A extends TypeDefFluent<A>> extends ModifierSupportFluent<A> {

  private ArrayList<AnnotationRefBuilder> annotations = new ArrayList<AnnotationRefBuilder>();
  private List<String> comments = new ArrayList<String>();
  private ArrayList<MethodBuilder> constructors = new ArrayList<MethodBuilder>();
  private ArrayList<ClassRefBuilder> extendsList = new ArrayList<ClassRefBuilder>();
  private ArrayList<ClassRefBuilder> implementsList = new ArrayList<ClassRefBuilder>();
  private ArrayList<TypeDefBuilder> innerTypes = new ArrayList<TypeDefBuilder>();
  private Kind kind;
  private ArrayList<MethodBuilder> methods = new ArrayList<MethodBuilder>();
  private String name;
  private String outerTypeName;
  private String packageName;
  private ArrayList<TypeParamDefBuilder> parameters = new ArrayList<TypeParamDefBuilder>();
  private ArrayList<PropertyBuilder> properties = new ArrayList<PropertyBuilder>();

  public TypeDefFluent() {
  }

  public TypeDefFluent(TypeDef instance) {
    this.copyInstance(instance);
  }

  public A addAllToAnnotations(Collection<AnnotationRef> items) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").add(builder);
      this.annotations.add(builder);
    }
    return (A) this;
  }

  public A addAllToComments(Collection<String> items) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    for (String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public A addAllToConstructors(Collection<Method> items) {
    if (this.constructors == null) {
      this.constructors = new ArrayList<MethodBuilder>();
    }
    for (Method item : items) {
      MethodBuilder builder = new MethodBuilder(item);
      _visitables.get("constructors").add(builder);
      this.constructors.add(builder);
    }
    return (A) this;
  }

  public A addAllToExtendsList(Collection<ClassRef> items) {
    if (this.extendsList == null) {
      this.extendsList = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("extendsList").add(builder);
      this.extendsList.add(builder);
    }
    return (A) this;
  }

  public A addAllToImplementsList(Collection<ClassRef> items) {
    if (this.implementsList == null) {
      this.implementsList = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("implementsList").add(builder);
      this.implementsList.add(builder);
    }
    return (A) this;
  }

  public A addAllToInnerTypes(Collection<TypeDef> items) {
    if (this.innerTypes == null) {
      this.innerTypes = new ArrayList<TypeDefBuilder>();
    }
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("innerTypes").add(builder);
      this.innerTypes.add(builder);
    }
    return (A) this;
  }

  public A addAllToMethods(Collection<Method> items) {
    if (this.methods == null) {
      this.methods = new ArrayList<MethodBuilder>();
    }
    for (Method item : items) {
      MethodBuilder builder = new MethodBuilder(item);
      _visitables.get("methods").add(builder);
      this.methods.add(builder);
    }
    return (A) this;
  }

  public A addAllToParameters(Collection<TypeParamDef> items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public A addAllToProperties(Collection<Property> items) {
    if (this.properties == null) {
      this.properties = new ArrayList<PropertyBuilder>();
    }
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").add(builder);
      this.properties.add(builder);
    }
    return (A) this;
  }

  public AnnotationsNested<A> addNewAnnotation() {
    return new AnnotationsNested(-1, null);
  }

  public AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item) {
    return new AnnotationsNested(-1, item);
  }

  public ConstructorsNested<A> addNewConstructor() {
    return new ConstructorsNested(-1, null);
  }

  public ConstructorsNested<A> addNewConstructorLike(Method item) {
    return new ConstructorsNested(-1, item);
  }

  public ExtendsListNested<A> addNewExtendsList() {
    return new ExtendsListNested(-1, null);
  }

  public ExtendsListNested<A> addNewExtendsListLike(ClassRef item) {
    return new ExtendsListNested(-1, item);
  }

  public ImplementsListNested<A> addNewImplementsList() {
    return new ImplementsListNested(-1, null);
  }

  public ImplementsListNested<A> addNewImplementsListLike(ClassRef item) {
    return new ImplementsListNested(-1, item);
  }

  public InnerTypesNested<A> addNewInnerType() {
    return new InnerTypesNested(-1, null);
  }

  public A addNewInnerType(String fullyQualifiedName) {
    return (A) addToInnerTypes(new TypeDef(fullyQualifiedName));
  }

  public InnerTypesNested<A> addNewInnerTypeLike(TypeDef item) {
    return new InnerTypesNested(-1, item);
  }

  public MethodsNested<A> addNewMethod() {
    return new MethodsNested(-1, null);
  }

  public MethodsNested<A> addNewMethodLike(Method item) {
    return new MethodsNested(-1, item);
  }

  public ParametersNested<A> addNewParameter() {
    return new ParametersNested(-1, null);
  }

  public ParametersNested<A> addNewParameterLike(TypeParamDef item) {
    return new ParametersNested(-1, item);
  }

  public PropertiesNested<A> addNewProperty() {
    return new PropertiesNested(-1, null);
  }

  public PropertiesNested<A> addNewPropertyLike(Property item) {
    return new PropertiesNested(-1, item);
  }

  public A addToAnnotations(AnnotationRef... items) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").add(builder);
      this.annotations.add(builder);
    }
    return (A) this;
  }

  public A addToAnnotations(int index, AnnotationRef item) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
    if (index < 0 || index >= annotations.size()) {
      _visitables.get("annotations").add(builder);
      annotations.add(builder);
    } else {
      _visitables.get("annotations").add(builder);
      annotations.add(index, builder);
    }
    return (A) this;
  }

  public A addToComments(String... items) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    for (String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public A addToComments(int index, String item) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    this.comments.add(index, item);
    return (A) this;
  }

  public A addToConstructors(Method... items) {
    if (this.constructors == null) {
      this.constructors = new ArrayList<MethodBuilder>();
    }
    for (Method item : items) {
      MethodBuilder builder = new MethodBuilder(item);
      _visitables.get("constructors").add(builder);
      this.constructors.add(builder);
    }
    return (A) this;
  }

  public A addToConstructors(int index, Method item) {
    if (this.constructors == null) {
      this.constructors = new ArrayList<MethodBuilder>();
    }
    MethodBuilder builder = new MethodBuilder(item);
    if (index < 0 || index >= constructors.size()) {
      _visitables.get("constructors").add(builder);
      constructors.add(builder);
    } else {
      _visitables.get("constructors").add(builder);
      constructors.add(index, builder);
    }
    return (A) this;
  }

  public A addToExtendsList(ClassRef... items) {
    if (this.extendsList == null) {
      this.extendsList = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("extendsList").add(builder);
      this.extendsList.add(builder);
    }
    return (A) this;
  }

  public A addToExtendsList(int index, ClassRef item) {
    if (this.extendsList == null) {
      this.extendsList = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= extendsList.size()) {
      _visitables.get("extendsList").add(builder);
      extendsList.add(builder);
    } else {
      _visitables.get("extendsList").add(builder);
      extendsList.add(index, builder);
    }
    return (A) this;
  }

  public A addToImplementsList(ClassRef... items) {
    if (this.implementsList == null) {
      this.implementsList = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("implementsList").add(builder);
      this.implementsList.add(builder);
    }
    return (A) this;
  }

  public A addToImplementsList(int index, ClassRef item) {
    if (this.implementsList == null) {
      this.implementsList = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= implementsList.size()) {
      _visitables.get("implementsList").add(builder);
      implementsList.add(builder);
    } else {
      _visitables.get("implementsList").add(builder);
      implementsList.add(index, builder);
    }
    return (A) this;
  }

  public A addToInnerTypes(TypeDef... items) {
    if (this.innerTypes == null) {
      this.innerTypes = new ArrayList<TypeDefBuilder>();
    }
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("innerTypes").add(builder);
      this.innerTypes.add(builder);
    }
    return (A) this;
  }

  public A addToInnerTypes(int index, TypeDef item) {
    if (this.innerTypes == null) {
      this.innerTypes = new ArrayList<TypeDefBuilder>();
    }
    TypeDefBuilder builder = new TypeDefBuilder(item);
    if (index < 0 || index >= innerTypes.size()) {
      _visitables.get("innerTypes").add(builder);
      innerTypes.add(builder);
    } else {
      _visitables.get("innerTypes").add(builder);
      innerTypes.add(index, builder);
    }
    return (A) this;
  }

  public A addToMethods(Method... items) {
    if (this.methods == null) {
      this.methods = new ArrayList<MethodBuilder>();
    }
    for (Method item : items) {
      MethodBuilder builder = new MethodBuilder(item);
      _visitables.get("methods").add(builder);
      this.methods.add(builder);
    }
    return (A) this;
  }

  public A addToMethods(int index, Method item) {
    if (this.methods == null) {
      this.methods = new ArrayList<MethodBuilder>();
    }
    MethodBuilder builder = new MethodBuilder(item);
    if (index < 0 || index >= methods.size()) {
      _visitables.get("methods").add(builder);
      methods.add(builder);
    } else {
      _visitables.get("methods").add(builder);
      methods.add(index, builder);
    }
    return (A) this;
  }

  public A addToParameters(TypeParamDef... items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public A addToParameters(int index, TypeParamDef item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
    if (index < 0 || index >= parameters.size()) {
      _visitables.get("parameters").add(builder);
      parameters.add(builder);
    } else {
      _visitables.get("parameters").add(builder);
      parameters.add(index, builder);
    }
    return (A) this;
  }

  public A addToProperties(Property... items) {
    if (this.properties == null) {
      this.properties = new ArrayList<PropertyBuilder>();
    }
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").add(builder);
      this.properties.add(builder);
    }
    return (A) this;
  }

  public A addToProperties(int index, Property item) {
    if (this.properties == null) {
      this.properties = new ArrayList<PropertyBuilder>();
    }
    PropertyBuilder builder = new PropertyBuilder(item);
    if (index < 0 || index >= properties.size()) {
      _visitables.get("properties").add(builder);
      properties.add(builder);
    } else {
      _visitables.get("properties").add(builder);
      properties.add(index, builder);
    }
    return (A) this;
  }

  public AnnotationRef buildAnnotation(int index) {
    return this.annotations.get(index).build();
  }

  public List<AnnotationRef> buildAnnotations() {
    return this.annotations != null ? build(annotations) : null;
  }

  public Method buildConstructor(int index) {
    return this.constructors.get(index).build();
  }

  public List<Method> buildConstructors() {
    return this.constructors != null ? build(constructors) : null;
  }

  public List<ClassRef> buildExtendsList() {
    return this.extendsList != null ? build(extendsList) : null;
  }

  public ClassRef buildExtendsList(int index) {
    return this.extendsList.get(index).build();
  }

  public AnnotationRef buildFirstAnnotation() {
    return this.annotations.get(0).build();
  }

  public Method buildFirstConstructor() {
    return this.constructors.get(0).build();
  }

  public ClassRef buildFirstExtendsList() {
    return this.extendsList.get(0).build();
  }

  public ClassRef buildFirstImplementsList() {
    return this.implementsList.get(0).build();
  }

  public TypeDef buildFirstInnerType() {
    return this.innerTypes.get(0).build();
  }

  public Method buildFirstMethod() {
    return this.methods.get(0).build();
  }

  public TypeParamDef buildFirstParameter() {
    return this.parameters.get(0).build();
  }

  public Property buildFirstProperty() {
    return this.properties.get(0).build();
  }

  public List<ClassRef> buildImplementsList() {
    return this.implementsList != null ? build(implementsList) : null;
  }

  public ClassRef buildImplementsList(int index) {
    return this.implementsList.get(index).build();
  }

  public TypeDef buildInnerType(int index) {
    return this.innerTypes.get(index).build();
  }

  public List<TypeDef> buildInnerTypes() {
    return this.innerTypes != null ? build(innerTypes) : null;
  }

  public AnnotationRef buildLastAnnotation() {
    return this.annotations.get(annotations.size() - 1).build();
  }

  public Method buildLastConstructor() {
    return this.constructors.get(constructors.size() - 1).build();
  }

  public ClassRef buildLastExtendsList() {
    return this.extendsList.get(extendsList.size() - 1).build();
  }

  public ClassRef buildLastImplementsList() {
    return this.implementsList.get(implementsList.size() - 1).build();
  }

  public TypeDef buildLastInnerType() {
    return this.innerTypes.get(innerTypes.size() - 1).build();
  }

  public Method buildLastMethod() {
    return this.methods.get(methods.size() - 1).build();
  }

  public TypeParamDef buildLastParameter() {
    return this.parameters.get(parameters.size() - 1).build();
  }

  public Property buildLastProperty() {
    return this.properties.get(properties.size() - 1).build();
  }

  public AnnotationRef buildMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
    for (AnnotationRefBuilder item : annotations) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Method buildMatchingConstructor(Predicate<MethodBuilder> predicate) {
    for (MethodBuilder item : constructors) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public ClassRef buildMatchingExtendsList(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : extendsList) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public ClassRef buildMatchingImplementsList(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : implementsList) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public TypeDef buildMatchingInnerType(Predicate<TypeDefBuilder> predicate) {
    for (TypeDefBuilder item : innerTypes) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Method buildMatchingMethod(Predicate<MethodBuilder> predicate) {
    for (MethodBuilder item : methods) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public TypeParamDef buildMatchingParameter(Predicate<TypeParamDefBuilder> predicate) {
    for (TypeParamDefBuilder item : parameters) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Property buildMatchingProperty(Predicate<PropertyBuilder> predicate) {
    for (PropertyBuilder item : properties) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Method buildMethod(int index) {
    return this.methods.get(index).build();
  }

  public List<Method> buildMethods() {
    return this.methods != null ? build(methods) : null;
  }

  public TypeParamDef buildParameter(int index) {
    return this.parameters.get(index).build();
  }

  public List<TypeParamDef> buildParameters() {
    return this.parameters != null ? build(parameters) : null;
  }

  public List<Property> buildProperties() {
    return this.properties != null ? build(properties) : null;
  }

  public Property buildProperty(int index) {
    return this.properties.get(index).build();
  }

  protected void copyInstance(TypeDef instance) {
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
  }

  public AnnotationsNested<A> editAnnotation(int index) {
    if (annotations.size() <= index)
      throw new RuntimeException("Can't edit annotations. Index exceeds size.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public ConstructorsNested<A> editConstructor(int index) {
    if (constructors.size() <= index)
      throw new RuntimeException("Can't edit constructors. Index exceeds size.");
    return setNewConstructorLike(index, buildConstructor(index));
  }

  public ExtendsListNested<A> editExtendsList(int index) {
    if (extendsList.size() <= index)
      throw new RuntimeException("Can't edit extendsList. Index exceeds size.");
    return setNewExtendsListLike(index, buildExtendsList(index));
  }

  public AnnotationsNested<A> editFirstAnnotation() {
    if (annotations.size() == 0)
      throw new RuntimeException("Can't edit first annotations. The list is empty.");
    return setNewAnnotationLike(0, buildAnnotation(0));
  }

  public ConstructorsNested<A> editFirstConstructor() {
    if (constructors.size() == 0)
      throw new RuntimeException("Can't edit first constructors. The list is empty.");
    return setNewConstructorLike(0, buildConstructor(0));
  }

  public ExtendsListNested<A> editFirstExtendsList() {
    if (extendsList.size() == 0)
      throw new RuntimeException("Can't edit first extendsList. The list is empty.");
    return setNewExtendsListLike(0, buildExtendsList(0));
  }

  public ImplementsListNested<A> editFirstImplementsList() {
    if (implementsList.size() == 0)
      throw new RuntimeException("Can't edit first implementsList. The list is empty.");
    return setNewImplementsListLike(0, buildImplementsList(0));
  }

  public InnerTypesNested<A> editFirstInnerType() {
    if (innerTypes.size() == 0)
      throw new RuntimeException("Can't edit first innerTypes. The list is empty.");
    return setNewInnerTypeLike(0, buildInnerType(0));
  }

  public MethodsNested<A> editFirstMethod() {
    if (methods.size() == 0)
      throw new RuntimeException("Can't edit first methods. The list is empty.");
    return setNewMethodLike(0, buildMethod(0));
  }

  public ParametersNested<A> editFirstParameter() {
    if (parameters.size() == 0)
      throw new RuntimeException("Can't edit first parameters. The list is empty.");
    return setNewParameterLike(0, buildParameter(0));
  }

  public PropertiesNested<A> editFirstProperty() {
    if (properties.size() == 0)
      throw new RuntimeException("Can't edit first properties. The list is empty.");
    return setNewPropertyLike(0, buildProperty(0));
  }

  public ImplementsListNested<A> editImplementsList(int index) {
    if (implementsList.size() <= index)
      throw new RuntimeException("Can't edit implementsList. Index exceeds size.");
    return setNewImplementsListLike(index, buildImplementsList(index));
  }

  public InnerTypesNested<A> editInnerType(int index) {
    if (innerTypes.size() <= index)
      throw new RuntimeException("Can't edit innerTypes. Index exceeds size.");
    return setNewInnerTypeLike(index, buildInnerType(index));
  }

  public AnnotationsNested<A> editLastAnnotation() {
    int index = annotations.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last annotations. The list is empty.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public ConstructorsNested<A> editLastConstructor() {
    int index = constructors.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last constructors. The list is empty.");
    return setNewConstructorLike(index, buildConstructor(index));
  }

  public ExtendsListNested<A> editLastExtendsList() {
    int index = extendsList.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last extendsList. The list is empty.");
    return setNewExtendsListLike(index, buildExtendsList(index));
  }

  public ImplementsListNested<A> editLastImplementsList() {
    int index = implementsList.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last implementsList. The list is empty.");
    return setNewImplementsListLike(index, buildImplementsList(index));
  }

  public InnerTypesNested<A> editLastInnerType() {
    int index = innerTypes.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last innerTypes. The list is empty.");
    return setNewInnerTypeLike(index, buildInnerType(index));
  }

  public MethodsNested<A> editLastMethod() {
    int index = methods.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last methods. The list is empty.");
    return setNewMethodLike(index, buildMethod(index));
  }

  public ParametersNested<A> editLastParameter() {
    int index = parameters.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last parameters. The list is empty.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public PropertiesNested<A> editLastProperty() {
    int index = properties.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last properties. The list is empty.");
    return setNewPropertyLike(index, buildProperty(index));
  }

  public AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < annotations.size(); i++) {
      if (predicate.test(annotations.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching annotations. No match found.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public ConstructorsNested<A> editMatchingConstructor(Predicate<MethodBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < constructors.size(); i++) {
      if (predicate.test(constructors.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching constructors. No match found.");
    return setNewConstructorLike(index, buildConstructor(index));
  }

  public ExtendsListNested<A> editMatchingExtendsList(Predicate<ClassRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < extendsList.size(); i++) {
      if (predicate.test(extendsList.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching extendsList. No match found.");
    return setNewExtendsListLike(index, buildExtendsList(index));
  }

  public ImplementsListNested<A> editMatchingImplementsList(Predicate<ClassRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < implementsList.size(); i++) {
      if (predicate.test(implementsList.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching implementsList. No match found.");
    return setNewImplementsListLike(index, buildImplementsList(index));
  }

  public InnerTypesNested<A> editMatchingInnerType(Predicate<TypeDefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < innerTypes.size(); i++) {
      if (predicate.test(innerTypes.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching innerTypes. No match found.");
    return setNewInnerTypeLike(index, buildInnerType(index));
  }

  public MethodsNested<A> editMatchingMethod(Predicate<MethodBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < methods.size(); i++) {
      if (predicate.test(methods.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching methods. No match found.");
    return setNewMethodLike(index, buildMethod(index));
  }

  public ParametersNested<A> editMatchingParameter(Predicate<TypeParamDefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < parameters.size(); i++) {
      if (predicate.test(parameters.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching parameters. No match found.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public PropertiesNested<A> editMatchingProperty(Predicate<PropertyBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < properties.size(); i++) {
      if (predicate.test(properties.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching properties. No match found.");
    return setNewPropertyLike(index, buildProperty(index));
  }

  public MethodsNested<A> editMethod(int index) {
    if (methods.size() <= index)
      throw new RuntimeException("Can't edit methods. Index exceeds size.");
    return setNewMethodLike(index, buildMethod(index));
  }

  public ParametersNested<A> editParameter(int index) {
    if (parameters.size() <= index)
      throw new RuntimeException("Can't edit parameters. Index exceeds size.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public PropertiesNested<A> editProperty(int index) {
    if (properties.size() <= index)
      throw new RuntimeException("Can't edit properties. Index exceeds size.");
    return setNewPropertyLike(index, buildProperty(index));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TypeDefFluent that = (TypeDefFluent) o;
    if (!java.util.Objects.equals(kind, that.kind))
      return false;
    if (!java.util.Objects.equals(packageName, that.packageName))
      return false;
    if (!java.util.Objects.equals(name, that.name))
      return false;
    if (!java.util.Objects.equals(comments, that.comments))
      return false;
    if (!java.util.Objects.equals(annotations, that.annotations))
      return false;
    if (!java.util.Objects.equals(extendsList, that.extendsList))
      return false;
    if (!java.util.Objects.equals(implementsList, that.implementsList))
      return false;
    if (!java.util.Objects.equals(parameters, that.parameters))
      return false;
    if (!java.util.Objects.equals(properties, that.properties))
      return false;
    if (!java.util.Objects.equals(constructors, that.constructors))
      return false;
    if (!java.util.Objects.equals(methods, that.methods))
      return false;
    if (!java.util.Objects.equals(outerTypeName, that.outerTypeName))
      return false;
    if (!java.util.Objects.equals(innerTypes, that.innerTypes))
      return false;
    return true;
  }

  public String getComment(int index) {
    return this.comments.get(index);
  }

  public List<String> getComments() {
    return this.comments;
  }

  public String getFirstComment() {
    return this.comments.get(0);
  }

  public Kind getKind() {
    return this.kind;
  }

  public String getLastComment() {
    return this.comments.get(comments.size() - 1);
  }

  public String getMatchingComment(Predicate<String> predicate) {
    for (String item : comments) {
      if (predicate.test(item)) {
        return item;
      }
    }
    return null;
  }

  public String getName() {
    return this.name;
  }

  public String getOuterTypeName() {
    return this.outerTypeName;
  }

  public String getPackageName() {
    return this.packageName;
  }

  public boolean hasAnnotations() {
    return this.annotations != null && !(this.annotations.isEmpty());
  }

  public boolean hasComments() {
    return this.comments != null && !(this.comments.isEmpty());
  }

  public boolean hasConstructors() {
    return this.constructors != null && !(this.constructors.isEmpty());
  }

  public boolean hasExtendsList() {
    return this.extendsList != null && !(this.extendsList.isEmpty());
  }

  public boolean hasImplementsList() {
    return this.implementsList != null && !(this.implementsList.isEmpty());
  }

  public boolean hasInnerTypes() {
    return this.innerTypes != null && !(this.innerTypes.isEmpty());
  }

  public boolean hasKind() {
    return this.kind != null;
  }

  public boolean hasMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
    for (AnnotationRefBuilder item : annotations) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingComment(Predicate<String> predicate) {
    for (String item : comments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingConstructor(Predicate<MethodBuilder> predicate) {
    for (MethodBuilder item : constructors) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingExtendsList(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : extendsList) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingImplementsList(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : implementsList) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingInnerType(Predicate<TypeDefBuilder> predicate) {
    for (TypeDefBuilder item : innerTypes) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingMethod(Predicate<MethodBuilder> predicate) {
    for (MethodBuilder item : methods) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingParameter(Predicate<TypeParamDefBuilder> predicate) {
    for (TypeParamDefBuilder item : parameters) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingProperty(Predicate<PropertyBuilder> predicate) {
    for (PropertyBuilder item : properties) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMethods() {
    return this.methods != null && !(this.methods.isEmpty());
  }

  public boolean hasName() {
    return this.name != null;
  }

  public boolean hasOuterTypeName() {
    return this.outerTypeName != null;
  }

  public boolean hasPackageName() {
    return this.packageName != null;
  }

  public boolean hasParameters() {
    return this.parameters != null && !(this.parameters.isEmpty());
  }

  public boolean hasProperties() {
    return this.properties != null && !(this.properties.isEmpty());
  }

  public int hashCode() {
    return java.util.Objects.hash(kind, packageName, name, comments, annotations, extendsList, implementsList, parameters,
        properties, constructors, methods, outerTypeName, innerTypes, super.hashCode());
  }

  public A removeAllFromAnnotations(Collection<AnnotationRef> items) {
    if (this.annotations == null)
      return (A) this;
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      this.annotations.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromComments(Collection<String> items) {
    if (this.comments == null)
      return (A) this;
    for (String item : items) {
      this.comments.remove(item);
    }
    return (A) this;
  }

  public A removeAllFromConstructors(Collection<Method> items) {
    if (this.constructors == null)
      return (A) this;
    for (Method item : items) {
      MethodBuilder builder = new MethodBuilder(item);
      _visitables.get("constructors").remove(builder);
      this.constructors.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromExtendsList(Collection<ClassRef> items) {
    if (this.extendsList == null)
      return (A) this;
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("extendsList").remove(builder);
      this.extendsList.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromImplementsList(Collection<ClassRef> items) {
    if (this.implementsList == null)
      return (A) this;
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("implementsList").remove(builder);
      this.implementsList.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromInnerTypes(Collection<TypeDef> items) {
    if (this.innerTypes == null)
      return (A) this;
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("innerTypes").remove(builder);
      this.innerTypes.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromMethods(Collection<Method> items) {
    if (this.methods == null)
      return (A) this;
    for (Method item : items) {
      MethodBuilder builder = new MethodBuilder(item);
      _visitables.get("methods").remove(builder);
      this.methods.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromParameters(Collection<TypeParamDef> items) {
    if (this.parameters == null)
      return (A) this;
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").remove(builder);
      this.parameters.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromProperties(Collection<Property> items) {
    if (this.properties == null)
      return (A) this;
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").remove(builder);
      this.properties.remove(builder);
    }
    return (A) this;
  }

  public A removeFromAnnotations(AnnotationRef... items) {
    if (this.annotations == null)
      return (A) this;
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      this.annotations.remove(builder);
    }
    return (A) this;
  }

  public A removeFromComments(String... items) {
    if (this.comments == null)
      return (A) this;
    for (String item : items) {
      this.comments.remove(item);
    }
    return (A) this;
  }

  public A removeFromConstructors(Method... items) {
    if (this.constructors == null)
      return (A) this;
    for (Method item : items) {
      MethodBuilder builder = new MethodBuilder(item);
      _visitables.get("constructors").remove(builder);
      this.constructors.remove(builder);
    }
    return (A) this;
  }

  public A removeFromExtendsList(ClassRef... items) {
    if (this.extendsList == null)
      return (A) this;
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("extendsList").remove(builder);
      this.extendsList.remove(builder);
    }
    return (A) this;
  }

  public A removeFromImplementsList(ClassRef... items) {
    if (this.implementsList == null)
      return (A) this;
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("implementsList").remove(builder);
      this.implementsList.remove(builder);
    }
    return (A) this;
  }

  public A removeFromInnerTypes(TypeDef... items) {
    if (this.innerTypes == null)
      return (A) this;
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("innerTypes").remove(builder);
      this.innerTypes.remove(builder);
    }
    return (A) this;
  }

  public A removeFromMethods(Method... items) {
    if (this.methods == null)
      return (A) this;
    for (Method item : items) {
      MethodBuilder builder = new MethodBuilder(item);
      _visitables.get("methods").remove(builder);
      this.methods.remove(builder);
    }
    return (A) this;
  }

  public A removeFromParameters(TypeParamDef... items) {
    if (this.parameters == null)
      return (A) this;
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").remove(builder);
      this.parameters.remove(builder);
    }
    return (A) this;
  }

  public A removeFromProperties(Property... items) {
    if (this.properties == null)
      return (A) this;
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").remove(builder);
      this.properties.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromAnnotations(Predicate<AnnotationRefBuilder> predicate) {
    if (annotations == null)
      return (A) this;
    final Iterator<AnnotationRefBuilder> each = annotations.iterator();
    final List visitables = _visitables.get("annotations");
    while (each.hasNext()) {
      AnnotationRefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public A removeMatchingFromConstructors(Predicate<MethodBuilder> predicate) {
    if (constructors == null)
      return (A) this;
    final Iterator<MethodBuilder> each = constructors.iterator();
    final List visitables = _visitables.get("constructors");
    while (each.hasNext()) {
      MethodBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public A removeMatchingFromExtendsList(Predicate<ClassRefBuilder> predicate) {
    if (extendsList == null)
      return (A) this;
    final Iterator<ClassRefBuilder> each = extendsList.iterator();
    final List visitables = _visitables.get("extendsList");
    while (each.hasNext()) {
      ClassRefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public A removeMatchingFromImplementsList(Predicate<ClassRefBuilder> predicate) {
    if (implementsList == null)
      return (A) this;
    final Iterator<ClassRefBuilder> each = implementsList.iterator();
    final List visitables = _visitables.get("implementsList");
    while (each.hasNext()) {
      ClassRefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public A removeMatchingFromInnerTypes(Predicate<TypeDefBuilder> predicate) {
    if (innerTypes == null)
      return (A) this;
    final Iterator<TypeDefBuilder> each = innerTypes.iterator();
    final List visitables = _visitables.get("innerTypes");
    while (each.hasNext()) {
      TypeDefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public A removeMatchingFromMethods(Predicate<MethodBuilder> predicate) {
    if (methods == null)
      return (A) this;
    final Iterator<MethodBuilder> each = methods.iterator();
    final List visitables = _visitables.get("methods");
    while (each.hasNext()) {
      MethodBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public A removeMatchingFromParameters(Predicate<TypeParamDefBuilder> predicate) {
    if (parameters == null)
      return (A) this;
    final Iterator<TypeParamDefBuilder> each = parameters.iterator();
    final List visitables = _visitables.get("parameters");
    while (each.hasNext()) {
      TypeParamDefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public A removeMatchingFromProperties(Predicate<PropertyBuilder> predicate) {
    if (properties == null)
      return (A) this;
    final Iterator<PropertyBuilder> each = properties.iterator();
    final List visitables = _visitables.get("properties");
    while (each.hasNext()) {
      PropertyBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item) {
    return new AnnotationsNested(index, item);
  }

  public ConstructorsNested<A> setNewConstructorLike(int index, Method item) {
    return new ConstructorsNested(index, item);
  }

  public ExtendsListNested<A> setNewExtendsListLike(int index, ClassRef item) {
    return new ExtendsListNested(index, item);
  }

  public ImplementsListNested<A> setNewImplementsListLike(int index, ClassRef item) {
    return new ImplementsListNested(index, item);
  }

  public InnerTypesNested<A> setNewInnerTypeLike(int index, TypeDef item) {
    return new InnerTypesNested(index, item);
  }

  public MethodsNested<A> setNewMethodLike(int index, Method item) {
    return new MethodsNested(index, item);
  }

  public ParametersNested<A> setNewParameterLike(int index, TypeParamDef item) {
    return new ParametersNested(index, item);
  }

  public PropertiesNested<A> setNewPropertyLike(int index, Property item) {
    return new PropertiesNested(index, item);
  }

  public A setToAnnotations(int index, AnnotationRef item) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
    if (index < 0 || index >= annotations.size()) {
      _visitables.get("annotations").add(builder);
      annotations.add(builder);
    } else {
      _visitables.get("annotations").add(builder);
      annotations.set(index, builder);
    }
    return (A) this;
  }

  public A setToComments(int index, String item) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    this.comments.set(index, item);
    return (A) this;
  }

  public A setToConstructors(int index, Method item) {
    if (this.constructors == null) {
      this.constructors = new ArrayList<MethodBuilder>();
    }
    MethodBuilder builder = new MethodBuilder(item);
    if (index < 0 || index >= constructors.size()) {
      _visitables.get("constructors").add(builder);
      constructors.add(builder);
    } else {
      _visitables.get("constructors").add(builder);
      constructors.set(index, builder);
    }
    return (A) this;
  }

  public A setToExtendsList(int index, ClassRef item) {
    if (this.extendsList == null) {
      this.extendsList = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= extendsList.size()) {
      _visitables.get("extendsList").add(builder);
      extendsList.add(builder);
    } else {
      _visitables.get("extendsList").add(builder);
      extendsList.set(index, builder);
    }
    return (A) this;
  }

  public A setToImplementsList(int index, ClassRef item) {
    if (this.implementsList == null) {
      this.implementsList = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= implementsList.size()) {
      _visitables.get("implementsList").add(builder);
      implementsList.add(builder);
    } else {
      _visitables.get("implementsList").add(builder);
      implementsList.set(index, builder);
    }
    return (A) this;
  }

  public A setToInnerTypes(int index, TypeDef item) {
    if (this.innerTypes == null) {
      this.innerTypes = new ArrayList<TypeDefBuilder>();
    }
    TypeDefBuilder builder = new TypeDefBuilder(item);
    if (index < 0 || index >= innerTypes.size()) {
      _visitables.get("innerTypes").add(builder);
      innerTypes.add(builder);
    } else {
      _visitables.get("innerTypes").add(builder);
      innerTypes.set(index, builder);
    }
    return (A) this;
  }

  public A setToMethods(int index, Method item) {
    if (this.methods == null) {
      this.methods = new ArrayList<MethodBuilder>();
    }
    MethodBuilder builder = new MethodBuilder(item);
    if (index < 0 || index >= methods.size()) {
      _visitables.get("methods").add(builder);
      methods.add(builder);
    } else {
      _visitables.get("methods").add(builder);
      methods.set(index, builder);
    }
    return (A) this;
  }

  public A setToParameters(int index, TypeParamDef item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
    if (index < 0 || index >= parameters.size()) {
      _visitables.get("parameters").add(builder);
      parameters.add(builder);
    } else {
      _visitables.get("parameters").add(builder);
      parameters.set(index, builder);
    }
    return (A) this;
  }

  public A setToProperties(int index, Property item) {
    if (this.properties == null) {
      this.properties = new ArrayList<PropertyBuilder>();
    }
    PropertyBuilder builder = new PropertyBuilder(item);
    if (index < 0 || index >= properties.size()) {
      _visitables.get("properties").add(builder);
      properties.add(builder);
    } else {
      _visitables.get("properties").add(builder);
      properties.set(index, builder);
    }
    return (A) this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (kind != null) {
      sb.append("kind:");
      sb.append(kind + ",");
    }
    if (packageName != null) {
      sb.append("packageName:");
      sb.append(packageName + ",");
    }
    if (name != null) {
      sb.append("name:");
      sb.append(name + ",");
    }
    if (comments != null && !comments.isEmpty()) {
      sb.append("comments:");
      sb.append(comments + ",");
    }
    if (annotations != null && !annotations.isEmpty()) {
      sb.append("annotations:");
      sb.append(annotations + ",");
    }
    if (extendsList != null && !extendsList.isEmpty()) {
      sb.append("extendsList:");
      sb.append(extendsList + ",");
    }
    if (implementsList != null && !implementsList.isEmpty()) {
      sb.append("implementsList:");
      sb.append(implementsList + ",");
    }
    if (parameters != null && !parameters.isEmpty()) {
      sb.append("parameters:");
      sb.append(parameters + ",");
    }
    if (properties != null && !properties.isEmpty()) {
      sb.append("properties:");
      sb.append(properties + ",");
    }
    if (constructors != null && !constructors.isEmpty()) {
      sb.append("constructors:");
      sb.append(constructors + ",");
    }
    if (methods != null && !methods.isEmpty()) {
      sb.append("methods:");
      sb.append(methods + ",");
    }
    if (outerTypeName != null) {
      sb.append("outerTypeName:");
      sb.append(outerTypeName + ",");
    }
    if (innerTypes != null && !innerTypes.isEmpty()) {
      sb.append("innerTypes:");
      sb.append(innerTypes);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withAnnotations(List<AnnotationRef> annotations) {
    if (this.annotations != null) {
      this._visitables.get("annotations").clear();
    }
    if (annotations != null) {
      this.annotations = new ArrayList();
      for (AnnotationRef item : annotations) {
        this.addToAnnotations(item);
      }
    } else {
      this.annotations = null;
    }
    return (A) this;
  }

  public A withAnnotations(AnnotationRef... annotations) {
    if (this.annotations != null) {
      this.annotations.clear();
      _visitables.remove("annotations");
    }
    if (annotations != null) {
      for (AnnotationRef item : annotations) {
        this.addToAnnotations(item);
      }
    }
    return (A) this;
  }

  public A withComments(List<String> comments) {
    if (comments != null) {
      this.comments = new ArrayList();
      for (String item : comments) {
        this.addToComments(item);
      }
    } else {
      this.comments = null;
    }
    return (A) this;
  }

  public A withComments(String... comments) {
    if (this.comments != null) {
      this.comments.clear();
      _visitables.remove("comments");
    }
    if (comments != null) {
      for (String item : comments) {
        this.addToComments(item);
      }
    }
    return (A) this;
  }

  public A withConstructors(List<Method> constructors) {
    if (this.constructors != null) {
      this._visitables.get("constructors").clear();
    }
    if (constructors != null) {
      this.constructors = new ArrayList();
      for (Method item : constructors) {
        this.addToConstructors(item);
      }
    } else {
      this.constructors = null;
    }
    return (A) this;
  }

  public A withConstructors(Method... constructors) {
    if (this.constructors != null) {
      this.constructors.clear();
      _visitables.remove("constructors");
    }
    if (constructors != null) {
      for (Method item : constructors) {
        this.addToConstructors(item);
      }
    }
    return (A) this;
  }

  public A withExtendsList(List<ClassRef> extendsList) {
    if (this.extendsList != null) {
      this._visitables.get("extendsList").clear();
    }
    if (extendsList != null) {
      this.extendsList = new ArrayList();
      for (ClassRef item : extendsList) {
        this.addToExtendsList(item);
      }
    } else {
      this.extendsList = null;
    }
    return (A) this;
  }

  public A withExtendsList(ClassRef... extendsList) {
    if (this.extendsList != null) {
      this.extendsList.clear();
      _visitables.remove("extendsList");
    }
    if (extendsList != null) {
      for (ClassRef item : extendsList) {
        this.addToExtendsList(item);
      }
    }
    return (A) this;
  }

  public A withImplementsList(List<ClassRef> implementsList) {
    if (this.implementsList != null) {
      this._visitables.get("implementsList").clear();
    }
    if (implementsList != null) {
      this.implementsList = new ArrayList();
      for (ClassRef item : implementsList) {
        this.addToImplementsList(item);
      }
    } else {
      this.implementsList = null;
    }
    return (A) this;
  }

  public A withImplementsList(ClassRef... implementsList) {
    if (this.implementsList != null) {
      this.implementsList.clear();
      _visitables.remove("implementsList");
    }
    if (implementsList != null) {
      for (ClassRef item : implementsList) {
        this.addToImplementsList(item);
      }
    }
    return (A) this;
  }

  public A withInnerTypes(List<TypeDef> innerTypes) {
    if (this.innerTypes != null) {
      this._visitables.get("innerTypes").clear();
    }
    if (innerTypes != null) {
      this.innerTypes = new ArrayList();
      for (TypeDef item : innerTypes) {
        this.addToInnerTypes(item);
      }
    } else {
      this.innerTypes = null;
    }
    return (A) this;
  }

  public A withInnerTypes(TypeDef... innerTypes) {
    if (this.innerTypes != null) {
      this.innerTypes.clear();
      _visitables.remove("innerTypes");
    }
    if (innerTypes != null) {
      for (TypeDef item : innerTypes) {
        this.addToInnerTypes(item);
      }
    }
    return (A) this;
  }

  public A withKind(Kind kind) {
    this.kind = kind;
    return (A) this;
  }

  public A withMethods(List<Method> methods) {
    if (this.methods != null) {
      this._visitables.get("methods").clear();
    }
    if (methods != null) {
      this.methods = new ArrayList();
      for (Method item : methods) {
        this.addToMethods(item);
      }
    } else {
      this.methods = null;
    }
    return (A) this;
  }

  public A withMethods(Method... methods) {
    if (this.methods != null) {
      this.methods.clear();
      _visitables.remove("methods");
    }
    if (methods != null) {
      for (Method item : methods) {
        this.addToMethods(item);
      }
    }
    return (A) this;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

  public A withOuterTypeName(String outerTypeName) {
    this.outerTypeName = outerTypeName;
    return (A) this;
  }

  public A withPackageName(String packageName) {
    this.packageName = packageName;
    return (A) this;
  }

  public A withParameters(List<TypeParamDef> parameters) {
    if (this.parameters != null) {
      this._visitables.get("parameters").clear();
    }
    if (parameters != null) {
      this.parameters = new ArrayList();
      for (TypeParamDef item : parameters) {
        this.addToParameters(item);
      }
    } else {
      this.parameters = null;
    }
    return (A) this;
  }

  public A withParameters(TypeParamDef... parameters) {
    if (this.parameters != null) {
      this.parameters.clear();
      _visitables.remove("parameters");
    }
    if (parameters != null) {
      for (TypeParamDef item : parameters) {
        this.addToParameters(item);
      }
    }
    return (A) this;
  }

  public A withProperties(List<Property> properties) {
    if (this.properties != null) {
      this._visitables.get("properties").clear();
    }
    if (properties != null) {
      this.properties = new ArrayList();
      for (Property item : properties) {
        this.addToProperties(item);
      }
    } else {
      this.properties = null;
    }
    return (A) this;
  }

  public A withProperties(Property... properties) {
    if (this.properties != null) {
      this.properties.clear();
      _visitables.remove("properties");
    }
    if (properties != null) {
      for (Property item : properties) {
        this.addToProperties(item);
      }
    }
    return (A) this;
  }

  public class AnnotationsNested<N> extends AnnotationRefFluent<AnnotationsNested<N>> implements Nested<N> {

    AnnotationRefBuilder builder;
    int index;

    AnnotationsNested(int index, AnnotationRef item) {
      this.index = index;
      this.builder = new AnnotationRefBuilder(this, item);
    }

    public N and() {
      return (N) TypeDefFluent.this.setToAnnotations(index, builder.build());
    }

    public N endAnnotation() {
      return and();
    }

  }

  public class ConstructorsNested<N> extends MethodFluent<ConstructorsNested<N>> implements Nested<N> {

    MethodBuilder builder;
    int index;

    ConstructorsNested(int index, Method item) {
      this.index = index;
      this.builder = new MethodBuilder(this, item);
    }

    public N and() {
      return (N) TypeDefFluent.this.setToConstructors(index, builder.build());
    }

    public N endConstructor() {
      return and();
    }

  }

  public class ExtendsListNested<N> extends ClassRefFluent<ExtendsListNested<N>> implements Nested<N> {

    ClassRefBuilder builder;
    int index;

    ExtendsListNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) TypeDefFluent.this.setToExtendsList(index, builder.build());
    }

    public N endExtendsList() {
      return and();
    }

  }

  public class ImplementsListNested<N> extends ClassRefFluent<ImplementsListNested<N>> implements Nested<N> {

    ClassRefBuilder builder;
    int index;

    ImplementsListNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) TypeDefFluent.this.setToImplementsList(index, builder.build());
    }

    public N endImplementsList() {
      return and();
    }

  }

  public class InnerTypesNested<N> extends TypeDefFluent<InnerTypesNested<N>> implements Nested<N> {

    TypeDefBuilder builder;
    int index;

    InnerTypesNested(int index, TypeDef item) {
      this.index = index;
      this.builder = new TypeDefBuilder(this, item);
    }

    public N and() {
      return (N) TypeDefFluent.this.setToInnerTypes(index, builder.build());
    }

    public N endInnerType() {
      return and();
    }

  }

  public class MethodsNested<N> extends MethodFluent<MethodsNested<N>> implements Nested<N> {

    MethodBuilder builder;
    int index;

    MethodsNested(int index, Method item) {
      this.index = index;
      this.builder = new MethodBuilder(this, item);
    }

    public N and() {
      return (N) TypeDefFluent.this.setToMethods(index, builder.build());
    }

    public N endMethod() {
      return and();
    }

  }

  public class ParametersNested<N> extends TypeParamDefFluent<ParametersNested<N>> implements Nested<N> {

    TypeParamDefBuilder builder;
    int index;

    ParametersNested(int index, TypeParamDef item) {
      this.index = index;
      this.builder = new TypeParamDefBuilder(this, item);
    }

    public N and() {
      return (N) TypeDefFluent.this.setToParameters(index, builder.build());
    }

    public N endParameter() {
      return and();
    }

  }

  public class PropertiesNested<N> extends PropertyFluent<PropertiesNested<N>> implements Nested<N> {

    PropertyBuilder builder;
    int index;

    PropertiesNested(int index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) TypeDefFluent.this.setToProperties(index, builder.build());
    }

    public N endProperty() {
      return and();
    }

  }
}
