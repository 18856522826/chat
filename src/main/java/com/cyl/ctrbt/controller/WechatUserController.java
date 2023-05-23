package com.cyl.ctrbt.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.seehoo.spg.commons.base.util.JsonHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyl.ctrbt.api.MessageInput;
import com.cyl.ctrbt.api.WxLoginRes;
import com.cyl.ctrbt.config.UserToLocal;
import com.cyl.ctrbt.mapper.ChatUserInfo;
import com.cyl.ctrbt.mapper.ChatUserInfoDao;
import com.cyl.ctrbt.openai.ChatGPTStrreamUtil;
import com.cyl.ctrbt.openai.ChatGPTUtil;
import com.cyl.ctrbt.openai.entity.chat.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2023/4/16
 * @since 1.0
 */
@RestController
@RequestMapping("/api/wechat")
@Slf4j
@AllArgsConstructor
public class WechatUserController {
    /**
     *  聊天机器人
     */
    private final ChatGPTUtil chatGPTUtil;
    private final ChatGPTStrreamUtil chatGPTStrreamUtil;
    /**
     * 用户信息操作
     */
    private final ChatUserInfoDao chatUserInfoDao;

    /**
     * 测试接口
     * @return hello robot
     */
    @GetMapping("/hello")
    public String  helloRobot(){
        chatGPTStrreamUtil.chat("hello robot","xx");
        return "hello robot";
    }

    /**
     * 聊天接口
     * @param messageInput 聊天信息
     * @return
     */
    @PostMapping("/chat")
    public TextMessage chatRobots(@RequestBody MessageInput messageInput) {
        long start=System.currentTimeMillis();
        String userId=UserToLocal.getUserId();
        ChatUserInfo userInfo= chatUserInfoDao.selectByPrimaryKey(userId);
//        if (userInfo==null){
//            return new TextMessage("未找到对应用户,请先登录");
//        }
//        if (userInfo.getChatSum()<=0){
//            return new TextMessage("聊天次数已用完");
//        }
        Message message= chatGPTUtil.chat(messageInput.getMessageText(), messageInput.getUserId());
        TextMessage textMessage;
        textMessage= new TextMessage(message.getContent());
//        userInfo.setChatSum(userInfo.getChatSum()-1);
//        chatUserInfoDao.updateByPrimaryKey(userInfo);
        log.info(System.currentTimeMillis()-start+"ms");
        return textMessage;
    }

    /**
     * 微信登录
     * @param code 微信code
     * @return token
     */
    @PostMapping("/loginByWeixin")
    public String loginByWeixin(@RequestBody String code) {
        log.info("loginByWeixin:{}",code);
        JSONObject jsonObject= JSONObject.parseObject(code);
        code=jsonObject.getString("code");
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx704381a1f625a4a2&secret=e39c23e7802fa4984f30aa688b327ca8&js_code="+code+"&grant_type=authorization_code";
        String res=HttpUtil.get(url);
        log.info("res:{}",res);
        WxLoginRes loginRes= JsonHelper.fromJson(res, WxLoginRes.class);
        //判断用户是否存在不存在则创建
        ChatUserInfo info= chatUserInfoDao.selectByOpenId(loginRes.getOpenid());
        if(info==null){
            info=new ChatUserInfo();
            //生成token
            String token=MD5.create().digestHex(loginRes.getOpenid());
            //token关联用户信息
            String id=IdUtil.fastSimpleUUID();
            info.setId(id);
            info.setBizKey(token);
            info.setChatSum(10L);
            info.setOpenId(loginRes.getOpenid());
            info.setSessionKey(loginRes.getSession_key());
            chatUserInfoDao.insert(info);
        }
        //响应信息
        Map map1=new HashMap<>();
        map1.put("id",info.getId());
        map1.put("token",info.getBizKey());
        final String key="xuxu";
        String ttoken= JWTUtil.createToken(map1,key.getBytes(StandardCharsets.UTF_8));
        return ttoken;
    }
}
