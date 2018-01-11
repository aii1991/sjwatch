package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.model.User;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Created by juha on 2018/1/10.
 */
@RestController
public class UserController extends BaseController{
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/user/{user_name}",method = RequestMethod.POST)
    public HandleResult login(@PathVariable(value = "user_name",required = true) String userName,@RequestParam(value = "password",required = true) String password){
        return userService.login(userName,password);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public HandleResult register(@RequestBody User user){
        return userService.register(user);
    }
}
