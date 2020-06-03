package com.jhome.modules.config.web;

import com.jhome.autoconfiguration.SysConfigurationPropertiesBean;

import com.jhome.modules.ymx.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springMvc 容器配置-扩展配置
 * 扫描 restController
 */

@Configuration

//@ComponentScan(
//        basePackages = {"com.jhome.modules.*"},
//        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {RestController.class})},
//        useDefaultFilters = false)
public class springMvcConfig implements WebMvcConfigurer {
    @Autowired
    public SysConfigurationPropertiesBean spro;


    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //此处添加需要注册的拦截器...
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
    }

    /**
     * 添加 viewControlle映射
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //System.out.println(String.format("@@@@@@@@@@@@@@@@@@@ %s",spro.getAdminPath()));
        //registry.addViewController(String.format("/%s/sys/error",spro.getAdminPath())).setViewName("/modules/sys/error");
        //registry.addViewController(String.format("/%s/sys/main",spro.getAdminPath())).setViewName("/modules/sys/main");
        // registry.addViewController(String.format("/%s/sys/login*",spro.getAdminPath())).setViewName("/modules/sys/login");
        //registry.addViewController("/sys/login").setViewName("/modules/sys/login");
    }

    /**
     * 文件上传下载组件
     *
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 10);
        return multipartResolver;
    }

    /**
     * 试图解析器
     * @return
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");
        viewResolver.setPrefix("classpath:/templates/");
        return viewResolver;
    }


}
