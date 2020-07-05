package com.sysAnnotation.common.annotationParsd;

import com.sysAnnotation.common.jhomeScan.sysScan;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;


/**
 * 注解解析：
 * 一 jdk反射解析
 * 二 spring 容器解析
 */
public class AnnotationContex implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext applicationContex;

    /**
     * 获取容器上下文
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContex=applicationContex;
    }

    /**
     * 监听容器启动或重启的事件
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Class<sysScan> sysScanClass=sysScan.class;
        //从容器中过去所有Class 是sysScan的bean
        Map<String,Object> beans=this.applicationContex.getBeansWithAnnotation(sysScanClass);
        Set<Map.Entry<String,Object>> entrySet= beans.entrySet();
        //遍历取到的beans
        for (Map.Entry<String,Object> item :entrySet)
        {
            String beanId=item.getKey();//获取Bean名字
            Class<? extends  Object> sysClass= item.getValue().getClass();//获取bean对象
            //获取注解类的实例
            sysScan scan= AnnotationUtils.findAnnotation(sysClass,sysScanClass);
            System.out.println(scan.value());

            //循环遍历方法上的注解
            Method[] sysMethod= item.getValue().getClass().getMethods();//获取bean对象
            for (Method method : sysMethod)
            {
                sysScan scanMethod= AnnotationUtils.findAnnotation(method,sysScanClass);
                System.out.println(scanMethod.value());
            }
        }

    }
}
