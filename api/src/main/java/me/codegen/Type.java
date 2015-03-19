package me.codegen;

public interface Type {

    /**
     * @return The package of the type.
     */
    String getPackageName();

    /**
     * @return The class name.
     */
    String getClassName();

    /**
     * @return true if type is an array type.
     */
    boolean isArray();

    /**
     * @return true if type is a Collection type.
     */    
    boolean isCollection();

    /**
     * @return true if type is a concrete type.
     */
    boolean isConcrete();

    /**
     * Return the default implementation of the type.
     * @return
     */
    Type getDefaultImplementation();

    /**
     * @return the super class of the type.
     */
    Type getSuperClass();

    /**
     * An array with the generic types.
     * @return
     */
    Type[] getGenericTypes();
}
