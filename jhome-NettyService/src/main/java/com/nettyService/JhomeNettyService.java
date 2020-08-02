package com.nettyService;

import com.nettyService.Config.EnableSpring;
import com.nettyService.Server.AppContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : Daxv
 * @date : 0:43 2020/5/16 0016
 */
@EnableSpring
public class JhomeNettyService {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JhomeNettyService.class);
        AppContext appContext = applicationContext.getBean(AppContext.class);
        try {
            appContext.run();
        } catch (Exception ex) {
            appContext.close();
        }
    }
}
