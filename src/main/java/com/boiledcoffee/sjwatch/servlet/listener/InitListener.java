package com.boiledcoffee.sjwatch.servlet.listener;

import com.boiledcoffee.sjwatch.model.entity.Role;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.user.IRoleService;
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
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HandleResult<Role> handleResult = roleService.findRoleById(1);
        if (handleResult.isError() || handleResult.getResult() == null){
            Role role1 = new Role();
            role1.setId(1L);
            role1.setName("超级管理员");
            roleService.insertRole(role1);
        }
        Logger.getInstance(this.getClass()).debug("=========init success=======");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
