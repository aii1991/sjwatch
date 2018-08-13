package com.boiledcoffee.sjwatch.servlet.listener;

import com.boiledcoffee.sjwatch.model.entity.Role;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.User;
import com.boiledcoffee.sjwatch.service.user.IRoleService;
import com.boiledcoffee.sjwatch.service.user.IUserService;
import org.apache.log4j.Logger;
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
        HandleResult<Role> handleResult = roleService.findRoleById(1);
        if (handleResult.isError() || handleResult.getResult() == null){
            Role role1 = new Role();
            role1.setId(1L);
            role1.setName("超级管理员");
            roleService.insertRole(role1);

            User admin = new User();
            admin.setUserName("admin");
            admin.setNickName("超级管理员");
            admin.setPassword("123456");
            admin.setRoleId(role1.getId());
            userService.register(admin);

        }
        Logger.getInstance(this.getClass()).debug("=========init success=======");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
