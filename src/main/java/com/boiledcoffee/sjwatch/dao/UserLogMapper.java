package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.entity.UserLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLogMapper extends BaseMapper<UserLog>{
    List<UserLog> findAllUserLog(@Param("offset") int offset,@Param("limit") int limit);
}