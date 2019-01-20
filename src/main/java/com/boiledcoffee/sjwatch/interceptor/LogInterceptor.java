package com.boiledcoffee.sjwatch.interceptor;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.UserLog;
import com.boiledcoffee.sjwatch.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ${juha} on 2018/10/19.
 * 日志拦截器
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String strUid = request.getHeader("uid");
        long uid = 2;
        if (!StringUtils.isEmpty(strUid) && !"null".equals(strUid) && !"undefined".equals(strUid)){
            uid = Long.parseLong(strUid);
        }
        UserLog userLog = new UserLog();
        userLog.setUid(uid);
        userLog.setRequestUrl(request.getRequestURL().toString());
        userLog.setLoginIp(request.getRemoteAddr() + ":" + request.getRemotePort());
        HandleResult handleResult = userService.addUserLog(userLog);
        return !handleResult.isError();
    }
}
