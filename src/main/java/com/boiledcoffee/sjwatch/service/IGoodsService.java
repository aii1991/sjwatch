package com.boiledcoffee.sjwatch.service;

import com.boiledcoffee.sjwatch.model.Goods;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;

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
     * 查找所有商品
     * @param offset
     * @param limit
     * @return
     */
    HandleResult<List<Goods>> findAllGoods(int offset,int limit);

    /**
     * 通过商品id删除商品
     * @param id
     * @return
     */
    HandleResult<Long> deleteGoodsById(long id);

    /**
     * 通过商品名查找商品
     * @param name
     * @param offset
     * @param limit
     * @return
     */
    HandleResult<List<Goods>> findGoodsByName(String name,int offset,int limit);

    /**
     * 通过品牌id查找商品
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    HandleResult<List<Goods>> findGoodsByBrandId(long id,int offset,int limit);

    /**
     * 通过品牌名查找商品
     * @param name
     * @param offset
     * @param limit
     * @return
     */
    HandleResult<List<Goods>> findGoodsByBrandName(String name,int offset,int limit);
}
