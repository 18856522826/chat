package com.cyl.ctrbt.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Notice: 登录拦截器
 *
 * @author xuxu
 * @version 1.0
 * @date 2023/5/4
 * @since 1.0
 */
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String token=request.getHeader("token");
       if (StrUtil.isEmpty(token)){
           if (request.getRequestURI().contains("loginByWeixin")){
               return HandlerInterceptor.super.preHandle(request, response, handler);
           }
           throw new RuntimeException("token is null");
       }
       JWT jwt= JWTUtil.parseToken(request.getHeader("token"));
       JWTPayload payload= jwt.getPayload();
       String id= (String) payload.getClaim("id");
       String bizId= (String) payload.getClaim("token");
        GuserInfo info=new GuserInfo();
        info.setUserId(id);
        info.setToken(bizId);
        UserToLocal.setUserLocal(info);
       return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        UserToLocal.removeUserLocal();
    }
}
