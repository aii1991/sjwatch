package com.boiledcoffee.sjwatch.controller.order;

import com.boiledcoffee.sjwatch.controller.BaseController;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Order;
import com.boiledcoffee.sjwatch.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by jason on 2018/8/6.
 *
 */
@RestController
public class OrderController extends BaseController{
    @Autowired
    IOrderService orderService;

    @RequestMapping(value = "/order/{validateCode}",method = RequestMethod.POST)
    public HandleResult<Order> addOrder(@RequestBody Order order,@PathVariable(value = "validateCode") String validateCode){
        return orderService.insertOrder(order,validateCode);
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

    @RequestMapping(value = "/order",method = RequestMethod.GET)
    public HandleResult<PageRspData<Order>> listOrder(int page,int pageSize,int sort,long uid){
        return orderService.listOrder(page,pageSize,sort,uid);
    }

}
