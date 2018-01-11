package com.boiledcoffee.sjwatch.service.impl;

import com.boiledcoffee.sjwatch.dao.RoleMapper;
import com.boiledcoffee.sjwatch.model.Role;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by juha on 2018/1/10.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService{
    @Autowired
    RoleMapper roleMapper;

    @Override
    public HandleResult<Role> insertRole(Role role) {
        HandleResult<Role> handleResult = new HandleResult<>();
        try{
            int resultId = roleMapper.insert(role);
            if (resultId > 0){
                handleResult.setResult(role);
            }else {
                handleResult.setErrorMsg("role insert error");
            }
        }catch (Exception e){
            handleResult.updateStatusToError(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Role> modifyRole(Role role) {
        HandleResult<Role> handleResult = new HandleResult<>();
        try {
            int resultId = roleMapper.updateByPrimaryKeySelective(role);
            if (resultId > 0){
                handleResult.setResult(role);
            }else {
                handleResult.setErrorMsg("role modify error");
            }
        }catch (Exception e){
            handleResult.updateStatusToError(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Long> deleteRoleById(long id) {
        HandleResult<Long> handleResult = new HandleResult<>();
        try {
            int resultId = roleMapper.deleteByPrimaryKey(id);
            if (resultId > 0){
                handleResult.setResult(id);
            }else {
                handleResult.setErrorMsg("role delete error");
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<List<Role>> findRoles() {
        HandleResult<List<Role>> handleResult = new HandleResult<>();
        try{
            List<Role> roleList = roleMapper.selectAll();
            handleResult.setResult(roleList);
        }catch (Exception e){
            handleResult.updateStatusToError(e.getMessage());
        }
        return handleResult;
    }
}
