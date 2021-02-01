package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.entity.GoodsWithBLOBs;
import com.boiledcoffee.sjwatch.model.query.GoodsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<GoodsWithBLOBs>{
    int updateByPrimaryKeyWithBLOBs(GoodsWithBLOBs record);


    List<GoodsWithBLOBs> findAllGoods(@Param("offset") int offset, @Param("limit") int limit,
                                      @Param("goodsQuery") GoodsQuery goodsQuery, @Param("isAdmin") int isAdmin);

    GoodsWithBLOBs getGoodsById(@Param("goodsId") long goodsId);

}