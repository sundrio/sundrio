/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.dsl.internal.processor;

import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.dsl.internal.element.functions.ToClasses;
import io.sundr.dsl.internal.element.functions.ToKeywords;
import io.sundr.dsl.internal.element.functions.ToRequiresAll;
import io.sundr.dsl.internal.element.functions.ToRequiresAny;
import io.sundr.dsl.internal.element.functions.ToRequiresNoneOf;
import io.sundr.dsl.internal.element.functions.ToRequiresOnly;
import io.sundr.dsl.internal.graph.NodeContext;
import io.sundr.dsl.internal.graph.NodeRepository;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class DslContext {

    private final Elements elements;
    private final Types types;
    private final CodegenContext codegenContext;

    private final ToRequiresAny toRequiresAny;
    private final ToRequiresAll toRequiresAll;
    private final ToRequiresNoneOf toRequiresNoneOf;
    private final ToRequiresOnly toRequiresOnly;
    private final ToKeywords toKeywords;
    private final ToClasses toClasses;

    private final NodeRepository nodeRepository = new NodeRepository();



    public DslContext(Elements elements, Types types) {
        this.elements = elements;
        this.types = types;
        this.codegenContext = CodegenContext.create(elements, types);
        this.toRequiresAny = new ToRequiresAny(elements);
        this.toRequiresAll = new ToRequiresAll(elements);
        this.toRequiresNoneOf = new ToRequiresNoneOf(elements);
        this.toRequiresOnly = new ToRequiresOnly(elements);
        this.toKeywords = new ToKeywords(elements);
        this.toClasses = new ToClasses(elements);
    }

    public Elements getElements() {
        return elements;
    }

    public Types getTypes() {
        return types;
    }

    public CodegenContext getCodegenContext() {
        return codegenContext;
    }

    public NodeRepository getNodeRepository() {
        return nodeRepository;
    }


    public DefinitionRepository getDefinitionRepository() {
        return codegenContext.getDefinitionRepository();
    }

    public ToRequiresAny getToRequiresAny() {
        return toRequiresAny;
    }

    public ToRequiresAll getToRequiresAll() {
        return toRequiresAll;
    }

    public ToRequiresNoneOf getToRequiresNoneOf() {
        return toRequiresNoneOf;
    }

    public ToRequiresOnly getToRequiresOnly() {
        return toRequiresOnly;
    }

    public ToKeywords getToKeywords() {
        return toKeywords;
    }

    public ToClasses getToClasses() {
        return toClasses;
    }

}
