package com.service;

import com.service.config.EnableSpring;
import com.service.server.AppContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * //
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` ':.          ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @description: 可靠性消息服务
 * @author: Daxv
 * @create: 2020-09-16 18:32
 **/
@EnableSpring
public class JhomeTransactionService {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JhomeTransactionService.class);
        AppContext appContext = applicationContext.getBean(AppContext.class);
        try {
            System.out.println("开启可靠消息服务...");
            appContext.run();
            //Bio 线程池模式 重复利用已有的线程
            System.out.println("可靠消息服务启动成功");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
