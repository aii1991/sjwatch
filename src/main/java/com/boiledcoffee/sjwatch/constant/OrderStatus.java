package com.boiledcoffee.sjwatch.constant;

/**
 * Created by ${juha} on 2018/8/20.
 */
public enum OrderStatus {
    PENDING_DELIVERY(1), //待发货
    DELIVERY(2), //已发货
    COMPLETE(3); //已完成

    private int status;
    OrderStatus(int status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
