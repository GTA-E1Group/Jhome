package com.nettyService.Server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author : Daxv
 * @date : 17:35 2020/5/17 0017
 * 空闲状态监测
 */

public class MyIdleStateHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event= (IdleStateEvent) evt;
        String eventStr="";
       switch (event.state())
       {
           case ALL_IDLE:
               eventStr="读写空闲";
               break;
           case READER_IDLE:
               eventStr="读空闲";
               break;
           case WRITER_IDLE:
               eventStr="写空闲";
               break;
       }

    }
}
