package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.model.Role;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * Created by juha on 2018/1/11.
 */
@RestController
public class RoleController extends BaseController{
    @Autowired
    IRoleService roleService;

    @RequestMapping(value = "/role",method = RequestMethod.POST)
    public HandleResult insertRole(@RequestBody Role role){
       return roleService.insertRole(role);
    }

    @RequestMapping(value = "/role",method = RequestMethod.PUT)
    public HandleResult modifyRole(@RequestBody Role role){
        return roleService.modifyRole(role);
    }

    @RequestMapping(value = "/role",method = RequestMethod.GET)
    public HandleResult findRoles(){
        return roleService.findRoles();
    }

    @RequestMapping(value = "/role/{id}",method = RequestMethod.DELETE)
    public HandleResult deleteRole(@PathVariable(value = "id",required = true)long id){
        return roleService.deleteRoleById(id);
    }

}
