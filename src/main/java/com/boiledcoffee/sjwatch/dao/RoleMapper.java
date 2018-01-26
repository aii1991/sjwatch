package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role>{
    List<Role> findAll();
}