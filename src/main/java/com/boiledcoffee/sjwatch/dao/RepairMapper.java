package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.entity.Repair;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairMapper extends BaseMapper<Repair>{
    List<Repair> findAllRepairs(@Param("offset") int offset, @Param("limit") int limit);
}