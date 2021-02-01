package com.boiledcoffee.sjwatch.servlet.listener;

import com.boiledcoffee.sjwatch.model.entity.Role;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.User;
import com.boiledcoffee.sjwatch.service.user.IRoleService;
import com.boiledcoffee.sjwatch.service.user.IUserService;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * Created by juha on 2018/1/11.
 */
@WebListener
public class InitListener implements ServletContextListener{
    @Autowired
    IRoleService roleService;
    @Autowired
    IUserService userService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        addUser(1L,"超级管理员","admin","超级管理员",MD5Encoder.encode("ww58879576".getBytes()));
        addUser(100L,"未知用户","unknow","未知用户", MD5Encoder.encode("unknow1122334455".getBytes()));
        Logger.getInstance(this.getClass()).debug("=========init success=======");
    }

    private void addUser(long roleId,String roleName,String userName,String nickName,String pwd) {
        HandleResult<Role> handleResult = roleService.findRoleById(roleId);
        if (handleResult.isError() || handleResult.getResult() == null){
            Role role1 = new Role();
            role1.setId(roleId);
            role1.setName(roleName);
            roleService.insertRole(role1);

            User admin = new User();
            admin.setUserName(userName);
            admin.setNickName(nickName);
            admin.setPassword(pwd);
            admin.setRoleId(role1.getId());
            userService.register(admin);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
