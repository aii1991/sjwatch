package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User>{
    User selectByUserName(@Param("user_name") String userName);
}