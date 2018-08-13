package com.boiledcoffee.sjwatch.service.goods.impl;

import com.boiledcoffee.sjwatch.dao.BrandMapper;
import com.boiledcoffee.sjwatch.dao.GoodsMapper;
import com.boiledcoffee.sjwatch.dao.GoodsTypeMapper;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Brand;
import com.boiledcoffee.sjwatch.model.entity.BrandType;
import com.boiledcoffee.sjwatch.model.entity.Goods;
import com.boiledcoffee.sjwatch.model.entity.GoodsType;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.query.GoodQuery;
import com.boiledcoffee.sjwatch.service.goods.IGoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public HandleResult<Goods> insertGoods(Goods goods) {
        HandleResult<Goods> handleResult = new HandleResult<>();
        try{
            long goodsId = goodsMapper.insertSelective(goods);
            if (goodsId > 0){
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
    public HandleResult<PageRspData> findAllGoods(int page, int pageSize, GoodQuery goodQuery,String uid) {
        HandleResult<PageRspData> handleResult = new HandleResult<>();
        int offSet = page * pageSize;
        try {
            Page page1 = PageHelper.startPage(page, pageSize, true);
            int isAdmin = Integer.valueOf(stringRedisTemplate.opsForValue().get("isAdmin/" + uid));
            List<Goods> goodsList = goodsMapper.findAllGoods(offSet, pageSize, goodQuery, isAdmin);
            long total = page1.getTotal();
            PageRspData<Goods> pageRspData = new PageRspData<>(total,goodsList);
            handleResult.setResult(pageRspData);

        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Map> deleteGoodsById(long id) {
        HandleResult<Map> handleResult = new HandleResult<>();
        try{
            int resultId = goodsMapper.deleteByPrimaryKey(id);
            if (resultId > 0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",id);
                handleResult.setResult(map);
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
    public HandleResult<Map> deleteGoodsTypeById(long id) {
        HandleResult<Map> handleResult = new HandleResult<>();
        try{
            int result = goodsTypeMapper.deleteByPrimaryKey(id);
            if (result > 0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",id);
                handleResult.setResult(map);
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
            long resultId = brandMapper.insertSelective(brand);
            if (resultId > 0){
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
    public HandleResult<Map> deleteBrandById(long id) {
        HandleResult<Map> handleResult = new HandleResult<>();
        try{
            int result = brandMapper.deleteByPrimaryKey(id);
            if (result > 0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",id);
                handleResult.setResult(map);
            }else {
                handleResult.setErrorMsg("delete brand error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<BrandType> insertBrandType(BrandType brandType) {
        return null;
    }

    @Override
    public HandleResult<BrandType> modifyBrandType(BrandType brandType) {
        return null;
    }

    @Override
    public HandleResult<List<BrandType>> findBrandTypeById(long brandId) {
        return null;
    }

    @Override
    public HandleResult<Map> deleteBrandTypeById(long brandTypeId) {
        return null;
    }

}
