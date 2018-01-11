package com.boiledcoffee.sjwatch.service;

import com.boiledcoffee.sjwatch.model.Role;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;

import java.util.List;

/**
 * Created by juha on 2018/1/10.
 */
public interface IRoleService {
    /**
     * 插入角色
     * @param role
     * @return 是否插入成功
     */
    HandleResult<Role> insertRole(Role role);
    /**
     * 修改角色
     * @param role
     * @return
     */
    HandleResult<Role> modifyRole(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    HandleResult<Long> deleteRoleById(long id);

    /**
     * 查询所有角色
     * @return
     */
    HandleResult<List<Role>> findRoles();
}
