package com.boiledcoffee.sjwatch.interceptor;

import com.boiledcoffee.sjwatch.model.entity.UserLog;
import com.boiledcoffee.sjwatch.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by juha on 2018/1/11.
 *
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        String token = httpServletRequest.getHeader("token");
//        String uid = httpServletRequest.getHeader("uid");
//        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(uid)){
//            httpServletResponse.setStatus(401);
//            return false;
//        }
//        String cacheToken = stringRedisTemplate.opsForValue().get(uid);
//        if (StringUtils.isEmpty(cacheToken)){
//            httpServletResponse.setStatus(401);
//            return false;
//        }
//        if (!token.equals(cacheToken)){
//            httpServletResponse.setStatus(401);
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
