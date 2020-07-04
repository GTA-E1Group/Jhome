package com.jhome.modules.config;
import com.jhome.autoconfiguration.SysConfigurationPropertiesBean;
import com.jhome.common.shiro.filter.*;
import com.jhome.common.shiro.realm.*;
import com.jhome.modules.sys.cert.AppShiroRealm;
import com.jhome.modules.sys.cert.CasAuthorizingRealm;
import com.jhome.modules.sys.cert.CustomRealm;
import com.jhome.modules.sys.cert.ThirdPathShiroRealm;
import com.shiro.common.SimpleCredentialsMatcher.CustomCredentialsMatcher;
import com.shiro.common.session.ShiroSessionFactory;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
@Configuration
public class ShiroConfig {

    //    @Autowired
//    public SysShiroProperties spro;
    @Bean(name = "SysConfigurationPropertiesBean")
    public SysConfigurationPropertiesBean sysConfigurationPropertiesBean() {
        return new SysConfigurationPropertiesBean();
    }


    @Bean(name = "SysShiroProperties")
    public SysShiroProperties sysShiroProperties() {
        return new SysShiroProperties();
    }



    /**
     * 注入 ShiroFilterFactoryBean
     *
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, AppShiroRealm appShiroRealm, CustomRealm customRealm, ThirdPathShiroRealm thirdPathShiroRealm) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        try {

            Map<String, Filter> filterMap=shiroFilterFactoryBean.getFilters();

            //filterMap.put("inner", shiroInnerFilter());
            //filterMap.put("cas", shiroCasFilter());
            filterMap.put("authc", shiroAuthcFilter(customRealm));
            filterMap.put("logout", shiroLogoutFilter(customRealm));
            filterMap.put("perms", shiroPermsFilter());
            filterMap.put("roles", shiroRolesFilter());
            filterMap.put("user", shiroUserFilter());

            shiroFilterFactoryBean.setLoginUrl(sysShiroProperties().getLoginUrl());//登录页面
            //shiroFilterFactoryBean.setSuccessUrl(sysShiroProperties().getSuccessUrl());//登录成功页面 首页
            shiroFilterFactoryBean.setUnauthorizedUrl(sysShiroProperties().getUnauthorizedUrl());//没有权限访问 错误页面，认证不通过跳转
            // 设置拦截器
            Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
            filterChainDefinitionMap=sysShiroProperties().getFilterChainDefinitionMap();
            // 开放登陆接口
//            filterChainDefinitionMap.put("/login", "anon");
//            // 静态资源放行
//            filterChainDefinitionMap.put("/static/**", "anon");
//            filterChainDefinitionMap.put("/images/**", "anon");
//            filterChainDefinitionMap.put("/css/**","anon");
//            filterChainDefinitionMap.put("/script/**", "anon");
//            filterChainDefinitionMap.put("/logout", "anon");
//            //其余接口一律拦截
//            //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
//            filterChainDefinitionMap.put("/userfeedback/**", "anon");
//            filterChainDefinitionMap.put("/**", "authc");

            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
            shiroFilterFactoryBean.setSecurityManager(securityManager);
        } catch (Exception ex) {
            System.err.println(String.format("Shiro 报错：%s", ex.getMessage().toString()));
        }

        return shiroFilterFactoryBean;
    }

    /**
     * 注入 SecurityManager
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置认证源头
        securityManager.setAuthenticator(new CustomizedModularRealmAuthenticator());
        // 设置Realm
        Collection<Realm> realms = new ArrayList<>();
        realms.add(customRealm());//后台系统数据认证
        realms.add(thirdPathShiroRealm());//第三方数据认证
        realms.add(appShiroRealm());//手机端数据认证
        realms.add(casAuthorizingRealm());//单点登录数认证
        securityManager.setRealms(realms);

        //设置会话
        securityManager.setSessionManager(sessionManager());
        //注入Cookie(记住我)管理器(remenberMeManager)
        //securityManager.setRememberMeManager(this.rememberMeManager());

        return securityManager;
    }



    /**
     * 生成代理，通过代理进行控制
     */
    @Bean
    //@DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 加入注解
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     *   会话Cookie模板 30天缓存
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //cookie的name,对应的默认是 JSESSIONID
        SimpleCookie simpleCookie = new SimpleCookie("JhomeCookie");
        simpleCookie.setMaxAge(2592000);//30天
        // jsessionId的path为 / 用于多个系统共享jsessionId
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }
    /**
    *@Description 关闭浏览器 Cookie失效 Add+2020-05-13
    *@Param
    *@Return
    *@Author Mr.Ren
    *@Date
    *@Time
    */
    @Bean
    public SimpleCookie sessionIdCookie() {
        //cookie的name,对应的默认是 JSESSIONID
        SimpleCookie simpleCookie = new SimpleCookie("JhomeCookie");
        simpleCookie.setMaxAge(-1);//30天
        // jsessionId的path为 / 用于多个系统共享jsessionId
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    /**
     * 设置记住我
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        byte[] cipherKey = Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(cipherKey);
        return cookieRememberMeManager;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行 .
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {

        //DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        JhomeDefaultWebSessionManager sessionManager = new JhomeDefaultWebSessionManager();
        //会话验证器调度时间 设置全局会话超时时间，默认30分钟(1800000)
        sessionManager.setSessionValidationInterval(1800000);
        //定时检查失效的session
        sessionManager.setSessionValidationSchedulerEnabled(false);
        //是否在会话过期后会调用SessionDAO的delete方法删除会话 默认true
        sessionManager.setDeleteInvalidSessions(true);
        //实现session存储
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookie(rememberMeCookie());
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionFactory(sessionFactory());
        return sessionManager;
    }

    /**
     * 定义自己的SessionFactory
     * @return
     */
    @Bean
    public SessionFactory sessionFactory()
    {
        ShiroSessionFactory sessionFactory=new ShiroSessionFactory();
        return  sessionFactory;
    }
    /**
     * 密码比较器
     *
     * @return
     */
    @Bean
    public CustomCredentialsMatcher customCredentialsMatcher() {
        return new CustomCredentialsMatcher();
    }




    /**
     * @param
     * @return
     * @describe Cs Realm
     */
    @Bean
    public CasAuthorizingRealm casAuthorizingRealm() {
        CasAuthorizingRealm casAuthorizingRealm = new CasAuthorizingRealm();
        return casAuthorizingRealm;
    }


    /**
     * @param
     * @return
     * @describe 自定义AppRealm
     */
    @Bean
    public AppShiroRealm appShiroRealm() {
        AppShiroRealm appShiroRealm = new AppShiroRealm();
        appShiroRealm.setName("AppRealm");
        appShiroRealm.setCredentialsMatcher(customCredentialsMatcher());
        return appShiroRealm;
    }


    /**
     * @param
     * @return
     * @describe 自定义AppRealm
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setName("PcRealm");
        customRealm.setCredentialsMatcher(customCredentialsMatcher());
        //customRealm.setCachingEnabled(true);
        return customRealm;
    }


    /**
     * @param
     * @return
     * @describe 第三方 自定义ThirdRealm
     */
    @Bean
    public ThirdPathShiroRealm thirdPathShiroRealm() {
        ThirdPathShiroRealm thirdPathShiroRealm = new ThirdPathShiroRealm();
        thirdPathShiroRealm.setName("ThirdPathRealm");
        thirdPathShiroRealm.setCredentialsMatcher(customCredentialsMatcher());
        return thirdPathShiroRealm;
    }

    /**
     * shiro 会话管理
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(RedisSessionDao.class)
    public RedisSessionDao redisSessionDAO() {
        return new RedisSessionDao();
    }


    /**
     * 内部系统访问过滤器
     */
/*
    private InnerFilter shiroInnerFilter() {
        return new InnerFilter();
    }
*/


    /**
     * Form登录过滤器
     */
    private FormAuthenticationFilter shiroAuthcFilter(AuthorizingRealm authorizingRealm) {
        FormAuthenticationFilter bean = new FormAuthenticationFilter();
        //bean.setAuthorizingRealm(authorizingRealm);
        return bean;
    }

    /**
     * 登出过滤器
     */
    private LogoutFilter shiroLogoutFilter(AuthorizingRealm authorizingRealm) {
        LogoutFilter bean = new LogoutFilter();
        //bean.setAuthorizingRealm(authorizingRealm);
        return bean;
    }

    /**
     * 权限字符串过滤器
     */
    private PermissionsAuthorizationFilter shiroPermsFilter() {
        return new PermissionsAuthorizationFilter();
    }

    /**
     * 角色权限过滤器
     */
    private RolesAuthorizationFilter shiroRolesFilter() {
        return new RolesAuthorizationFilter();
    }

    /**
     * 用户权限过滤器
     */
    private UserFilter shiroUserFilter() {
        return new UserFilter();
    }



}
