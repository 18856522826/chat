package com.cyl.ctrbt.config;

import lombok.Data;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2023/5/17
 * @since 1.0
 */
@Data
public class GuserInfo {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 业务ID
     */
    private String token;
}
