package com.nettyServer;

import com.nettyServer.Config.EnableSpring;
import com.nettyServer.Server.AppContext;
import com.nettyServer.Server.WebSocketServer;
import org.apache.thrift.server.THsHaServer;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Logger;

/**
 * @author : Daxv
 * @date : 0:43 2020/5/16 0016
 */
@EnableSpring
public class JhomeNettyServer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JhomeNettyServer.class);
        AppContext appContext = applicationContext.getBean(AppContext.class);
        try {
            appContext.run();
        } catch (Exception ex) {
            appContext.close();
        }
    }

}
