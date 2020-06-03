package com.jhome.modules.config.web;

import com.jhome.autoconfiguration.SysConfigurationPropertiesBean;
import com.jhome.modules.config.MessageConverter;
import com.jhome.modules.sys.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * springMvc 容器配置-扩展配置
 * 扫描 restController
 */
@EnableWebMvc
@Configuration
//@ComponentScan(
//        basePackages = {"com.jhome.modules.*"},
//        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {RestController.class})},
//        useDefaultFilters = false)
public class springMvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/static/",
            "classpath:/public/"};

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
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");
        viewResolver.setPrefix("classpath:/templates/");
        return viewResolver;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MessageConverter());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
