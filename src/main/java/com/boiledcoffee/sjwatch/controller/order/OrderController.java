package com.boiledcoffee.sjwatch.controller.order;

import com.boiledcoffee.sjwatch.controller.BaseController;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Order;
import com.boiledcoffee.sjwatch.model.entity.OrderWrapper;
import com.boiledcoffee.sjwatch.model.query.GoodQuery;
import com.boiledcoffee.sjwatch.model.query.OrderQuery;
import com.boiledcoffee.sjwatch.service.order.IOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jason on 2018/8/6.
 *
 */
@RestController
public class OrderController extends BaseController{
    @Autowired
    IOrderService orderService;
    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/order/{validateCode}",method = RequestMethod.POST)
    public HandleResult<Order> addOrder(HttpServletRequest request,@RequestBody Order order,@PathVariable(value = "validateCode") String validateCode){
        long uid = Long.parseLong(request.getHeader("uid"));
        return orderService.insertOrder(uid,order,validateCode);
    }

    @RequestMapping(value = "/order/{validateCode}",method = RequestMethod.PUT)
    public HandleResult<Order> modifyOrder(HttpServletRequest request,@RequestBody Order order, @PathVariable(value = "validateCode") String validateCode){
        long uid = Long.parseLong(request.getHeader("uid"));
        return orderService.modifyOrder(order,uid,validateCode);
    }

    @RequestMapping(value = "/order/{id}",method = RequestMethod.DELETE)
    public HandleResult<Map> deleteOrder(HttpServletRequest request,@PathVariable(value = "id") long id){
        long uid = Long.parseLong(request.getHeader("uid"));
        return orderService.delOrderById(uid,id);
    }

    @RequestMapping(value = "/order/{page}/{page_size}",method = RequestMethod.GET)
    public HandleResult<PageRspData<OrderWrapper>> listOrder(HttpServletRequest request,@PathVariable(value = "page")int page,@PathVariable(value = "page_size")int pageSize,@RequestParam(name = "param",required = false) String queryParam){
        long uid = Long.parseLong(request.getHeader("uid"));
        OrderQuery orderQuery;
        try {
            if (!StringUtils.isEmpty(queryParam)){
                orderQuery = objectMapper.readValue(queryParam,OrderQuery.class);
            }else {
                orderQuery = new OrderQuery();
            }
        }catch (IOException e){
            orderQuery = new OrderQuery();
        }
        return orderService.listOrder(page,pageSize,uid,orderQuery);
    }

}
