package com.example.demo.point.netty.client;

import com.example.demo.point.netty.encoder.MessageDecoder;
import com.example.demo.point.netty.encoder.MessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * @Auther ziyu.wei
 * @date 2021/7/29 11:27 上午
 */
public class EchoClient {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));


    public static void main(String[] args) throws Exception{
        System.out.println("echo client main");

        final SslContext sslContext;
        if (SSL) {
            sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslContext = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslContext != null) {
                                p.addLast(sslContext.newHandler(ch.alloc(), HOST, PORT));
                            }

                            p.addLast(new MessageDecoder());
                            p.addLast(new MessageEncoder());
                            p.addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture f = b.connect(HOST, PORT).sync();
            System.out.println("echo client main server bootstrap 配置启动完成");

            f.channel().closeFuture().sync();
            System.out.println("echo client end.");
        } finally {
            group.shutdownGracefully();
        }
    }
}
