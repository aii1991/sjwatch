package com.boiledcoffee.sjwatch.controller.user;

import com.boiledcoffee.sjwatch.controller.BaseController;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.User;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.UserLog;
import com.boiledcoffee.sjwatch.service.user.IUserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * Created by juha on 2018/1/10.
 */
@RestController
public class UserController extends BaseController {
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

    @RequestMapping(value = "/user/logout")
    public HandleResult logout(HttpServletRequest request) throws JSONException {
        String uid = request.getHeader("uid");
        return userService.loginOut(Long.parseLong(uid));
    }

    @RequestMapping(value = "/user/log/{page}/{page_size}",method = RequestMethod.GET)
    public HandleResult<PageRspData<UserLog>> getUserLog(HttpServletRequest request,@PathVariable("page")int page,@PathVariable("page_size") int pageSize){
        String uid = request.getHeader("uid");
        return userService.listUserLog(page,pageSize,Long.parseLong(uid));
    }
}
