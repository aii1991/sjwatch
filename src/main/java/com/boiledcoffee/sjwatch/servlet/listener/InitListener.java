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
        role1.setId(1L);
        role1.setName("超级管理员");

        roleService.insertRole(role1);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
