package com.model1235.chat.server.pipline;

import com.model1235.chat.server.channel.DefaultInChannelHandler;
import com.model1235.chat.server.channel.DefaultOutChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hl
 * @date : 2019/4/22 5:34 PM
 * @description
 */
@Slf4j
public class ChatroomPipline extends ChannelInitializer<SocketChannel> {

    private List<Channel> lists = Collections.synchronizedList(new ArrayList());

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        log.info("有新连接:ip:{},port:{}",ch.remoteAddress().getAddress(),ch.remoteAddress().getPort());
        ch.pipeline().addFirst(new DefaultOutChannelHandler(),new DefaultInChannelHandler(lists));
    }
}