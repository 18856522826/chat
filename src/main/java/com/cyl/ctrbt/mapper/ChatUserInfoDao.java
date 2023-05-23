package com.cyl.ctrbt.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatUserInfoDao {

    int deleteByPrimaryKey(String id);

    int insert(ChatUserInfo record);

    int insertSelective(ChatUserInfo record);

    ChatUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChatUserInfo record);

    int updateByPrimaryKey(ChatUserInfo record);

    ChatUserInfo selectByBizKey(String bizKey);

    ChatUserInfo selectByOpenId(String openId);
}