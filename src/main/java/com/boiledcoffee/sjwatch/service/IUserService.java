package com.boiledcoffee.sjwatch.service;

import com.boiledcoffee.sjwatch.model.User;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;

/**
 * Created by juha on 2018/1/10.
 */
public interface IUserService {
    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    HandleResult<User> login(String userName,String password);

    /**
     * 注册
     * @param user
     * @return
     */
    HandleResult<User> register(User user);

    /**
     * 设置用户角色
     * @param user
     * @return
     */
    HandleResult<User> setUserRole(User user);

}
