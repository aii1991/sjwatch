package com.boiledcoffee.sjwatch.service.order.impl;

import com.boiledcoffee.sjwatch.constant.OrderStatus;
import com.boiledcoffee.sjwatch.dao.OrderMapper;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Order;
import com.boiledcoffee.sjwatch.service.order.IOrderService;
import com.boiledcoffee.sjwatch.service.sms.ISmsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2018/8/6.
 *
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService{
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ISmsService smsService;

    @Override
    public HandleResult<Order> insertOrder(Order order,String verifyCode) {
        final HandleResult<Order> handleResult = new HandleResult<>();
        final String receiverName = order.getReceiverName();
        final String receiverPhoneNumber = order.getReceiverNumber();

        if (order.getGoodsId() < 0){ //商品有问题
            handleResult.setErrorMsg("goods not found");
            return handleResult;
        }
        if (!smsService.validateCode(receiverPhoneNumber,verifyCode)){
            handleResult.setErrorMsg("验证码错误,请重新获取验证码后重试");
            return handleResult;
        }
        if (StringUtils.isEmpty(order.getAddress())
                || StringUtils.isEmpty(receiverName)
                || StringUtils.isEmpty(receiverPhoneNumber)){ //检测必要信息是否不为空
            handleResult.setErrorMsg("address receiver name or receiver phone number cant empty");
            return handleResult;
        }

        order.setNo(generateOrderNO());
        order.setStatus(OrderStatus.PENDING_DELIVERY.getStatus());
        try{
            long orderId = orderMapper.insertSelective(order);
            if (orderId > 0){
                handleResult.setResult(order);
            }else {
                handleResult.setErrorMsg("insert order error");
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Map> delOrderById(long uid,long orderId) {
        int isAdmin = Integer.valueOf(stringRedisTemplate.opsForValue().get("isAdmin/" + uid));
        HandleResult<Map> handleResult = new HandleResult<>();
        if (isAdmin != 1){
            handleResult.setErrorMsg("no authority");
            return handleResult;
        }
        try{
            int resultId = orderMapper.deleteByPrimaryKey(orderId);
            if (resultId > 0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",orderId);
                handleResult.setResult(map);
            }else {
                handleResult.setErrorMsg("order delete error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Order> modifyOrder(Order order,long uid,String verifyCode) {
        int isAdmin = Integer.valueOf(stringRedisTemplate.opsForValue().get("isAdmin/" + uid));
        if (isAdmin == 1){
            return modifyOrderOperate(order);
        }else {
            Order modifyOrder = new Order();
            String address = order.getAddress();
            String name = order.getReceiverName();
            String phoneNumber = order.getReceiverNumber();
            if (!StringUtils.isEmpty(address)){
                modifyOrder.setAddress(address);
            }
            if (!StringUtils.isEmpty(order.getReceiverName())){
                modifyOrder.setReceiverName(name);
            }
            if (!StringUtils.isEmpty(phoneNumber) && smsService.validateCode(phoneNumber, verifyCode)){
                modifyOrder.setReceiverNumber(phoneNumber);
            }
            return modifyOrderOperate(modifyOrder);
        }
    }

    private HandleResult<Order> modifyOrderOperate(Order order) {
        HandleResult<Order> handleResult = new HandleResult<>();
        try{
            int resultId = orderMapper.updateByPrimaryKeySelective(order);
            if (resultId > 0){
                handleResult.setResult(order);
            }else {
                handleResult.setErrorMsg("order modify error");
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<PageRspData<Order>> listOrder(int page, int pageSize, int sort,long uid) {
        HandleResult<PageRspData<Order>> handleResult = new HandleResult<>();
        int offSet = page * pageSize;
        try {
            Page page1 = PageHelper.startPage(page, pageSize, true);
            int isAdmin = Integer.valueOf(stringRedisTemplate.opsForValue().get("isAdmin/" + uid));
            if (isAdmin != 1){
                handleResult.setErrorMsg("no authority to get data");
                return handleResult;
            }
            List<Order> orderList = orderMapper.findAllOrders(offSet, pageSize,sort);
            long total = page1.getTotal();
            PageRspData<Order> pageRspData = new PageRspData<>(total,orderList);
            handleResult.setResult(pageRspData);

        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }


    /**
     * 生成订单号
     * @return
     */
    private long generateOrderNO(){
        return 1 + System.currentTimeMillis();
    }
}
