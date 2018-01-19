package com.boiledcoffee.sjwatch.service;

import com.boiledcoffee.sjwatch.model.Brand;
import com.boiledcoffee.sjwatch.model.Goods;
import com.boiledcoffee.sjwatch.model.GoodsType;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by juha on 2018/1/10.
 */
public interface IGoodsService {
    /**
     * 插入商品
     * @param goods
     * @return
     */
    HandleResult<Goods> insertGoods(Goods goods);

    /**
     * 修改商品
     * @param goods
     * @return
     */
    HandleResult<Goods> modifyGoods(Goods goods);

    /**
     * 查找商品
     * @param page
     * @param pageSize
     * @param sort
     * @param goods
     * @return
     */
    HandleResult<List<Goods>> findAllGoods(int page,int pageSize,int sort,Goods goods);

    /**
     * 通过商品id删除商品
     * @param id
     * @return
     */
    HandleResult<Long> deleteGoodsById(long id);

    /**
     * 插入商品类型
     * @param goodsType
     * @return
     */
    HandleResult<GoodsType> insertGoodsType(GoodsType goodsType);

    /**
     * 列出所有商品类型
     * @return
     */
    HandleResult<List<GoodsType>> findAllGoodsType();

    /**
     * 删除商品类型
     * @param id
     * @return
     */
    HandleResult<Long> deleteGoodsTypeById(long id);

    /**
     * 插入品牌
     * @return
     */
    HandleResult<Brand> insertBrand(Brand brand);

    /**
     * 通过商品类型查询品牌
     * @return
     */
    HandleResult<List<Brand>> findAllBrand(long type);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    HandleResult<Long> deleteBrandById(long id);

}
