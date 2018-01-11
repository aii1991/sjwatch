package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.UserAcl;

public interface UserAclMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAcl record);

    int insertSelective(UserAcl record);

    UserAcl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAcl record);

    int updateByPrimaryKey(UserAcl record);
}