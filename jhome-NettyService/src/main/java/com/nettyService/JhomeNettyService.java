package com.nettyService;

import com.nettyService.Config.EnableSpring;
import com.nettyService.Server.AppContext;
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
 * @program: jhome-root
 * @description: JhomeNettyService  消息中心
 * @author: Daxv
 * @create: 2017-08-02 13:51
 **/
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
