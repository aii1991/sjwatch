package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.Goods;

public interface GoodsMapper extends BaseMapper<Goods>{
    int updateByPrimaryKeyWithBLOBs(Goods record);
}