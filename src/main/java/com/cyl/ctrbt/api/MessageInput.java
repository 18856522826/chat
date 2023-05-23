package com.cyl.ctrbt.api;

import lombok.Data;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2023/4/23
 * @since 1.0
 */
@Data
public class MessageInput {
    /**
     * 用户ID
     */
    private String userId;
    /**
     *  信息输入
     */
    private String messageText;
}
