package com.nettyServer.Server;

import com.nettyServer.Model.ResponseJson;
import com.nettyServer.Service.impl.ChatServiceImpl;
import com.nettyServer.Util.Constant;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

/**
 * @author : Daxv
 * @date : 16:15 2020/5/14 0014
 */
@Component
@ChannelHandler.Sharable
public class WebSocketServerHandler  extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());
    @Autowired
    public ChatServiceImpl chatService;

    /**
     * 描述：读取完连接的消息后，对消息进行处理。
     *      这里主要是处理WebSocket请求
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        handlerWebSocketFrame(ctx,frame);
    }

    /**
     * 处理请求
     * @param ctx
     * @param frame
     */
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //关闭请求
        if(frame instanceof  CloseWebSocketFrame)
        {
            WebSocketServerHandshaker handshaker=  Constant.webSocketHandshakerMap.get(ctx.channel().id().asLongText());
            if (handshaker == null) {
                sendErrorMessage(ctx, "不存在的客户端连接！");
            } else {
                handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            }
            return;
        }
        //ping 请求
        if(frame instanceof  PingWebSocketFrame)
        {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
        }
        // 只支持文本格式，不支持二进制消息
        if(!(frame instanceof  TextWebSocketFrame))
        {
            sendErrorMessage(ctx,"仅支持文本(Text)格式，不支持二进制消息");
        }
        // 客服端发送过来的消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("服务器端收到消息："+request);
        JSONObject param = null;

        try {
            param = JSONObject.parseObject(request);
        }catch (Exception ex)
        {
            sendErrorMessage(ctx, "JSON字符串转换出错！");
            ex.printStackTrace();
        }
        if (param == null) {
            sendErrorMessage(ctx, "参数为空！");
            return;
        }
        String type = (String) param.get("type");

        switch (type) {
            case "REGISTER":
                chatService.register(param, ctx);
                break;
            case "SINGLE_SENDING":
                chatService.singleSend(param, ctx);
                break;
            case "GROUP_SENDING":
                chatService.groupSend(param, ctx);
                break;
            case "FILE_MSG_SINGLE_SENDING":
                chatService.FileMsgSingleSend(param, ctx);
                break;
            case "FILE_MSG_GROUP_SENDING":
                chatService.FileMsgGroupSend(param, ctx);
                break;
            default:
                chatService.typeError(ctx);
                break;
        }
    }

    /**
     * 描述：客户端断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        chatService.remove(ctx);
    }

    /**
     * 异常捕获
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("出现异常的连接:"+ctx.channel().id().asLongText());
        ctx.close();
    }


    /**
     * 加入到ChannelHandler
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("通道加入的连接："+ctx.channel().id().asLongText());
    }

    /**
     * 连接关闭
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println("通道关闭的连接："+ctx.channel().id().asLongText());

    }

    private void sendErrorMessage(ChannelHandlerContext ctx, String errorMsg) {
        String responseJson=new ResponseJson()
                .error(errorMsg)
                .toString();
        ctx.channel().writeAndFlush(new TextWebSocketFrame(responseJson));
    }
}
