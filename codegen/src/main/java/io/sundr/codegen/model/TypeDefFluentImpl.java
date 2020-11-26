/*
 *      Copyright 2019 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.codegen.model;

import io.sundr.builder.Nested;
import java.util.function.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TypeDefFluentImpl<A extends TypeDefFluent<A>> extends ModifierSupportFluentImpl<A> implements TypeDefFluent<A>{

    private Kind kind;
    private String packageName;
    private String name;
    private List<String> comments = new ArrayList<String>();
    private List<AnnotationRefBuilder> annotations =  new ArrayList<AnnotationRefBuilder>();
    private List<ClassRefBuilder> extendsList =  new ArrayList<ClassRefBuilder>();
    private List<ClassRefBuilder> implementsList =  new ArrayList<ClassRefBuilder>();
    private List<TypeParamDefBuilder> parameters =  new ArrayList<TypeParamDefBuilder>();
    private List<PropertyBuilder> properties =  new ArrayList<PropertyBuilder>();
    private List<MethodBuilder> constructors =  new ArrayList<MethodBuilder>();
    private List<MethodBuilder> methods =  new ArrayList<MethodBuilder>();
    private TypeDefBuilder outerType;
    private List<TypeDefBuilder> innerTypes =  new ArrayList<TypeDefBuilder>();

    public TypeDefFluentImpl(){
    }
    public TypeDefFluentImpl(TypeDef instance){
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
            this.withOuterType(instance.getOuterType()); 
            this.withInnerTypes(instance.getInnerTypes()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public Kind getKind(){
            return this.kind;
    }

    public A withKind(Kind kind){
            this.kind=kind; return (A) this;
    }

    public Boolean hasKind(){
            return this.kind != null;
    }

    public String getPackageName(){
            return this.packageName;
    }

    public A withPackageName(String packageName){
            this.packageName=packageName; return (A) this;
    }

    public Boolean hasPackageName(){
            return this.packageName != null;
    }

    public A withNewPackageName(String arg1){
            return (A)withPackageName(new String(arg1));
    }

    public A withNewPackageName(StringBuilder arg1){
            return (A)withPackageName(new String(arg1));
    }

    public A withNewPackageName(StringBuffer arg1){
            return (A)withPackageName(new String(arg1));
    }

    public String getName(){
            return this.name;
    }

    public A withName(String name){
            this.name=name; return (A) this;
    }

    public Boolean hasName(){
            return this.name != null;
    }

    public A withNewName(String arg1){
            return (A)withName(new String(arg1));
    }

    public A withNewName(StringBuilder arg1){
            return (A)withName(new String(arg1));
    }

    public A withNewName(StringBuffer arg1){
            return (A)withName(new String(arg1));
    }

    public A addToComments(int index,String item){
            if (this.comments == null) {this.comments = new ArrayList<String>();}
            this.comments.add(index, item);
            return (A)this;
    }

    public A setToComments(int index,String item){
            if (this.comments == null) {this.comments = new ArrayList<String>();}
            this.comments.set(index, item); return (A)this;
    }

    public A addToComments(String... items){
            if (this.comments == null) {this.comments = new ArrayList<String>();}
            for (String item : items) {this.comments.add(item);} return (A)this;
    }

    public A addAllToComments(Collection<String> items){
            if (this.comments == null) {this.comments = new ArrayList<String>();}
            for (String item : items) {this.comments.add(item);} return (A)this;
    }

    public A removeFromComments(String... items){
            for (String item : items) {if (this.comments!= null){ this.comments.remove(item);}} return (A)this;
    }

    public A removeAllFromComments(Collection<String> items){
            for (String item : items) {if (this.comments!= null){ this.comments.remove(item);}} return (A)this;
    }

    public List<String> getComments(){
            return this.comments;
    }

    public String getComment(int index){
            return this.comments.get(index);
    }

    public String getFirstComment(){
            return this.comments.get(0);
    }

    public String getLastComment(){
            return this.comments.get(comments.size() - 1);
    }

    public String getMatchingComment(Predicate<String> predicate){
            for (String item: comments) { if(predicate.test(item)){return item;} } return null;
    }

    public Boolean hasMatchingComment(Predicate<String> predicate){
            for (String item: comments) { if(predicate.test(item)){return true;} } return false;
    }

    public A withComments(List<String> comments){
            if (this.comments != null) { _visitables.get("comments").removeAll(this.comments);}
            if (comments != null) {this.comments = new ArrayList<String>(); for (String item : comments){this.addToComments(item);}} else { this.comments = new ArrayList<String>();} return (A) this;
    }

    public A withComments(String... comments){
            if (this.comments != null) {this.comments.clear();}
            if (comments != null) {for (String item :comments){ this.addToComments(item);}} return (A) this;
    }

    public Boolean hasComments(){
            return comments != null && !comments.isEmpty();
    }

    public A addNewComment(String arg1){
            return (A)addToComments(new String(arg1));
    }

    public A addNewComment(StringBuilder arg1){
            return (A)addToComments(new String(arg1));
    }

    public A addNewComment(StringBuffer arg1){
            return (A)addToComments(new String(arg1));
    }

    public A addToAnnotations(int index,AnnotationRef item){
            if (this.annotations == null) {this.annotations = new ArrayList<AnnotationRefBuilder>();}
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").add(index >= 0 ? index : _visitables.get("annotations").size(), builder);this.annotations.add(index >= 0 ? index : annotations.size(), builder); return (A)this;
    }

    public A setToAnnotations(int index,AnnotationRef item){
            if (this.annotations == null) {this.annotations = new ArrayList<AnnotationRefBuilder>();}
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
            if (index < 0 || index >= _visitables.get("annotations").size()) { _visitables.get("annotations").add(builder); } else { _visitables.get("annotations").set(index, builder);}
            if (index < 0 || index >= annotations.size()) { annotations.add(builder); } else { annotations.set(index, builder);}
             return (A)this;
    }

    public A addToAnnotations(AnnotationRef... items){
            if (this.annotations == null) {this.annotations = new ArrayList<AnnotationRefBuilder>();}
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A addAllToAnnotations(Collection<AnnotationRef> items){
            if (this.annotations == null) {this.annotations = new ArrayList<AnnotationRefBuilder>();}
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A removeFromAnnotations(AnnotationRef... items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").remove(builder);if (this.annotations != null) {this.annotations.remove(builder);}} return (A)this;
    }

    public A removeAllFromAnnotations(Collection<AnnotationRef> items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").remove(builder);if (this.annotations != null) {this.annotations.remove(builder);}} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildAnnotations instead.
 * @return The buildable object.
 */
