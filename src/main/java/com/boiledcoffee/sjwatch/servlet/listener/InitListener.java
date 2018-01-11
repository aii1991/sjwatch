package com.boiledcoffee.sjwatch.servlet.listener;

import com.boiledcoffee.sjwatch.model.Role;
import com.boiledcoffee.sjwatch.service.IRoleService;
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
        Role role1 = new Role();
        role1.setName("超级管理员");

        Role role2 = new Role();
        role2.setName("卖家");

        Role role3 = new Role();
        role3.setName("买家");

        roleService.insertRole(role1);
        roleService.insertRole(role2);
        roleService.insertRole(role3);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
