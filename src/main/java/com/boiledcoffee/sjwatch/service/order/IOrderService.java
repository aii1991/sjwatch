package com.boiledcoffee.sjwatch.service.order;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Order;
import com.boiledcoffee.sjwatch.model.entity.OrderWrapper;
import com.boiledcoffee.sjwatch.model.query.OrderQuery;

import java.util.Map;

/**
 * Created by ${juha} on 2018/8/6.
 *
 */
public interface IOrderService {
    HandleResult<Order> insertOrder(Order order,String verifyCode);

    HandleResult<Map> delOrderById(long uid,long orderId);

    HandleResult<Order> modifyOrder(Order order,long uid,String verifyCode);

    HandleResult<PageRspData<OrderWrapper>> listOrder(int page,int pageSize,long uid,OrderQuery orderQuery);
}
