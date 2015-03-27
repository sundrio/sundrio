package me.dsl.examples.curator.expected;

public interface GetDataInterface extends WatchedInterface<GetDataInterface>, 
        InBackgroundInterface<GetDataInterface>,
        StoringStatInInterface<GetDataInterface>,
        ForPathInterface<String> {

}
