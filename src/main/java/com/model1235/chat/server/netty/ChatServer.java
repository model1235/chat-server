package com.model1235.chat.server.netty;

import com.model1235.chat.server.channel.DefaultInChannelHandler;
import com.model1235.chat.server.channel.DefaultOutChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.BindException;

/**
 * @author hl
 * @date : 2019/4/19 4:09 PM
 * @description
 */
@Slf4j
public class ChatServer {

    private int port = 9988;

    public void start(){

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
//                .childHandler(new StringEncoder())
//                .childHandler(new JsonObjectDecoder())
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch){
                        ch.pipeline().addFirst(new DefaultInChannelHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            log.info("监听端口:{}",port);
            ChannelFuture future = server.bind(port)
                    .addListener(f->{
                        Throwable cause = f.cause();
                        if(cause!=null&&(cause instanceof BindException)){
                            log.error("端口被占用");
                        }
                    }).sync();

            log.info("future.isSuccess():{}",future.isSuccess());

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }finally{
            log.info("关闭程序");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }



}