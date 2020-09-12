package com.nettyService.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author : Daxv
 * @date : 18:06 2020/5/14 0014
*/

@Component
public class WebSocketChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Autowired
    public HttpRequestHandler httpRequestHandler;
    @Autowired
    public WebSocketServerHandler webSocketServerHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline=socketChannel.pipeline();
        // HTTP编码解码器
        pipeline.addLast("http-codec",new HttpServerCodec());
        // 把HTTP头、HTTP体拼成完整的HTTP请求
        pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
        // 方便大文件传输，不过实质上都是短的文本数据
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
        pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));
        //读写空闲处理器
        //pipeline.addLast("", new IdleStateHandler(5,5,10, TimeUnit.SECONDS));
        pipeline.addLast("http-handler", httpRequestHandler);
        pipeline.addLast("websocket-handler",webSocketServerHandler);

    }
}
