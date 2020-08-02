package com.configService;/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-08-02 13:51
 **/

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

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
 * @description: spring cloud config pei
 * @author: Daxv
 * @create: 2020-08-02 13:51
 **/
@SpringBootApplication
@EnableConfigServer
public class JhomeConfigServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(JhomeConfigServiceApplication.class).bannerMode(Banner.Mode.OFF).run(args);
    }
}
