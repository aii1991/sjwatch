package com.boiledcoffee.sjwatch.service.user.impl;

import com.boiledcoffee.sjwatch.dao.UserLogMapper;
import com.boiledcoffee.sjwatch.dao.UserMapper;
import com.boiledcoffee.sjwatch.model.OssProperties;
import com.boiledcoffee.sjwatch.model.QiniuProperties;
import com.boiledcoffee.sjwatch.model.SJProperties;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.User;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.UserLog;
import com.boiledcoffee.sjwatch.service.user.IUserService;
import com.boiledcoffee.sjwatch.util.UploadUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Autowired
    UserLogMapper userLogMapper;
    @Autowired
    QiniuProperties qiniuProperties;
    @Autowired
    SJProperties sjProperties;
    @Autowired
    OssProperties ossProperties;


    @Override
    public HandleResult<User> login(String userName,String password) {
        HandleResult<User> handleResult = new HandleResult<>();
        try {
            User user = userMapper.selectByUserName(userName);
            if (user != null){
                String findPassword = user.getPassword();
                if (findPassword.equals(password)){
                    //登录成功
                    String token = createAndSaveAccessToken(user);
                    stringRedisTemplate.opsForValue().set(String.valueOf(user.getId()), token, sjProperties.getTokenValidTime(), TimeUnit.HOURS);
                    stringRedisTemplate.opsForValue().set("isAdmin/" + user.getId(),user.getRoleId() == 1 ? "1" : "0");
                    user.setPassword(null);
//                    user.setUploadToken(UploadUtils.generatorQiNiuToken(qiniuProperties.getAk(), qiniuProperties.getSk(), qiniuProperties.getBucket()));
                    user.setUploadToken(UploadUtils.generateOssPolicyAndToken(ossProperties.getAk(),ossProperties.getSk(),ossProperties.getExpiration()));
                    handleResult.setResult(user);
                }else {
                    //账号密码错误
                    handleResult.setErrorMsg("username or password error");
                }
            }else {
                handleResult.setErrorMsg("not found user,please go to register!");
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    public HandleResult loginOut(long uid){
        HandleResult<User> handleResult = new HandleResult<>();
        handleResult.updateStatusToSuccess();
        return  handleResult;
    }

    @Override
    public HandleResult addUserLog(UserLog userLog) {
        HandleResult<User> handleResult = new HandleResult<>();
        try{
            userLogMapper.insertSelective(userLog);
            handleResult.updateStatusToSuccess();
        }catch (Exception e){
            e.printStackTrace();
            handleResult.updateStatusToError();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<PageRspData<UserLog>> listUserLog(int page, int pageSize, long uid) {
        final int isAdmin = Integer.valueOf(stringRedisTemplate.opsForValue().get("isAdmin/" + uid));
        final HandleResult<PageRspData<UserLog>> handleResult = new HandleResult<>();
        if (isAdmin != 1){
            handleResult.setErrorMsg("no authority");
            return handleResult;
        }
        int offSet = (page - 1) * pageSize;
        if (offSet < 0){
            offSet = 0;
        }
        Page page1 = PageHelper.startPage(page, pageSize, true);
        List<UserLog> orderList = userLogMapper.findAllUserLog(offSet,pageSize);
        long total = page1.getTotal();
        PageRspData<UserLog> pageRspData = new PageRspData<>(total,orderList);
        handleResult.setResult(pageRspData);
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
                userMapper.insertSelective(user);
                user.setPassword(null);
                handleResult.setResult(user);
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
