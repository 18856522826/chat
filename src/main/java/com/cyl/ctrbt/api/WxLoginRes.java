package com.cyl.ctrbt.api;

import lombok.Data;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2023/4/27
 * @since 1.0
 */
@Data
public class WxLoginRes {
    /**
     * 会话密钥
     */
    private String session_key;
    /**
     * 用户在开放平台的唯一标识符
     */
    private String unionid;
    /**
     * 错误信息
     */
    private String errmsg;
    /**
     * 用户唯一标识符
     */
    private String openid;
    /**
     * 错误码
     */
    private String errcode;
}
