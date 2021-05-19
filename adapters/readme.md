# Sundrio Java Model Adapters

The adapters allow to adapt existing java type representations to the sundrio model. 

Available Adapters:

- [Annotation Processing model (javax.lang.model)](apt/readme.md)
- [Reflection model (java.lang.reflect)](reflect/readme.md)
- [Source model (github java parser)](source/readme.md)


## Using the Adapters

Given object `t` which is a type representation (e.g. `class`, `TypeElement` or `TypeDeclaration`) you can convert it to `io.sundr.model.TypeDef` using:

    TypeDef def = Adapters.adaptType(t, new AdapterContext.getContext());
   

All types that are referenced by `t` either directly or transitively will be proccessed and stored in a `io.sundr.model.repo.DefinitionRepository` that is part of the `AdapterContext`.
The model itself is quite flat and 100% decoupled from the `DefinitionRepository`. By, flat we mean that there is no nesting, so referenced types (e.g. extended / implemented types) are only referenced by id (fully qualified name).

So, in order to grab the actual `TypeDef` of the superclass one needs, to lookup the repo, either directly or using available utilities like `GetDefinition.of`:

    ClassRef superClassRef = typeDef.getExtendsList().get(0);
    TypeDef superClassDef = GetDefinition.of(superClassRef);
