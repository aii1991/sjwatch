package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order>{
    List<Order> findAllOrders( @Param("offset") int offset,@Param("limit") int limit,@Param("sort")int sortByTime);
}