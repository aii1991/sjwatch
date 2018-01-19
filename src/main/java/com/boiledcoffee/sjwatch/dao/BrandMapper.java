package com.boiledcoffee.sjwatch.dao;

import com.boiledcoffee.sjwatch.model.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand>{
    /**
     * goodsType=-1查询所有品牌,否则查询对应商品类型品牌
     * @param goodsType 商品类型
     * @return
     */
    List<Brand> findSelectiveAll(@Param("type_id") long goodsType);
}