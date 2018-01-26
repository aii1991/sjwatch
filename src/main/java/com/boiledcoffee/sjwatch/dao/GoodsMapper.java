package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.entity.Goods;
import com.boiledcoffee.sjwatch.model.query.GoodQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods>{
    int updateByPrimaryKeyWithBLOBs(Goods record);


    List<Goods> findAllGoods(@Param("offset") int offset,@Param("limit") int limit,
                             @Param("goodQuery") GoodQuery goodQuery,@Param("isAdmin") int isAdmin);


}