@Deprecated public List<AnnotationRef> getAnnotations(){
            return build(annotations);
    }

    public List<AnnotationRef> buildAnnotations(){
            return build(annotations);
    }

    public AnnotationRef buildAnnotation(int index){
            return this.annotations.get(index).build();
    }

    public AnnotationRef buildFirstAnnotation(){
            return this.annotations.get(0).build();
    }

    public AnnotationRef buildLastAnnotation(){
            return this.annotations.get(annotations.size() - 1).build();
    }

    public AnnotationRef buildMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate){
            for (AnnotationRefBuilder item: annotations) { if(predicate.test(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate){
            for (AnnotationRefBuilder item: annotations) { if(predicate.test(item)){return true;} } return false;
    }

    public A withAnnotations(List<AnnotationRef> annotations){
            if (this.annotations != null) { _visitables.get("annotations").removeAll(this.annotations);}
            if (annotations != null) {this.annotations = new ArrayList<AnnotationRefBuilder>(); for (AnnotationRef item : annotations){this.addToAnnotations(item);}} else { this.annotations = new ArrayList<AnnotationRefBuilder>();} return (A) this;
    }

    public A withAnnotations(AnnotationRef... annotations){
            if (this.annotations != null) {this.annotations.clear();}
            if (annotations != null) {for (AnnotationRef item :annotations){ this.addToAnnotations(item);}} return (A) this;
    }

    public Boolean hasAnnotations(){
            return annotations != null && !annotations.isEmpty();
    }

    public TypeDefFluent.AnnotationsNested<A> addNewAnnotation(){
            return new AnnotationsNestedImpl();
    }

    public TypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item){
            return new AnnotationsNestedImpl(-1, item);
    }

    public TypeDefFluent.AnnotationsNested<A> setNewAnnotationLike(int index,AnnotationRef item){
            return new AnnotationsNestedImpl(index, item);
    }

    public TypeDefFluent.AnnotationsNested<A> editAnnotation(int index){
            if (annotations.size() <= index) throw new RuntimeException("Can't edit annotations. Index exceeds size.");
            return setNewAnnotationLike(index, buildAnnotation(index));
    }

    public TypeDefFluent.AnnotationsNested<A> editFirstAnnotation(){
            if (annotations.size() == 0) throw new RuntimeException("Can't edit first annotations. The list is empty.");
            return setNewAnnotationLike(0, buildAnnotation(0));
    }

    public TypeDefFluent.AnnotationsNested<A> editLastAnnotation(){
            int index = annotations.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last annotations. The list is empty.");
            return setNewAnnotationLike(index, buildAnnotation(index));
    }

    public TypeDefFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate){
            int index = -1;
            for (int i=0;i<annotations.size();i++) { 
            if (predicate.test(annotations.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching annotations. No match found.");
            return setNewAnnotationLike(index, buildAnnotation(index));
    }

    public A addToExtendsList(int index,ClassRef item){
            if (this.extendsList == null) {this.extendsList = new ArrayList<ClassRefBuilder>();}
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("extendsList").add(index >= 0 ? index : _visitables.get("extendsList").size(), builder);this.extendsList.add(index >= 0 ? index : extendsList.size(), builder); return (A)this;
    }

    public A setToExtendsList(int index,ClassRef item){
            if (this.extendsList == null) {this.extendsList = new ArrayList<ClassRefBuilder>();}
            ClassRefBuilder builder = new ClassRefBuilder(item);
            if (index < 0 || index >= _visitables.get("extendsList").size()) { _visitables.get("extendsList").add(builder); } else { _visitables.get("extendsList").set(index, builder);}
            if (index < 0 || index >= extendsList.size()) { extendsList.add(builder); } else { extendsList.set(index, builder);}
             return (A)this;
    }

    public A addToExtendsList(ClassRef... items){
            if (this.extendsList == null) {this.extendsList = new ArrayList<ClassRefBuilder>();}
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("extendsList").add(builder);this.extendsList.add(builder);} return (A)this;
    }

    public A addAllToExtendsList(Collection<ClassRef> items){
            if (this.extendsList == null) {this.extendsList = new ArrayList<ClassRefBuilder>();}
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("extendsList").add(builder);this.extendsList.add(builder);} return (A)this;
    }

    public A removeFromExtendsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("extendsList").remove(builder);if (this.extendsList != null) {this.extendsList.remove(builder);}} return (A)this;
    }

    public A removeAllFromExtendsList(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("extendsList").remove(builder);if (this.extendsList != null) {this.extendsList.remove(builder);}} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildExtendsList instead.
 * @return The buildable object.
 */
