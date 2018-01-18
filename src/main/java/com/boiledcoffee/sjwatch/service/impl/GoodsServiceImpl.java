package com.boiledcoffee.sjwatch.service.impl;

import com.boiledcoffee.sjwatch.dao.GoodsMapper;
import com.boiledcoffee.sjwatch.model.Goods;
import com.boiledcoffee.sjwatch.model.Role;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by juha on 2018/1/11.
 */
public class GoodsServiceImpl implements IGoodsService{
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public HandleResult<Goods> insertGoods(Goods goods) {
        HandleResult<Goods> handleResult = new HandleResult<>();
        try{
            long goodsId = goodsMapper.insertSelective(goods);
            if (goodsId > 0){
                goods.setId(goodsId);
                handleResult.setResult(goods);
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Goods> modifyGoods(Goods goods) {
        HandleResult<Goods> handleResult = new HandleResult<>();
        try{
            int resultId = goodsMapper.updateByPrimaryKeySelective(goods);
            if (resultId > 0){
                handleResult.setResult(goods);
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<List<Goods>> findAllGoods(int offset, int limit) {
        return null;
    }

    @Override
    public HandleResult<Long> deleteGoodsById(long id) {
        HandleResult<Long> handleResult = new HandleResult<>();
        try{
            int resultId = goodsMapper.deleteByPrimaryKey(id);
            if (resultId > 0){
                handleResult.setResult(id);
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
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
