package com.model1235.chat.server.model;

import lombok.Data;

/**
 * @author hl
 * @date : 2019/4/19 6:00 PM
 * @description
 */
@Data
public class ImMessage {

    /**
     * 0心跳
     * 1消息
     */
    private Byte type;

    private Byte targetType;

    private Long targetId;

    private String message;

    private Long timestamp;

}