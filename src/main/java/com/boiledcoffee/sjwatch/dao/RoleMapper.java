package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role>{
    List<Role> selectAll();
}