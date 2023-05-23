package com.cyl.ctrbt.mapper;

import java.io.Serializable;
import lombok.Data;

/**
 * chat_user_info
 * @author 
 */
@Data
public class ChatUserInfo implements Serializable {
    private String id;

    private String bizKey;

    private String openId;

    private String sessionKey;

    private Long chatSum;

    private static final long serialVersionUID = 1L;
}