package com.nettyServer.Server;

import com.nettyServer.Config.ConfigInfo;
import org.apache.thrift.server.THsHaServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author : Daxv
 * @date : 23:18 2020/5/19 0019
 */

@Component
public class AppContext {
    @Autowired
    public ConfigInfo configInfo;
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private THsHaServer tHsHaServer;
    private final Logger logger = LoggerFactory.getLogger(AppContext.class);
    public AppContext() {
    }

    /**
     * 启动服务器
     */
    public void run() {
        try {
            logger.info("启动WebSocket服务器！...");
            new Thread(webSocketServer.InitWebSocketServer(configInfo.getWebSocketPort())).start();
            logger.info("启动RPC服务器！...");
            tHsHaServer.serve();
            logger.info("启动完毕！...");
        } catch (Exception ex) {
            logger.info(String.format("启动服务器失败：s%", ex.getMessage().toString()));
            this.close();
        }
    }

    /**
     * 关闭服务器
     */
    public void close() {
        logger.info("关闭启动Netty WebSocket服务器！...");
        webSocketServer.close();
        logger.info("关闭RPC服务器！...");
        tHsHaServer.stop();
    }
}
