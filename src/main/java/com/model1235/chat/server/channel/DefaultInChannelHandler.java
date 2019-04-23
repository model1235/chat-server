package com.model1235.chat.server.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hl
 * @date : 2019/4/22 2:01 PM
 * @description
 */
@Slf4j
public class DefaultInChannelHandler extends ChannelInboundHandlerAdapter {

    private List<Channel> lists;

    public DefaultInChannelHandler(List<Channel> lists){
        this.lists = lists;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAdded");
        lists.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved");
        lists.remove(ctx.channel());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive");
        log.info("this.hash:{}",this.hashCode());
        ctx.pipeline();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            ByteBuf msg1 = (ByteBuf) msg;
            String s = msg1.toString(Charset.forName("utf-8"));
            if(StringUtil.isNullOrEmpty(s)){
                return;
            }
            log.info("channelRead:{}",s);
            byte[] bytes = s.getBytes("utf-8");
            lists.stream().filter(x->!ctx.channel().equals(x))
                    .forEach(x->{
                        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
                        buffer.writeBytes(bytes);
                        x.writeAndFlush(buffer);
                    });
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("channelReadComplete");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("userEventTriggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.info("channelWritabilityChanged");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("exceptionCaught",cause);
    }
}