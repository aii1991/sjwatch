package com.boiledcoffee.sjwatch.service.user;

import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.User;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.UserLog;

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

    HandleResult loginOut(long uid);

    HandleResult addUserLog(UserLog userLog);

    HandleResult<PageRspData<UserLog>> listUserLog(int page, int pageSize,long uid);
}
