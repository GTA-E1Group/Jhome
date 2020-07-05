package com.nettyServer.Server;

import com.daxu.common.Bus.ResponseJson;
import com.nettyServer.Util.Constant;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : Daxv
 * @date : 10:42 2020/5/15 0015
 * 升级Http协议
 * http 1.0 keeplive 0s
 * 1.2 keeplive 3s
 */
@Component
@ChannelHandler.Sharable
public class HttpRequestHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            ctx.fireChannelRead(((WebSocketFrame) msg).retain());
        } else if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            //ctx.channel().writeAndFlush(String.format("222222222222"));
        }
    }

    /**
     * 描述：处理Http请求，主要是完成HTTP协议到Websocket协议的升级
     *
     * @param ctx
     * @param
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest msg) {
        if (msg.decoderResult().isSuccess()) {

            sendHttpResponse(ctx, msg,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws:/" + ctx.channel() + "/websocket", null, false);
        WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(msg);
        Constant.webSocketHandshakerMap.put(ctx.channel().id().asLongText(), handshaker);

        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), msg);
        }

    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest msg, DefaultFullHttpResponse defaultFullHttpResponse) {
        // 返回应答给客户端
/*        if (msg.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(msg.status().toString(), CharsetUtil.UTF_8);
            msg.content().writeBytes(buf);
            buf.release();
        }*/
        // 如果是非Keep-Alive，关闭连接
        boolean keepAlive = HttpUtil.isKeepAlive(msg);
        ChannelFuture f = ctx.channel().writeAndFlush(msg);
        if (!keepAlive) {
            f.addListener(ChannelFutureListener.CLOSE);
        }

    }

    /**
     * 异常捕获
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void sendErrorMessage(ChannelHandlerContext ctx, String errorMsg) {
        String responseJson = new ResponseJson()
                .error(errorMsg)
                .toString();
        ctx.channel().writeAndFlush(new TextWebSocketFrame(responseJson));
    }
}
