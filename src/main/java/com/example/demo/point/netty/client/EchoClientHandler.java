package com.example.demo.point.netty.client;

import com.example.demo.point.netty.encoder.Header;
import com.example.demo.point.netty.encoder.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther ziyu.wei
 * @date 2021/7/29 11:48 上午
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");

        String content = "hello, this is netty client";
        Header header = new Header((byte)0, (byte)1, (byte)1, (byte)1, (byte)0, "913f17ca614361fb257dc6741332cnbb",content.getBytes("UTF-8").length, 1);
        Message message = new Message(header,content);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message msg1 = (Message)msg;
        System.out.println(msg1.getData());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel read complete");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
