package com.boiledcoffee.sjwatch.service.impl;

import com.boiledcoffee.sjwatch.model.UserAcl;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.IUserAclService;

import java.util.List;

/**
 * Created by juha on 2018/1/11.
 */
public class UserAclServiceImpl implements IUserAclService{
    @Override
    public HandleResult<UserAcl> insertAcl(UserAcl userAcl) {
        return null;
    }

    @Override
    public HandleResult<List<UserAcl>> findAclsByRoleId(long roleId) {
        return null;
    }
}
