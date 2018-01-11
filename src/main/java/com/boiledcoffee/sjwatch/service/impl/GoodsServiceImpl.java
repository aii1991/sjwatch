package com.boiledcoffee.sjwatch.service.impl;

import com.boiledcoffee.sjwatch.model.Goods;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.IGoodsService;

import java.util.List;

/**
 * Created by juha on 2018/1/11.
 */
public class GoodsServiceImpl implements IGoodsService{
    @Override
    public HandleResult<Integer> insertGoods(Goods goods) {
        return null;
    }

    @Override
    public HandleResult<Goods> modifyGoods(Goods goods) {
        return null;
    }

    @Override
    public HandleResult<List<Goods>> findAllGoods(int offset, int limit) {
        return null;
    }

    @Override
    public HandleResult<Long> deleteGoodsById(long id) {
        return null;
    }

    @Override
    public HandleResult<List<Goods>> findGoodsByName(String name, int offset, int limit) {
        return null;
    }

    @Override
    public HandleResult<List<Goods>> findGoodsByBrandId(long id, int offset, int limit) {
        return null;
    }

    @Override
    public HandleResult<List<Goods>> findGoodsByBrandName(String name, int offset, int limit) {
        return null;
    }
}
