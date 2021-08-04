package com.example.demo.point.netty.server;

import com.example.demo.point.netty.encoder.Header;
import com.example.demo.point.netty.encoder.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther ziyu.wei
 * @date 2021/7/29 10:12 上午
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Message msg1 = (Message)msg;
        System.out.println(msg1.getData());

        String content = "hello, this is nettyServer";

        Header header = new Header((byte)0, (byte)1, (byte)1, (byte)1, (byte)0, "813f17ca614361fb257dc6741332caf1", content.getBytes().length, 1);
        Message message = new Message(header, content);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("echo server hanlder channel read complete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
