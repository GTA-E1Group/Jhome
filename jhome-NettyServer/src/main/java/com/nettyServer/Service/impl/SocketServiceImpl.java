package com.nettyServer.Service.impl;

import com.nettyServer.Service.ChatService;
import com.rpc.common.thrift.socketService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Daxv
 * @date : 22:53 2020/5/19 0019
 * 远程SocketService调用接口实现类
 */
@Service
public class SocketServiceImpl implements socketService.Iface {
    @Autowired
    private ChatService chatService;

    @Override
    public void singleSend(String param) throws TException {
        chatService.singleSendByRemotely(param);
    }

    @Override
    public void groupSend(String param) throws TException {
        chatService.groupSendByRemotely(param);

    }
}
