package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.dao.UserMapper;
import com.boiledcoffee.sjwatch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songlive on 2018/1/10.
 */
@RestController
public class GoodsController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/test")
    public User test(){
        User user = new User();
        user.setId(1L);
        user.setUserName("你好");
        return user;
    }
}
