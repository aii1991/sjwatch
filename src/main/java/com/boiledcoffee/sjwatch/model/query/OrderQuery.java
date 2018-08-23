package com.boiledcoffee.sjwatch.model.query;

import java.io.Serializable;

/**
 * Created by juha on 2018/8/23.
 *
 */
public class OrderQuery implements Serializable{
    private int sortTime = 2;
    private String receiverName;
    private String receiverNumber;
    private String deliveryNumber;
    private String address;
    private int status;
    private String no; //订单号
    private long goodsId;


    public int getSortTime() {
        return sortTime;
    }

    public void setSortTime(int sortTime) {
        this.sortTime = sortTime;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
