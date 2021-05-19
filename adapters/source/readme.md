# Source adapter (github java parser)

This adapter allows adaption github java parser artifacts to the sundro model.

## Using the Adapters

Given object `TypeDeclaration t` you can convert it to `io.sundr.model.TypeDef` using:

      TypeDef def = Adapters.adaptType(c, AdapterContext.getContext());

## Utilities

In most cases no one, will create directly instances of `TypeDeclaration`. These are instances expected to be the result of parsing a source file.

To go directly from source to `TypeDef` additional utilities are provided:

    try (FileInputStream is = new FileInputStream("MyType.java")) {
      TypeDef typeDef = Sources.readTypeDefFromStream(is);
    }


An additional utility that allows you to parse classpath resources can be used:

    TypeDef typeDef = Sources.readTypeDefFromResource("MyType.java");
    
This is something that makes sense to use for things like testing etc.
