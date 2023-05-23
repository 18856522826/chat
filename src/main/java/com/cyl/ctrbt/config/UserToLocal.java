package com.cyl.ctrbt.config;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/3/23
 * @since 1.0
 */
public class UserToLocal {
    private static final ThreadLocal<GuserInfo> USER_LOCAL = new ThreadLocal<>();
    public static void setUserLocal(GuserInfo guserInfo){
        USER_LOCAL.set(guserInfo);
    }
    public static String getUserId(){
        return USER_LOCAL.get().getUserId();
    }
    public static String gettoken(){
        return USER_LOCAL.get().getToken();
    }
    public static void removeUserLocal(){
        USER_LOCAL.remove();
    }
}
