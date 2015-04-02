package me.dsl.examples.curator.generated.impl;

import me.dsl.examples.curator.InBackgroundForPathForPathAndBytesInterface;
import me.dsl.examples.curator.generated.ForPathForPathAndBytesInterface;
import me.dsl.examples.curator.generated.ForPathInterface;
import me.dsl.examples.curator.generated.InBackgroundForPathInterface;
import me.dsl.examples.curator.generated.SetDataInterface;

public class SetDataInterfaceImpl implements SetDataInterface {


    static {
        new SetDataInterfaceImpl().forPath("somePath");
        new SetDataInterfaceImpl().forPath("somePath", (byte) 0);
        new SetDataInterfaceImpl().inBackground().forPath("somePath");
        new SetDataInterfaceImpl().forPath("somePath");
        new SetDataInterfaceImpl().watched().forPath("somePath");
    }

    @Override
    public Void forPath(String path, byte data) {
        return null;
    }

    @Override
    public String forPath(String path) {
        return "";
    }

    @Override
    public me.dsl.examples.curator.ForPathForPathAndBytesInterface<String, Void> inBackground() {
        return null;
    }

    @Override
    public InBackgroundForPathForPathAndBytesInterface<Void, String> watched() {
        return null;
    }
}
