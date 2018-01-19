package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.GoodsType;

import java.util.List;

public interface GoodsTypeMapper extends BaseMapper<GoodsType>{
    /**
     * 查询所有商品类型
     * @return
     */
    List<GoodsType> findAll();
}