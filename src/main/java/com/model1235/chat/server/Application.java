package com.model1235.chat.server;

import com.model1235.chat.server.netty.ChatServer;

/**
 * @author hl
 * @date : 2019/4/19 4:05 PM
 * @description
 */
public class Application {


    public static void main(String[] s){
        new ChatServer().start();

    }

}