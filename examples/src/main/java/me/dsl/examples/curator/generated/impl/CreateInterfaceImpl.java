package me.dsl.examples.curator.generated.impl;

import me.dsl.examples.curator.generated.CreateInterface;
import me.dsl.examples.curator.generated.ForPathInterface;
import me.dsl.examples.curator.generated.WithModeForPathInterface;

public class CreateInterfaceImpl implements CreateInterface {
    
    
    public WithModeForPathInterface<String> createParentsIfNeeded() {
        return this;
    }

    public String forPath(String path) {
        return "end";
    }

    public ForPathInterface<String> withMode(Integer mode) {
        return (ForPathInterface<String>) this;
    }
    
    static {
        new CreateInterfaceImpl().createParentsIfNeeded().withMode(1).forPath("somePath");
        new CreateInterfaceImpl().withMode(1).forPath("somePath");
        new CreateInterfaceImpl().createParentsIfNeeded().forPath("somePath");
        new CreateInterfaceImpl().forPath("somePath");
    }
}
