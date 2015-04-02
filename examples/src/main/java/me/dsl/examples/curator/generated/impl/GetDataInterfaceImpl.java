package me.dsl.examples.curator.generated.impl;

import me.dsl.examples.curator.generated.ForPathInterface;
import me.dsl.examples.curator.generated.GetDataInterface;
import me.dsl.examples.curator.generated.InBackgroundForPathInterface;

public class GetDataInterfaceImpl implements GetDataInterface {
    

    static {
        new GetDataInterfaceImpl().watched().forPath("somePath");
        new GetDataInterfaceImpl().inBackground().forPath("somePath");
        new GetDataInterfaceImpl().forPath("somePath");
    }

    @Override
    public String forPath(String path) {
        return null;
    }

    @Override
    public ForPathInterface<String> inBackground() {
        return null;
    }

    @Override
    public InBackgroundForPathInterface<String> watched() {
        return null;
    }
}
