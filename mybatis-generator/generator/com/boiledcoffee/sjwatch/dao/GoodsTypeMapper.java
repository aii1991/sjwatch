package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.GoodsType;

public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);
}