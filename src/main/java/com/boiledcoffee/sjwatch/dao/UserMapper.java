package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.User;

public interface UserMapper extends BaseMapper<User>{
    User selectByUserName(String userName);
}