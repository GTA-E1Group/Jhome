package com.jhome.common.shiro.realm;

import com.shiro.common.ShiroProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "shiro" )
public class SysShiroProperties extends ShiroProperties {
    public Map filterChainDefinition;
    public String filterChainDefinitionMap;
    public String LoginUrl;
    public String SuccessUrl;
    public String UnauthorizedUrl;
    public LinkedHashMap getFilterChainDefinitionMap()
    {
        return  super.ConvertMap(filterChainDefinitionMap);
    }
}