@Deprecated public List<ClassRef> getExtendsList(){
            return build(extendsList);
    }

    public List<ClassRef> buildExtendsList(){
            return build(extendsList);
    }

    public ClassRef buildExtendsList(int index){
            return this.extendsList.get(index).build();
    }

    public ClassRef buildFirstExtendsList(){
            return this.extendsList.get(0).build();
    }

    public ClassRef buildLastExtendsList(){
            return this.extendsList.get(extendsList.size() - 1).build();
    }

    public ClassRef buildMatchingExtendsList(Predicate<ClassRefBuilder> predicate){
            for (ClassRefBuilder item: extendsList) { if(predicate.test(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingExtendsList(Predicate<ClassRefBuilder> predicate){
            for (ClassRefBuilder item: extendsList) { if(predicate.test(item)){return true;} } return false;
    }

    public A withExtendsList(List<ClassRef> extendsList){
            if (this.extendsList != null) { _visitables.get("extendsList").removeAll(this.extendsList);}
            if (extendsList != null) {this.extendsList = new ArrayList<ClassRefBuilder>(); for (ClassRef item : extendsList){this.addToExtendsList(item);}} else { this.extendsList = new ArrayList<ClassRefBuilder>();} return (A) this;
    }

    public A withExtendsList(ClassRef... extendsList){
            if (this.extendsList != null) {this.extendsList.clear();}
            if (extendsList != null) {for (ClassRef item :extendsList){ this.addToExtendsList(item);}} return (A) this;
    }

    public Boolean hasExtendsList(){
            return extendsList != null && !extendsList.isEmpty();
    }

    public TypeDefFluent.ExtendsListNested<A> addNewExtendsList(){
            return new ExtendsListNestedImpl();
    }

    public TypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(ClassRef item){
            return new ExtendsListNestedImpl(-1, item);
    }

    public TypeDefFluent.ExtendsListNested<A> setNewExtendsListLike(int index,ClassRef item){
            return new ExtendsListNestedImpl(index, item);
    }

    public TypeDefFluent.ExtendsListNested<A> editExtendsList(int index){
            if (extendsList.size() <= index) throw new RuntimeException("Can't edit extendsList. Index exceeds size.");
            return setNewExtendsListLike(index, buildExtendsList(index));
    }

    public TypeDefFluent.ExtendsListNested<A> editFirstExtendsList(){
            if (extendsList.size() == 0) throw new RuntimeException("Can't edit first extendsList. The list is empty.");
            return setNewExtendsListLike(0, buildExtendsList(0));
    }

    public TypeDefFluent.ExtendsListNested<A> editLastExtendsList(){
            int index = extendsList.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last extendsList. The list is empty.");
            return setNewExtendsListLike(index, buildExtendsList(index));
    }

    public TypeDefFluent.ExtendsListNested<A> editMatchingExtendsList(Predicate<ClassRefBuilder> predicate){
            int index = -1;
            for (int i=0;i<extendsList.size();i++) { 
            if (predicate.test(extendsList.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching extendsList. No match found.");
            return setNewExtendsListLike(index, buildExtendsList(index));
    }

    public A addToImplementsList(int index,ClassRef item){
            if (this.implementsList == null) {this.implementsList = new ArrayList<ClassRefBuilder>();}
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("implementsList").add(index >= 0 ? index : _visitables.get("implementsList").size(), builder);this.implementsList.add(index >= 0 ? index : implementsList.size(), builder); return (A)this;
    }

    public A setToImplementsList(int index,ClassRef item){
            if (this.implementsList == null) {this.implementsList = new ArrayList<ClassRefBuilder>();}
            ClassRefBuilder builder = new ClassRefBuilder(item);
            if (index < 0 || index >= _visitables.get("implementsList").size()) { _visitables.get("implementsList").add(builder); } else { _visitables.get("implementsList").set(index, builder);}
            if (index < 0 || index >= implementsList.size()) { implementsList.add(builder); } else { implementsList.set(index, builder);}
             return (A)this;
    }

    public A addToImplementsList(ClassRef... items){
            if (this.implementsList == null) {this.implementsList = new ArrayList<ClassRefBuilder>();}
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("implementsList").add(builder);this.implementsList.add(builder);} return (A)this;
    }

    public A addAllToImplementsList(Collection<ClassRef> items){
            if (this.implementsList == null) {this.implementsList = new ArrayList<ClassRefBuilder>();}
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("implementsList").add(builder);this.implementsList.add(builder);} return (A)this;
    }

    public A removeFromImplementsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("implementsList").remove(builder);if (this.implementsList != null) {this.implementsList.remove(builder);}} return (A)this;
    }

    public A removeAllFromImplementsList(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("implementsList").remove(builder);if (this.implementsList != null) {this.implementsList.remove(builder);}} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildImplementsList instead.
 * @return The buildable object.
 */
@Deprecated public List<ClassRef> getImplementsList(){
            return build(implementsList);
    }

    public List<ClassRef> buildImplementsList(){
            return build(implementsList);
    }

    public ClassRef buildImplementsList(int index){
            return this.implementsList.get(index).build();
    }

    public ClassRef buildFirstImplementsList(){
            return this.implementsList.get(0).build();
    }

    public ClassRef buildLastImplementsList(){
            return this.implementsList.get(implementsList.size() - 1).build();
    }

    public ClassRef buildMatchingImplementsList(Predicate<ClassRefBuilder> predicate){
            for (ClassRefBuilder item: implementsList) { if(predicate.test(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingImplementsList(Predicate<ClassRefBuilder> predicate){
            for (ClassRefBuilder item: implementsList) { if(predicate.test(item)){return true;} } return false;
    }

    public A withImplementsList(List<ClassRef> implementsList){
            if (this.implementsList != null) { _visitables.get("implementsList").removeAll(this.implementsList);}
            if (implementsList != null) {this.implementsList = new ArrayList<ClassRefBuilder>(); for (ClassRef item : implementsList){this.addToImplementsList(item);}} else { this.implementsList = new ArrayList<ClassRefBuilder>();} return (A) this;
    }

    public A withImplementsList(ClassRef... implementsList){
            if (this.implementsList != null) {this.implementsList.clear();}
            if (implementsList != null) {for (ClassRef item :implementsList){ this.addToImplementsList(item);}} return (A) this;
    }

    public Boolean hasImplementsList(){
            return implementsList != null && !implementsList.isEmpty();
    }

    public TypeDefFluent.ImplementsListNested<A> addNewImplementsList(){
            return new ImplementsListNestedImpl();
    }

    public TypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(ClassRef item){
            return new ImplementsListNestedImpl(-1, item);
    }

    public TypeDefFluent.ImplementsListNested<A> setNewImplementsListLike(int index,ClassRef item){
            return new ImplementsListNestedImpl(index, item);
    }

    public TypeDefFluent.ImplementsListNested<A> editImplementsList(int index){
            if (implementsList.size() <= index) throw new RuntimeException("Can't edit implementsList. Index exceeds size.");
            return setNewImplementsListLike(index, buildImplementsList(index));
    }

    public TypeDefFluent.ImplementsListNested<A> editFirstImplementsList(){
            if (implementsList.size() == 0) throw new RuntimeException("Can't edit first implementsList. The list is empty.");
            return setNewImplementsListLike(0, buildImplementsList(0));
    }

    public TypeDefFluent.ImplementsListNested<A> editLastImplementsList(){
            int index = implementsList.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last implementsList. The list is empty.");
            return setNewImplementsListLike(index, buildImplementsList(index));
    }

    public TypeDefFluent.ImplementsListNested<A> editMatchingImplementsList(Predicate<ClassRefBuilder> predicate){
            int index = -1;
            for (int i=0;i<implementsList.size();i++) { 
            if (predicate.test(implementsList.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching implementsList. No match found.");
            return setNewImplementsListLike(index, buildImplementsList(index));
    }

    public A addToParameters(int index,TypeParamDef item){
            if (this.parameters == null) {this.parameters = new ArrayList<TypeParamDefBuilder>();}
            TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.get("parameters").add(index >= 0 ? index : _visitables.get("parameters").size(), builder);this.parameters.add(index >= 0 ? index : parameters.size(), builder); return (A)this;
    }

    public A setToParameters(int index,TypeParamDef item){
            if (this.parameters == null) {this.parameters = new ArrayList<TypeParamDefBuilder>();}
            TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
            if (index < 0 || index >= _visitables.get("parameters").size()) { _visitables.get("parameters").add(builder); } else { _visitables.get("parameters").set(index, builder);}
            if (index < 0 || index >= parameters.size()) { parameters.add(builder); } else { parameters.set(index, builder);}
             return (A)this;
    }

    public A addToParameters(TypeParamDef... items){
            if (this.parameters == null) {this.parameters = new ArrayList<TypeParamDefBuilder>();}
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.get("parameters").add(builder);this.parameters.add(builder);} return (A)this;
    }

    public A addAllToParameters(Collection<TypeParamDef> items){
            if (this.parameters == null) {this.parameters = new ArrayList<TypeParamDefBuilder>();}
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.get("parameters").add(builder);this.parameters.add(builder);} return (A)this;
    }

    public A removeFromParameters(TypeParamDef... items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.get("parameters").remove(builder);if (this.parameters != null) {this.parameters.remove(builder);}} return (A)this;
    }

    public A removeAllFromParameters(Collection<TypeParamDef> items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.get("parameters").remove(builder);if (this.parameters != null) {this.parameters.remove(builder);}} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildParameters instead.
 * @return The buildable object.
 */
@Deprecated public List<TypeParamDef> getParameters(){
            return build(parameters);
    }

    public List<TypeParamDef> buildParameters(){
            return build(parameters);
    }

    public TypeParamDef buildParameter(int index){
            return this.parameters.get(index).build();
    }

    public TypeParamDef buildFirstParameter(){
            return this.parameters.get(0).build();
    }

    public TypeParamDef buildLastParameter(){
            return this.parameters.get(parameters.size() - 1).build();
    }

    public TypeParamDef buildMatchingParameter(Predicate<TypeParamDefBuilder> predicate){
            for (TypeParamDefBuilder item: parameters) { if(predicate.test(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingParameter(Predicate<TypeParamDefBuilder> predicate){
            for (TypeParamDefBuilder item: parameters) { if(predicate.test(item)){return true;} } return false;
    }

    public A withParameters(List<TypeParamDef> parameters){
            if (this.parameters != null) { _visitables.get("parameters").removeAll(this.parameters);}
            if (parameters != null) {this.parameters = new ArrayList<TypeParamDefBuilder>(); for (TypeParamDef item : parameters){this.addToParameters(item);}} else { this.parameters = new ArrayList<TypeParamDefBuilder>();} return (A) this;
    }

    public A withParameters(TypeParamDef... parameters){
            if (this.parameters != null) {this.parameters.clear();}
            if (parameters != null) {for (TypeParamDef item :parameters){ this.addToParameters(item);}} return (A) this;
    }

    public Boolean hasParameters(){
            return parameters != null && !parameters.isEmpty();
    }

    public TypeDefFluent.ParametersNested<A> addNewParameter(){
            return new ParametersNestedImpl();
    }

    public TypeDefFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item){
            return new ParametersNestedImpl(-1, item);
    }

    public TypeDefFluent.ParametersNested<A> setNewParameterLike(int index,TypeParamDef item){
            return new ParametersNestedImpl(index, item);
    }

    public TypeDefFluent.ParametersNested<A> editParameter(int index){
            if (parameters.size() <= index) throw new RuntimeException("Can't edit parameters. Index exceeds size.");
            return setNewParameterLike(index, buildParameter(index));
    }

    public TypeDefFluent.ParametersNested<A> editFirstParameter(){
            if (parameters.size() == 0) throw new RuntimeException("Can't edit first parameters. The list is empty.");
            return setNewParameterLike(0, buildParameter(0));
    }

    public TypeDefFluent.ParametersNested<A> editLastParameter(){
            int index = parameters.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last parameters. The list is empty.");
            return setNewParameterLike(index, buildParameter(index));
    }

    public TypeDefFluent.ParametersNested<A> editMatchingParameter(Predicate<TypeParamDefBuilder> predicate){
            int index = -1;
            for (int i=0;i<parameters.size();i++) { 
            if (predicate.test(parameters.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching parameters. No match found.");
            return setNewParameterLike(index, buildParameter(index));
    }

    public A addToProperties(int index,Property item){
            if (this.properties == null) {this.properties = new ArrayList<PropertyBuilder>();}
            PropertyBuilder builder = new PropertyBuilder(item);_visitables.get("properties").add(index >= 0 ? index : _visitables.get("properties").size(), builder);this.properties.add(index >= 0 ? index : properties.size(), builder); return (A)this;
    }

    public A setToProperties(int index,Property item){
            if (this.properties == null) {this.properties = new ArrayList<PropertyBuilder>();}
            PropertyBuilder builder = new PropertyBuilder(item);
            if (index < 0 || index >= _visitables.get("properties").size()) { _visitables.get("properties").add(builder); } else { _visitables.get("properties").set(index, builder);}
            if (index < 0 || index >= properties.size()) { properties.add(builder); } else { properties.set(index, builder);}
             return (A)this;
    }

    public A addToProperties(Property... items){
            if (this.properties == null) {this.properties = new ArrayList<PropertyBuilder>();}
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.get("properties").add(builder);this.properties.add(builder);} return (A)this;
    }

    public A addAllToProperties(Collection<Property> items){
            if (this.properties == null) {this.properties = new ArrayList<PropertyBuilder>();}
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.get("properties").add(builder);this.properties.add(builder);} return (A)this;
    }

    public A removeFromProperties(Property... items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.get("properties").remove(builder);if (this.properties != null) {this.properties.remove(builder);}} return (A)this;
    }

    public A removeAllFromProperties(Collection<Property> items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.get("properties").remove(builder);if (this.properties != null) {this.properties.remove(builder);}} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildProperties instead.
 * @return The buildable object.
 */
@Deprecated public List<Property> getProperties(){
            return build(properties);
    }

    public List<Property> buildProperties(){
            return build(properties);
    }

    public Property buildProperty(int index){
            return this.properties.get(index).build();
    }

    public Property buildFirstProperty(){
            return this.properties.get(0).build();
    }

    public Property buildLastProperty(){
            return this.properties.get(properties.size() - 1).build();
    }

    public Property buildMatchingProperty(Predicate<PropertyBuilder> predicate){
            for (PropertyBuilder item: properties) { if(predicate.test(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingProperty(Predicate<PropertyBuilder> predicate){
            for (PropertyBuilder item: properties) { if(predicate.test(item)){return true;} } return false;
    }

    public A withProperties(List<Property> properties){
            if (this.properties != null) { _visitables.get("properties").removeAll(this.properties);}
            if (properties != null) {this.properties = new ArrayList<PropertyBuilder>(); for (Property item : properties){this.addToProperties(item);}} else { this.properties = new ArrayList<PropertyBuilder>();} return (A) this;
    }

    public A withProperties(Property... properties){
            if (this.properties != null) {this.properties.clear();}
            if (properties != null) {for (Property item :properties){ this.addToProperties(item);}} return (A) this;
    }

    public Boolean hasProperties(){
            return properties != null && !properties.isEmpty();
    }

    public TypeDefFluent.PropertiesNested<A> addNewProperty(){
            return new PropertiesNestedImpl();
    }

    public TypeDefFluent.PropertiesNested<A> addNewPropertyLike(Property item){
            return new PropertiesNestedImpl(-1, item);
    }

    public TypeDefFluent.PropertiesNested<A> setNewPropertyLike(int index,Property item){
            return new PropertiesNestedImpl(index, item);
    }

    public TypeDefFluent.PropertiesNested<A> editProperty(int index){
            if (properties.size() <= index) throw new RuntimeException("Can't edit properties. Index exceeds size.");
            return setNewPropertyLike(index, buildProperty(index));
    }

    public TypeDefFluent.PropertiesNested<A> editFirstProperty(){
            if (properties.size() == 0) throw new RuntimeException("Can't edit first properties. The list is empty.");
            return setNewPropertyLike(0, buildProperty(0));
    }

    public TypeDefFluent.PropertiesNested<A> editLastProperty(){
            int index = properties.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last properties. The list is empty.");
            return setNewPropertyLike(index, buildProperty(index));
    }

    public TypeDefFluent.PropertiesNested<A> editMatchingProperty(Predicate<PropertyBuilder> predicate){
            int index = -1;
            for (int i=0;i<properties.size();i++) { 
            if (predicate.test(properties.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching properties. No match found.");
            return setNewPropertyLike(index, buildProperty(index));
    }

    public A addToConstructors(int index,Method item){
            if (this.constructors == null) {this.constructors = new ArrayList<MethodBuilder>();}
            MethodBuilder builder = new MethodBuilder(item);_visitables.get("constructors").add(index >= 0 ? index : _visitables.get("constructors").size(), builder);this.constructors.add(index >= 0 ? index : constructors.size(), builder); return (A)this;
    }

    public A setToConstructors(int index,Method item){
            if (this.constructors == null) {this.constructors = new ArrayList<MethodBuilder>();}
            MethodBuilder builder = new MethodBuilder(item);
            if (index < 0 || index >= _visitables.get("constructors").size()) { _visitables.get("constructors").add(builder); } else { _visitables.get("constructors").set(index, builder);}
            if (index < 0 || index >= constructors.size()) { constructors.add(builder); } else { constructors.set(index, builder);}
             return (A)this;
    }

    public A addToConstructors(Method... items){
            if (this.constructors == null) {this.constructors = new ArrayList<MethodBuilder>();}
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.get("constructors").add(builder);this.constructors.add(builder);} return (A)this;
    }

    public A addAllToConstructors(Collection<Method> items){
            if (this.constructors == null) {this.constructors = new ArrayList<MethodBuilder>();}
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.get("constructors").add(builder);this.constructors.add(builder);} return (A)this;
    }

    public A removeFromConstructors(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.get("constructors").remove(builder);if (this.constructors != null) {this.constructors.remove(builder);}} return (A)this;
    }

    public A removeAllFromConstructors(Collection<Method> items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.get("constructors").remove(builder);if (this.constructors != null) {this.constructors.remove(builder);}} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildConstructors instead.
 * @return The buildable object.
 */
@Deprecated public List<Method> getConstructors(){
            return build(constructors);
    }

    public List<Method> buildConstructors(){
            return build(constructors);
    }

    public Method buildConstructor(int index){
            return this.constructors.get(index).build();
    }

    public Method buildFirstConstructor(){
            return this.constructors.get(0).build();
    }

    public Method buildLastConstructor(){
            return this.constructors.get(constructors.size() - 1).build();
    }

    public Method buildMatchingConstructor(Predicate<MethodBuilder> predicate){
            for (MethodBuilder item: constructors) { if(predicate.test(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingConstructor(Predicate<MethodBuilder> predicate){
            for (MethodBuilder item: constructors) { if(predicate.test(item)){return true;} } return false;
    }

    public A withConstructors(List<Method> constructors){
            if (this.constructors != null) { _visitables.get("constructors").removeAll(this.constructors);}
            if (constructors != null) {this.constructors = new ArrayList<MethodBuilder>(); for (Method item : constructors){this.addToConstructors(item);}} else { this.constructors = new ArrayList<MethodBuilder>();} return (A) this;
    }

    public A withConstructors(Method... constructors){
            if (this.constructors != null) {this.constructors.clear();}
            if (constructors != null) {for (Method item :constructors){ this.addToConstructors(item);}} return (A) this;
    }

    public Boolean hasConstructors(){
            return constructors != null && !constructors.isEmpty();
    }

    public TypeDefFluent.ConstructorsNested<A> addNewConstructor(){
            return new ConstructorsNestedImpl();
    }

    public TypeDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item){
            return new ConstructorsNestedImpl(-1, item);
    }

    public TypeDefFluent.ConstructorsNested<A> setNewConstructorLike(int index,Method item){
            return new ConstructorsNestedImpl(index, item);
    }

    public TypeDefFluent.ConstructorsNested<A> editConstructor(int index){
            if (constructors.size() <= index) throw new RuntimeException("Can't edit constructors. Index exceeds size.");
            return setNewConstructorLike(index, buildConstructor(index));
    }

    public TypeDefFluent.ConstructorsNested<A> editFirstConstructor(){
            if (constructors.size() == 0) throw new RuntimeException("Can't edit first constructors. The list is empty.");
            return setNewConstructorLike(0, buildConstructor(0));
    }

    public TypeDefFluent.ConstructorsNested<A> editLastConstructor(){
            int index = constructors.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last constructors. The list is empty.");
            return setNewConstructorLike(index, buildConstructor(index));
    }

    public TypeDefFluent.ConstructorsNested<A> editMatchingConstructor(Predicate<MethodBuilder> predicate){
            int index = -1;
            for (int i=0;i<constructors.size();i++) { 
            if (predicate.test(constructors.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching constructors. No match found.");
            return setNewConstructorLike(index, buildConstructor(index));
    }

    public A addToMethods(int index,Method item){
            if (this.methods == null) {this.methods = new ArrayList<MethodBuilder>();}
            MethodBuilder builder = new MethodBuilder(item);_visitables.get("methods").add(index >= 0 ? index : _visitables.get("methods").size(), builder);this.methods.add(index >= 0 ? index : methods.size(), builder); return (A)this;
    }

    public A setToMethods(int index,Method item){
            if (this.methods == null) {this.methods = new ArrayList<MethodBuilder>();}
            MethodBuilder builder = new MethodBuilder(item);
            if (index < 0 || index >= _visitables.get("methods").size()) { _visitables.get("methods").add(builder); } else { _visitables.get("methods").set(index, builder);}
            if (index < 0 || index >= methods.size()) { methods.add(builder); } else { methods.set(index, builder);}
             return (A)this;
    }

    public A addToMethods(Method... items){
            if (this.methods == null) {this.methods = new ArrayList<MethodBuilder>();}
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.get("methods").add(builder);this.methods.add(builder);} return (A)this;
    }

    public A addAllToMethods(Collection<Method> items){
            if (this.methods == null) {this.methods = new ArrayList<MethodBuilder>();}
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.get("methods").add(builder);this.methods.add(builder);} return (A)this;
    }

    public A removeFromMethods(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.get("methods").remove(builder);if (this.methods != null) {this.methods.remove(builder);}} return (A)this;
    }

    public A removeAllFromMethods(Collection<Method> items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.get("methods").remove(builder);if (this.methods != null) {this.methods.remove(builder);}} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildMethods instead.
 * @return The buildable object.
 */
@Deprecated public List<Method> getMethods(){
            return build(methods);
    }

    public List<Method> buildMethods(){
            return build(methods);
    }

    public Method buildMethod(int index){
            return this.methods.get(index).build();
    }

    public Method buildFirstMethod(){
            return this.methods.get(0).build();
    }

    public Method buildLastMethod(){
            return this.methods.get(methods.size() - 1).build();
    }

    public Method buildMatchingMethod(Predicate<MethodBuilder> predicate){
            for (MethodBuilder item: methods) { if(predicate.test(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingMethod(Predicate<MethodBuilder> predicate){
            for (MethodBuilder item: methods) { if(predicate.test(item)){return true;} } return false;
    }

    public A withMethods(List<Method> methods){
            if (this.methods != null) { _visitables.get("methods").removeAll(this.methods);}
            if (methods != null) {this.methods = new ArrayList<MethodBuilder>(); for (Method item : methods){this.addToMethods(item);}} else { this.methods = new ArrayList<MethodBuilder>();} return (A) this;
    }

    public A withMethods(Method... methods){
            if (this.methods != null) {this.methods.clear();}
            if (methods != null) {for (Method item :methods){ this.addToMethods(item);}} return (A) this;
    }

    public Boolean hasMethods(){
            return methods != null && !methods.isEmpty();
    }

    public TypeDefFluent.MethodsNested<A> addNewMethod(){
            return new MethodsNestedImpl();
    }

    public TypeDefFluent.MethodsNested<A> addNewMethodLike(Method item){
            return new MethodsNestedImpl(-1, item);
    }

    public TypeDefFluent.MethodsNested<A> setNewMethodLike(int index,Method item){
            return new MethodsNestedImpl(index, item);
    }

    public TypeDefFluent.MethodsNested<A> editMethod(int index){
            if (methods.size() <= index) throw new RuntimeException("Can't edit methods. Index exceeds size.");
            return setNewMethodLike(index, buildMethod(index));
    }

    public TypeDefFluent.MethodsNested<A> editFirstMethod(){
            if (methods.size() == 0) throw new RuntimeException("Can't edit first methods. The list is empty.");
            return setNewMethodLike(0, buildMethod(0));
    }

    public TypeDefFluent.MethodsNested<A> editLastMethod(){
            int index = methods.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last methods. The list is empty.");
            return setNewMethodLike(index, buildMethod(index));
    }

    public TypeDefFluent.MethodsNested<A> editMatchingMethod(Predicate<MethodBuilder> predicate){
            int index = -1;
            for (int i=0;i<methods.size();i++) { 
            if (predicate.test(methods.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching methods. No match found.");
            return setNewMethodLike(index, buildMethod(index));
    }

    
/**
 * This method has been deprecated, please use method buildOuterType instead.
 * @return The buildable object.
 */
@Deprecated public TypeDef getOuterType(){
            return this.outerType!=null?this.outerType.build():null;
    }

    public TypeDef buildOuterType(){
            return this.outerType!=null?this.outerType.build():null;
    }

    public A withOuterType(TypeDef outerType){
            _visitables.get("outerType").remove(this.outerType);
            if (outerType!=null){ this.outerType= new TypeDefBuilder(outerType); _visitables.get("outerType").add(this.outerType);} return (A) this;
    }

    public Boolean hasOuterType(){
            return this.outerType != null;
    }

    public TypeDefFluent.OuterTypeNested<A> withNewOuterType(){
            return new OuterTypeNestedImpl();
    }

    public TypeDefFluent.OuterTypeNested<A> withNewOuterTypeLike(TypeDef item){
            return new OuterTypeNestedImpl(item);
    }

    public TypeDefFluent.OuterTypeNested<A> editOuterType(){
            return withNewOuterTypeLike(getOuterType());
    }

    public TypeDefFluent.OuterTypeNested<A> editOrNewOuterType(){
            return withNewOuterTypeLike(getOuterType() != null ? getOuterType(): new TypeDefBuilder().build());
    }

    public TypeDefFluent.OuterTypeNested<A> editOrNewOuterTypeLike(TypeDef item){
            return withNewOuterTypeLike(getOuterType() != null ? getOuterType(): item);
    }

    public A addToInnerTypes(int index,TypeDef item){
            if (this.innerTypes == null) {this.innerTypes = new ArrayList<TypeDefBuilder>();}
            TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.get("innerTypes").add(index >= 0 ? index : _visitables.get("innerTypes").size(), builder);this.innerTypes.add(index >= 0 ? index : innerTypes.size(), builder); return (A)this;
    }

    public A setToInnerTypes(int index,TypeDef item){
            if (this.innerTypes == null) {this.innerTypes = new ArrayList<TypeDefBuilder>();}
            TypeDefBuilder builder = new TypeDefBuilder(item);
            if (index < 0 || index >= _visitables.get("innerTypes").size()) { _visitables.get("innerTypes").add(builder); } else { _visitables.get("innerTypes").set(index, builder);}
            if (index < 0 || index >= innerTypes.size()) { innerTypes.add(builder); } else { innerTypes.set(index, builder);}
             return (A)this;
    }

    public A addToInnerTypes(TypeDef... items){
            if (this.innerTypes == null) {this.innerTypes = new ArrayList<TypeDefBuilder>();}
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.get("innerTypes").add(builder);this.innerTypes.add(builder);} return (A)this;
    }

    public A addAllToInnerTypes(Collection<TypeDef> items){
            if (this.innerTypes == null) {this.innerTypes = new ArrayList<TypeDefBuilder>();}
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.get("innerTypes").add(builder);this.innerTypes.add(builder);} return (A)this;
    }

    public A removeFromInnerTypes(TypeDef... items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.get("innerTypes").remove(builder);if (this.innerTypes != null) {this.innerTypes.remove(builder);}} return (A)this;
    }

    public A removeAllFromInnerTypes(Collection<TypeDef> items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.get("innerTypes").remove(builder);if (this.innerTypes != null) {this.innerTypes.remove(builder);}} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildInnerTypes instead.
 * @return The buildable object.
 */
@Deprecated public List<TypeDef> getInnerTypes(){
            return build(innerTypes);
    }

    public List<TypeDef> buildInnerTypes(){
            return build(innerTypes);
    }

    public TypeDef buildInnerType(int index){
            return this.innerTypes.get(index).build();
    }

    public TypeDef buildFirstInnerType(){
            return this.innerTypes.get(0).build();
    }

    public TypeDef buildLastInnerType(){
            return this.innerTypes.get(innerTypes.size() - 1).build();
    }

    public TypeDef buildMatchingInnerType(Predicate<TypeDefBuilder> predicate){
            for (TypeDefBuilder item: innerTypes) { if(predicate.test(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingInnerType(Predicate<TypeDefBuilder> predicate){
            for (TypeDefBuilder item: innerTypes) { if(predicate.test(item)){return true;} } return false;
    }

    public A withInnerTypes(List<TypeDef> innerTypes){
            if (this.innerTypes != null) { _visitables.get("innerTypes").removeAll(this.innerTypes);}
            if (innerTypes != null) {this.innerTypes = new ArrayList<TypeDefBuilder>(); for (TypeDef item : innerTypes){this.addToInnerTypes(item);}} else { this.innerTypes = new ArrayList<TypeDefBuilder>();} return (A) this;
    }

    public A withInnerTypes(TypeDef... innerTypes){
            if (this.innerTypes != null) {this.innerTypes.clear();}
            if (innerTypes != null) {for (TypeDef item :innerTypes){ this.addToInnerTypes(item);}} return (A) this;
    }

    public Boolean hasInnerTypes(){
            return innerTypes != null && !innerTypes.isEmpty();
    }

    public TypeDefFluent.InnerTypesNested<A> addNewInnerType(){
            return new InnerTypesNestedImpl();
    }

    public TypeDefFluent.InnerTypesNested<A> addNewInnerTypeLike(TypeDef item){
            return new InnerTypesNestedImpl(-1, item);
    }

    public TypeDefFluent.InnerTypesNested<A> setNewInnerTypeLike(int index,TypeDef item){
            return new InnerTypesNestedImpl(index, item);
    }

    public TypeDefFluent.InnerTypesNested<A> editInnerType(int index){
            if (innerTypes.size() <= index) throw new RuntimeException("Can't edit innerTypes. Index exceeds size.");
            return setNewInnerTypeLike(index, buildInnerType(index));
    }

    public TypeDefFluent.InnerTypesNested<A> editFirstInnerType(){
            if (innerTypes.size() == 0) throw new RuntimeException("Can't edit first innerTypes. The list is empty.");
            return setNewInnerTypeLike(0, buildInnerType(0));
    }

    public TypeDefFluent.InnerTypesNested<A> editLastInnerType(){
            int index = innerTypes.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last innerTypes. The list is empty.");
            return setNewInnerTypeLike(index, buildInnerType(index));
    }

    public TypeDefFluent.InnerTypesNested<A> editMatchingInnerType(Predicate<TypeDefBuilder> predicate){
            int index = -1;
            for (int i=0;i<innerTypes.size();i++) { 
            if (predicate.test(innerTypes.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching innerTypes. No match found.");
            return setNewInnerTypeLike(index, buildInnerType(index));
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            TypeDefFluentImpl that = (TypeDefFluentImpl) o;
            if (kind != null ? !kind.equals(that.kind) :that.kind != null) return false;
            if (packageName != null ? !packageName.equals(that.packageName) :that.packageName != null) return false;
            if (name != null ? !name.equals(that.name) :that.name != null) return false;
            if (comments != null ? !comments.equals(that.comments) :that.comments != null) return false;
            if (annotations != null ? !annotations.equals(that.annotations) :that.annotations != null) return false;
            if (extendsList != null ? !extendsList.equals(that.extendsList) :that.extendsList != null) return false;
            if (implementsList != null ? !implementsList.equals(that.implementsList) :that.implementsList != null) return false;
            if (parameters != null ? !parameters.equals(that.parameters) :that.parameters != null) return false;
            if (properties != null ? !properties.equals(that.properties) :that.properties != null) return false;
            if (constructors != null ? !constructors.equals(that.constructors) :that.constructors != null) return false;
            if (methods != null ? !methods.equals(that.methods) :that.methods != null) return false;
            if (outerType != null ? !outerType.equals(that.outerType) :that.outerType != null) return false;
            if (innerTypes != null ? !innerTypes.equals(that.innerTypes) :that.innerTypes != null) return false;
            return true;
    }


    public class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<TypeDefFluent.AnnotationsNested<N>> implements TypeDefFluent.AnnotationsNested<N>,Nested<N>{

            private final AnnotationRefBuilder builder;
        private final int index;
    
            AnnotationsNestedImpl(int index,AnnotationRef item){
                    this.index = index;
                    this.builder = new AnnotationRefBuilder(this, item);
            }
            AnnotationsNestedImpl(){
                    this.index = -1;
                    this.builder = new AnnotationRefBuilder(this);
            }
    
    public N and(){
            return (N) TypeDefFluentImpl.this.setToAnnotations(index, builder.build());
    }
    public N endAnnotation(){
            return and();
    }

}
    public class ExtendsListNestedImpl<N> extends ClassRefFluentImpl<TypeDefFluent.ExtendsListNested<N>> implements TypeDefFluent.ExtendsListNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
        private final int index;
    
            ExtendsListNestedImpl(int index,ClassRef item){
                    this.index = index;
                    this.builder = new ClassRefBuilder(this, item);
            }
            ExtendsListNestedImpl(){
                    this.index = -1;
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) TypeDefFluentImpl.this.setToExtendsList(index, builder.build());
    }
    public N endExtendsList(){
            return and();
    }

}
    public class ImplementsListNestedImpl<N> extends ClassRefFluentImpl<TypeDefFluent.ImplementsListNested<N>> implements TypeDefFluent.ImplementsListNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
        private final int index;
    
            ImplementsListNestedImpl(int index,ClassRef item){
                    this.index = index;
                    this.builder = new ClassRefBuilder(this, item);
            }
            ImplementsListNestedImpl(){
                    this.index = -1;
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) TypeDefFluentImpl.this.setToImplementsList(index, builder.build());
    }
    public N endImplementsList(){
            return and();
    }

}
    public class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<TypeDefFluent.ParametersNested<N>> implements TypeDefFluent.ParametersNested<N>,Nested<N>{

            private final TypeParamDefBuilder builder;
        private final int index;
    
            ParametersNestedImpl(int index,TypeParamDef item){
                    this.index = index;
                    this.builder = new TypeParamDefBuilder(this, item);
            }
            ParametersNestedImpl(){
                    this.index = -1;
                    this.builder = new TypeParamDefBuilder(this);
            }
    
    public N and(){
            return (N) TypeDefFluentImpl.this.setToParameters(index, builder.build());
    }
    public N endParameter(){
            return and();
    }

}
    public class PropertiesNestedImpl<N> extends PropertyFluentImpl<TypeDefFluent.PropertiesNested<N>> implements TypeDefFluent.PropertiesNested<N>,Nested<N>{

            private final PropertyBuilder builder;
        private final int index;
    
            PropertiesNestedImpl(int index,Property item){
                    this.index = index;
                    this.builder = new PropertyBuilder(this, item);
            }
            PropertiesNestedImpl(){
                    this.index = -1;
                    this.builder = new PropertyBuilder(this);
            }
    
    public N and(){
            return (N) TypeDefFluentImpl.this.setToProperties(index, builder.build());
    }
    public N endProperty(){
            return and();
    }

}
    public class ConstructorsNestedImpl<N> extends MethodFluentImpl<TypeDefFluent.ConstructorsNested<N>> implements TypeDefFluent.ConstructorsNested<N>,Nested<N>{

            private final MethodBuilder builder;
        private final int index;
    
            ConstructorsNestedImpl(int index,Method item){
                    this.index = index;
                    this.builder = new MethodBuilder(this, item);
            }
            ConstructorsNestedImpl(){
                    this.index = -1;
                    this.builder = new MethodBuilder(this);
            }
    
    public N and(){
            return (N) TypeDefFluentImpl.this.setToConstructors(index, builder.build());
    }
    public N endConstructor(){
            return and();
    }

}
    public class MethodsNestedImpl<N> extends MethodFluentImpl<TypeDefFluent.MethodsNested<N>> implements TypeDefFluent.MethodsNested<N>,Nested<N>{

            private final MethodBuilder builder;
        private final int index;
    
            MethodsNestedImpl(int index,Method item){
                    this.index = index;
                    this.builder = new MethodBuilder(this, item);
            }
            MethodsNestedImpl(){
                    this.index = -1;
                    this.builder = new MethodBuilder(this);
            }
    
    public N and(){
            return (N) TypeDefFluentImpl.this.setToMethods(index, builder.build());
    }
    public N endMethod(){
            return and();
    }

}
    public class OuterTypeNestedImpl<N> extends TypeDefFluentImpl<TypeDefFluent.OuterTypeNested<N>> implements TypeDefFluent.OuterTypeNested<N>,Nested<N>{

            private final TypeDefBuilder builder;

            OuterTypeNestedImpl(TypeDef item){
                    this.builder = new TypeDefBuilder(this, item);
            }
            OuterTypeNestedImpl(){
                    this.builder = new TypeDefBuilder(this);
            }

    public N and(){
            return (N) TypeDefFluentImpl.this.withOuterType(builder.build());
    }
    public N endOuterType(){
            return and();
    }

}
    public class InnerTypesNestedImpl<N> extends TypeDefFluentImpl<TypeDefFluent.InnerTypesNested<N>> implements TypeDefFluent.InnerTypesNested<N>,Nested<N>{

            private final TypeDefBuilder builder;
        private final int index;
    
            InnerTypesNestedImpl(int index,TypeDef item){
                    this.index = index;
                    this.builder = new TypeDefBuilder(this, item);
            }
            InnerTypesNestedImpl(){
                    this.index = -1;
                    this.builder = new TypeDefBuilder(this);
            }
    
    public N and(){
            return (N) TypeDefFluentImpl.this.setToInnerTypes(index, builder.build());
    }
    public N endInnerType(){
            return and();
    }

}


}
