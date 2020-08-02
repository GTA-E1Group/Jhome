package com.nettyService.Config;

import com.nettyService.Service.impl.SocketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rpc.common.thrift.socketService;

/**
 * @author : Daxv
 * @date : 22:26 2020/5/19 0019
 */
@Slf4j
@Configuration
public class RpcConfiguration {
    @Autowired
    public ConfigInfo configInfo;
    @Autowired
    private SocketServiceImpl socketServiceImpl;
    @Bean
    public THsHaServer tHsHaServer() throws TTransportException {
        log.info("开始注入 rpc服务器端组件");
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(configInfo.getRpcPort());
        THsHaServer.Args args = new THsHaServer.Args(serverSocket).minWorkerThreads(configInfo.getMinWorkerThreads()).maxWorkerThreads(configInfo.getMaxWorkerThreads());
        socketService.Processor<SocketServiceImpl> processor = new socketService.Processor<>(socketServiceImpl);
        args.processorFactory(new TProcessorFactory(processor));
        args.protocolFactory(new TCompactProtocol.Factory());
        args.transportFactory(new TFramedTransport.Factory());
        THsHaServer tServer = new THsHaServer(args);
        log.info("THsHaServer 注入成功");
        return tServer;
    }

}
