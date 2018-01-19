package com.boiledcoffee.sjwatch.service.impl;

import com.boiledcoffee.sjwatch.dao.BrandMapper;
import com.boiledcoffee.sjwatch.dao.GoodsMapper;
import com.boiledcoffee.sjwatch.dao.GoodsTypeMapper;
import com.boiledcoffee.sjwatch.model.Brand;
import com.boiledcoffee.sjwatch.model.Goods;
import com.boiledcoffee.sjwatch.model.GoodsType;
import com.boiledcoffee.sjwatch.model.Role;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by juha on 2018/1/11.
 */
@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService{
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsTypeMapper goodsTypeMapper;
    @Autowired
    BrandMapper brandMapper;

    @Override
    public HandleResult<Goods> insertGoods(Goods goods) {
        HandleResult<Goods> handleResult = new HandleResult<>();
        try{
            long goodsId = goodsMapper.insertSelective(goods);
            if (goodsId > 0){
                goods.setId(goodsId);
                handleResult.setResult(goods);
            }else {
                handleResult.setErrorMsg("insert goods error");
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
            }else {
                handleResult.setErrorMsg("goods modify error");
            }
        }catch (Exception e){
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<List<Goods>> findAllGoods(int page, int pageSize, int sort, Goods goods) {
        //Todo 等写
        HandleResult<List<Goods>> handleResult = new HandleResult<>();
        try {

        }catch (Exception e){
            e.printStackTrace();

        }
        int offSet = page * pageSize;
        return null;
    }

    @Override
    public HandleResult<Long> deleteGoodsById(long id) {
        HandleResult<Long> handleResult = new HandleResult<>();
        try{
            int resultId = goodsMapper.deleteByPrimaryKey(id);
            if (resultId > 0){
                handleResult.setResult(id);
            }else {
                handleResult.setErrorMsg("goods delete error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<GoodsType> insertGoodsType(GoodsType goodsType) {
        HandleResult<GoodsType> handleResult = new HandleResult<>();
        try {
            long resultId = goodsTypeMapper.insertSelective(goodsType);
            if (resultId > 0){
                goodsType.setId(resultId);
                handleResult.setResult(goodsType);
            }else {
                handleResult.setErrorMsg("goodsType insert error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<List<GoodsType>> findAllGoodsType() {
        HandleResult<List<GoodsType>> handleResult = new HandleResult<>();
        try{
            List<GoodsType> goodsTypes = goodsTypeMapper.findAll();
            handleResult.setResult(goodsTypes);
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Long> deleteGoodsTypeById(long id) {
        HandleResult<Long> handleResult = new HandleResult<>();
        try{
            int result = goodsTypeMapper.deleteByPrimaryKey(id);
            if (result > 0){
                handleResult.setResult(id);
            }else {
                handleResult.setErrorMsg("goods type delete error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Brand> insertBrand(Brand brand) {
        HandleResult<Brand> handleResult = new HandleResult<>();
        try {
            long resultId = brandMapper.insert(brand);
            if (resultId > 0){
                brand.setId(resultId);
                handleResult.setResult(brand);
            }else {
                handleResult.setErrorMsg("insert brand error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<List<Brand>> findAllBrand(long type) {
        HandleResult<List<Brand>> handleResult = new HandleResult<>();
        try {
            List<Brand> brands = brandMapper.findSelectiveAll(type);
            handleResult.setResult(brands);
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Long> deleteBrandById(long id) {
        HandleResult<Long> handleResult = new HandleResult<>();
        try{
            int result = brandMapper.deleteByPrimaryKey(id);
            if (result > 0){
                handleResult.setResult(id);
            }else {
                handleResult.setErrorMsg("delete brand error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

}
