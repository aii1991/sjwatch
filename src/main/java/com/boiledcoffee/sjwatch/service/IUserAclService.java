package com.boiledcoffee.sjwatch.service;

import com.boiledcoffee.sjwatch.model.UserAcl;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;

import java.util.List;

/**
 * Created by juha on 2018/1/10.
 */
public interface IUserAclService {
    HandleResult<UserAcl> insertAcl(UserAcl userAcl);

    HandleResult<List<UserAcl>> findAclsByRoleId(long roleId);
}
