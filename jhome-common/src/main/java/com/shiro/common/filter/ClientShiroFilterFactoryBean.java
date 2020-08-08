package com.shiro.common.filter;

import com.bracket.common.ToolKit.StringUtil;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.Filter;



/**
 * ClientShiroFilterFactoryBean 添加两个方法setFiltersStr、setFilterChainDefinitionsStr，方便在properties文件中配置拦截器和定义过滤链
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class ClientShiroFilterFactoryBean extends ShiroFilterFactoryBean implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setFiltersStr(String filters) {
        if(StringUtil.isBlank(filters)) {
            return;
        }
        String[] filterArray = filters.split(";");
        for(String filter : filterArray) {
            String[] o = filter.split("=");
            getFilters().put(o[0], (Filter) applicationContext.getBean(o[1]));
        }
    }

    public void setFilterChainDefinitionsStr(String filterChainDefinitions) {
        if(StringUtil.isBlank(filterChainDefinitions)) {
            return;
        }
        String[] chainDefinitionsArray = filterChainDefinitions.split(";");
        for(String filter : chainDefinitionsArray) {
            String[] o = filter.split("=");
            getFilterChainDefinitionMap().put(o[0], o[1]);
        }
    }
}
