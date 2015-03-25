package me.codegen.model;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.LinkedHashSet;
import java.util.Set;

public class JavaClazzFluent<T extends JavaClazzFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {
        private JavaType type ;
        
	public T withType(JavaType type) {
		this.type=type;
		return (T)this;
	}
                        
	public JavaType getType() {
		return this.type;
	}
                
	public TypeNested<T> addType() {
		return new TypeNested<T>();
	}
        
	public class TypeNested<N> extends JavaTypeFluent<TypeNested<N>> implements Nested<N> {
		private final JavaTypeBuilder builder = new JavaTypeBuilder(this);
		public N endType() { return and(); }
		public N and() {
			return (N) withType(builder.build());
		}
	}
            private Set<JavaMethod> methods  = new LinkedHashSet();
        
	public T withMethods(Set methods) {
		this.methods=methods;
		return (T)this;
	}
        
	public T addToMethods(JavaMethod item) {
		this.methods.add(item);
		return (T)this;
	}
                
	public Set<JavaMethod> getMethods() {
		return this.methods;
	}
                
	public MethodsNested<T> addMethods() {
		return new MethodsNested<T>();
	}
        
	public class MethodsNested<N> extends JavaMethodFluent<MethodsNested<N>> implements Nested<N> {
		private final JavaMethodBuilder builder = new JavaMethodBuilder(this);
		public N endMethods() { return and(); }
		public N and() {
			return (N) addToMethods(builder.build());
		}
	}
            private Set<JavaMethod> constructors  = new LinkedHashSet();
        
	public T withConstructors(Set constructors) {
		this.constructors=constructors;
		return (T)this;
	}
        
	public T addToConstructors(JavaMethod item) {
		this.constructors.add(item);
		return (T)this;
	}
                
	public Set<JavaMethod> getConstructors() {
		return this.constructors;
	}
                
	public ConstructorsNested<T> addConstructors() {
		return new ConstructorsNested<T>();
	}
        
	public class ConstructorsNested<N> extends JavaMethodFluent<ConstructorsNested<N>> implements Nested<N> {
		private final JavaMethodBuilder builder = new JavaMethodBuilder(this);
		public N endConstructors() { return and(); }
		public N and() {
			return (N) addToConstructors(builder.build());
		}
	}
            private Set<JavaProperty> fields  = new LinkedHashSet();
        
	public T withFields(Set fields) {
		this.fields=fields;
		return (T)this;
	}
        
	public T addToFields(JavaProperty item) {
		this.fields.add(item);
		return (T)this;
	}
                
	public Set<JavaProperty> getFields() {
		return this.fields;
	}
                
	public FieldsNested<T> addFields() {
		return new FieldsNested<T>();
	}
        
	public class FieldsNested<N> extends JavaPropertyFluent<FieldsNested<N>> implements Nested<N> {
		private final JavaPropertyBuilder builder = new JavaPropertyBuilder(this);
		public N endFields() { return and(); }
		public N and() {
			return (N) addToFields(builder.build());
		}
	}
            private Set<JavaType> imports  = new LinkedHashSet();
        
	public T withImports(Set imports) {
		this.imports=imports;
		return (T)this;
	}
        
	public T addToImports(JavaType item) {
		this.imports.add(item);
		return (T)this;
	}
                
	public Set<JavaType> getImports() {
		return this.imports;
	}
                
	public ImportsNested<T> addImports() {
		return new ImportsNested<T>();
	}
        
	public class ImportsNested<N> extends JavaTypeFluent<ImportsNested<N>> implements Nested<N> {
		private final JavaTypeBuilder builder = new JavaTypeBuilder(this);
		public N endImports() { return and(); }
		public N and() {
			return (N) addToImports(builder.build());
		}
	}
    }