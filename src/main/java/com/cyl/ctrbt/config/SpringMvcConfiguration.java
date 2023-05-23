package com.cyl.ctrbt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2023/5/6
 * @since 1.0
 */
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration=  registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**");
        registration.excludePathPatterns("/error");
    }

}
