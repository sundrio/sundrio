package me.codegen.model;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JavaTypeFluent<T extends JavaTypeFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {
        private JavaKind kind ;
        
	public T withKind(JavaKind kind) {
		this.kind=kind;
		return (T)this;
	}
                        
	public JavaKind getKind() {
		return this.kind;
	}
                private String packageName ;
        
	public T withPackageName(String packageName) {
		this.packageName=packageName;
		return (T)this;
	}
                        
	public String getPackageName() {
		return this.packageName;
	}
                private String className ;
        
	public T withClassName(String className) {
		this.className=className;
		return (T)this;
	}
                        
	public String getClassName() {
		return this.className;
	}
	private boolean array ;

	public T withArray(boolean array) {
		this.array=array;
		return (T)this;
	}

	public boolean isArray() {
		return this.array;
	}
	private boolean collection ;
        
	public T withCollection(boolean collection) {
		this.collection=collection;
		return (T)this;
	}
                        
	public boolean isCollection() {
		return this.collection;
	}
    private boolean concrete ;
        
	public T withConcrete(boolean concrete) {
		this.concrete=concrete;
		return (T)this;
	}
                        
	public boolean isConcrete() {
		return this.concrete;
	}
   private JavaType defaultImplementation ;
        
	public T withDefaultImplementation(JavaType defaultImplementation) {
		this.defaultImplementation=defaultImplementation;
		return (T)this;
	}
                        
	public JavaType getDefaultImplementation() {
		return this.defaultImplementation;
	}
                
	public DefaultImplementationNested<T> addDefaultImplementation() {
		return new DefaultImplementationNested<T>();
	}
        
	public class DefaultImplementationNested<N> extends JavaTypeFluent<DefaultImplementationNested<N>> implements Nested<N> {
		private final JavaTypeBuilder builder = new JavaTypeBuilder(this);
		public N endDefaultImplementation() { return and(); }
		public N and() {
			return (N) withDefaultImplementation(builder.build());
		}
	}
            private JavaType superClass ;
        
	public T withSuperClass(JavaType superClass) {
		this.superClass=superClass;
		return (T)this;
	}
                        
	public JavaType getSuperClass() {
		return this.superClass;
	}
                
	public SuperClassNested<T> addSuperClass() {
		return new SuperClassNested<T>();
	}
        
	public class SuperClassNested<N> extends JavaTypeFluent<SuperClassNested<N>> implements Nested<N> {
		private final JavaTypeBuilder builder = new JavaTypeBuilder(this);
		public N endSuperClass() { return and(); }
		public N and() {
			return (N) withSuperClass(builder.build());
		}
	}
            private Set<JavaType> interfaces  = new LinkedHashSet();
        
	public T withInterfaces(Set interfaces) {
		this.interfaces=interfaces;
		return (T)this;
	}
        
	public T addToInterfaces(JavaType item) {
		this.interfaces.add(item);
		return (T)this;
	}
                
	public Set<JavaType> getInterfaces() {
		return this.interfaces;
	}
                
	public InterfacesNested<T> addInterfaces() {
		return new InterfacesNested<T>();
	}
        
	public class InterfacesNested<N> extends JavaTypeFluent<InterfacesNested<N>> implements Nested<N> {
		private final JavaTypeBuilder builder = new JavaTypeBuilder(this);
		public N endInterfaces() { return and(); }
		public N and() {
			return (N) addToInterfaces(builder.build());
		}
	}
            private final List<JavaType> genericTypes = new ArrayList<>();
        
	public T withGenericTypes(JavaType[] genericTypes) {
		this.genericTypes.clear();
		for(JavaType item : genericTypes) {
			this.genericTypes.add(item);
		}
		return (T)this;
	}
        
	public T addToGenericTypes(JavaType item) {
		this.genericTypes.add(item);
		return (T)this;
	}
        
	public JavaType[] getGenericTypes() {
		return this.genericTypes.toArray(new JavaType[genericTypes.size()]);
	}
                
	public GenericTypesNested<T> addGenericTypes() {
		return new GenericTypesNested<T>();
	}
        
	public class GenericTypesNested<N> extends JavaTypeFluent<GenericTypesNested<N>> implements Nested<N> {
		private final JavaTypeBuilder builder = new JavaTypeBuilder(this);
		public N endGenericTypes() { return and(); }
		public N and() {
			return (N) addToGenericTypes(builder.build());
		}
	}
    }