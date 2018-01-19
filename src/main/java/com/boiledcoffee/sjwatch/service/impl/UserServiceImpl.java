package com.boiledcoffee.sjwatch.service.impl;

import com.boiledcoffee.sjwatch.dao.UserMapper;
import com.boiledcoffee.sjwatch.model.User;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by juha on 2018/1/10.
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    UserMapper userMapper;

    @Override
    public HandleResult<User> login(String userName,String password) {
        HandleResult<User> handleResult = new HandleResult<>();
        try {
            User findUser = userMapper.selectByUserName(userName);
            if (findUser != null){
                String findPassword = findUser.getPassword();
                if (findPassword.equals(userName)){
                    //登录成功
                    stringRedisTemplate.expire(createAndSaveAccessToken(findUser),2, TimeUnit.HOURS);
                    findUser.setPassword(null);
                    handleResult.setResult(findUser);
                }else {
                    //账号密码错误
                    handleResult.setErrorMsg("username or password error");
                }
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<User> register(User user) {
        HandleResult<User> handleResult = new HandleResult<>();
        try {
            User findUser = userMapper.selectByUserName(user.getUserName());
            if (findUser != null){
                handleResult.setErrorMsg("username exist");
            }else {
                user.setCreateTime(System.currentTimeMillis());
                user.setModifyTime(System.currentTimeMillis());
                user.setPassword(null);
                userMapper.insert(user);
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<User> setUserRole(User user) {
        HandleResult<User> handleResult = new HandleResult<>();
        try {
            userMapper.updateByPrimaryKey(user);
            handleResult.setResult(user);
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    /**
     * 生成accessToken
     * @param user
     * @return
     */
    private String createAndSaveAccessToken(User user){
        String token = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println("token="+token);
        user.setToken(token);
        userMapper.updateByPrimaryKeySelective(user);
        return token;
    }
}
