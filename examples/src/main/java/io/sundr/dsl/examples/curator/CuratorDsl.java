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

package io.sundr.dsl.examples.curator;

import io.sundr.dsl.annotations.Dsl;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.Keyword;
import io.sundr.dsl.annotations.TargetName;
import io.sundr.dsl.annotations.Terminal;

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
    void delete();
    
    @EntryPoint
    @ExistsOption
    void exists();

    @EntryPoint
    @CreateOption
    void create();
    
    @CreateOption
    void createParentsIfNeeded();
    
    @DeleteOption
    void deleteChildrenIfNeeded();

    @Keyword
    @ExistsOption
    void storingStatIn(Object stat);

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
    void inBackground();

    @Terminal
    @CreateOption
    @GetDataOption
    @DeleteOption
    @SetDataOption
    @ExistsOption
    String forPath(String path);


    @Terminal
    @SetDataOption
    @TargetName("ForPathAndBytesInterface")        
    void forPath(String path, byte[] data);


}
