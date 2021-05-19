# Annotation processing adapter

This adapter allows adaption of `javax.lang.model` classes to the sundrio model.

## Using the Adapters

Given object `TypeElement t` which is a type representation you can convert it to `io.sundr.model.TypeDef` using:

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
      Elements elements = processingEnv.getElementUtils();
      Types types = processingEnv.getTypeUtils();

      TypeDef def = Adapters.adaptType(t, AptContext.create(elements, types));
      ...
    }

In the example above `AptContext` is just a wrapper around `AdapterContext` that also includes `Types` and `Elements` which are internally required for the adaptation process.
