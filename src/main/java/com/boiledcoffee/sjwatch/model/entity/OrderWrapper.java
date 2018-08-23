package com.boiledcoffee.sjwatch.model.entity;

/**
 * Created by ${juha} on 2018/8/23.
 *
 */
public class OrderWrapper extends Order{
    private GoodsWithBLOBs goods;

    public GoodsWithBLOBs getGoods() {
        return goods;
    }

    public void setGoods(GoodsWithBLOBs goods) {
        this.goods = goods;
    }
}
