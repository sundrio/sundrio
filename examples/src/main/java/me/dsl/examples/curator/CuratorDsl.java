package me.dsl.examples.curator;

import me.dsl.annotations.Dsl;
import me.dsl.annotations.EntryPoint;
import me.dsl.annotations.Keyword;
import me.dsl.annotations.TargetName;
import me.dsl.annotations.Terminal;
import me.dsl.annotations.Transition;

@Dsl
@TargetName("CuratorFramework")
public interface CuratorDsl {

    
    @EntryPoint
    @Transition(any = GetDataOption.class)        
    void getData();
    
    @EntryPoint
    @Transition(any = SetDataOption.class)            
    void setData();

    @EntryPoint
    @Transition(any = DeleteDataOption.class)        
    void deleteData();
    
    @EntryPoint
    @Transition(any = ExistsOption.class)
    void checkExists();
    
    @CreateOption
    @Transition(any = CreateOption.class)
    void createParentsIfNeeded();
    
    @DeleteDataOption
    void deleteChildrenIfNeeded();

    @Keyword
    @ExistsOption
    public void storingStatIn(Object stat);

    @Keyword
    @CreateOption        
    void withMode(Integer mode);
    
    @Keyword
    @GetDataOption
    public void watched();

    @Keyword
    @GetDataOption
    @SetDataOption
    @ExistsOption
    public void inBackground();

    @Terminal
    @GetDataOption
    @SetDataOption
    @ExistsOption
    void forPath(String path);

    @Terminal
    @SetDataOption
    @TargetName("ForPathAndBytesInterface")        
    void forPath(String path, byte[] data);

}
