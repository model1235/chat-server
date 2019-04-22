package com.model1235.chat.server.channel;

import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

/**
 * @author hl
 * @date : 2019/4/22 2:01 PM
 * @description
 */
@Slf4j
public class DefaultOutChannelHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        log.info("bind");
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        log.info("connect");
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        log.info("disconnect");
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        log.info("close");
    }

    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        log.info("deregister");
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        log.info("read");
        ctx.read();
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("write:{}",msg);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        log.info("flush");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("exceptionCaught");
    }
}