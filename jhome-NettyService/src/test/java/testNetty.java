
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : Daxv
 * @date : 23:23 2020/5/16 0016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class testNetty {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventExecutors=new NioEventLoopGroup();
        try {
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(null);
            ChannelFuture channelFuture= bootstrap.connect("localhost", Integer.parseInt("3333")).sync();
            channelFuture.channel().close().sync();
        }
        catch (Exception d)
        {
            eventExecutors.shutdownGracefully();
        }
        finally {
            eventExecutors.shutdownGracefully();
        }

    }


}
