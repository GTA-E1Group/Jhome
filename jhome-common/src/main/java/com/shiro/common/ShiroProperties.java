package com.shiro.common;

import java.util.Arrays;
import java.util.LinkedHashMap;

public abstract class ShiroProperties {
    public LinkedHashMap ConvertMap( String filterChainDefinitionMap)
    {
        LinkedHashMap<String,String> filetLMap = new LinkedHashMap();
        Arrays.asList(filterChainDefinitionMap.split(",")).forEach(c->{
            String[] item= c.split(":");
            if(item.length>0)
            {
                filetLMap.put(item[0].trim(),item[1].trim());
            }
        });
        return filetLMap;
    }
}
