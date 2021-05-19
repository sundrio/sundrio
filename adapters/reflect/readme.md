# Reflection adapter

This adapter allows adaption of `Class` instances to the sundrio model, using reflection.

## Using the Adapters

Given object `Class x` you can convert it to `io.sundr.model.TypeDef` using:

      TypeDef def = Adapters.adaptType(c, AdapterContext.getContext());

