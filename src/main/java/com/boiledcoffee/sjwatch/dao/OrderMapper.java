package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.entity.Order;
import com.boiledcoffee.sjwatch.model.entity.OrderWrapper;
import com.boiledcoffee.sjwatch.model.query.OrderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order>{
    List<OrderWrapper> findAllOrders( @Param("offset") int offset,@Param("limit") int limit,@Param("orderQuery")OrderQuery orderQuery);
}