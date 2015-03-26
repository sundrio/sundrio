package me.dsl.examples.curator;

import me.dsl.annotations.Dsl;
import me.dsl.annotations.EntryPoint;
import me.dsl.annotations.Keyword;
import me.dsl.annotations.TargetName;
import me.dsl.annotations.Terminal;

@Dsl
@TargetName("CuratorFramework")
public interface CuratorDsl {

    
    @EntryPoint
    @GetDataOption
    void getData();
    
    @EntryPoint
    @SetDataOption
    void setData();

    @EntryPoint
    @DeleteOption
    void deleteData();
    
    @EntryPoint
    @ExistsOption
    void checkExists();
    
    @CreateOption
    void createParentsIfNeeded();
    
    @DeleteOption
    void deleteChildrenIfNeeded();

    @Keyword
    @ExistsOption
    public void storingStatIn(Object stat);

    @Keyword
    @CreateOption        
    void withMode(Integer mode);
    
    @Keyword
    @SetDataOption
    @GetDataOption
    @ExistsOption
    public void watched();

    @Keyword
    @GetDataOption
    @SetDataOption
    @DeleteOption
    @ExistsOption
    public void inBackground();

    @Terminal
    @CreateOption
    @GetDataOption
    @DeleteOption
    @SetDataOption
    @ExistsOption
    void forPath(String path);

    @Terminal
    @SetDataOption
    @TargetName("ForPathAndBytesInterface")        
    void forPath(String path, byte[] data);

}
