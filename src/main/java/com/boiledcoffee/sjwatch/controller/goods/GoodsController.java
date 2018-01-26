package com.boiledcoffee.sjwatch.controller.goods;

import com.boiledcoffee.sjwatch.controller.BaseController;
import com.boiledcoffee.sjwatch.model.entity.Brand;
import com.boiledcoffee.sjwatch.model.entity.Goods;
import com.boiledcoffee.sjwatch.model.entity.GoodsType;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.query.GoodQuery;
import com.boiledcoffee.sjwatch.service.goods.IGoodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by juha on 2018/1/10.
 *
 */
@RestController
public class GoodsController extends BaseController {
    @Autowired
    IGoodsService goodsService;
    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/goods/type",method = RequestMethod.POST)
    public HandleResult<GoodsType> addGoodsType(@RequestBody GoodsType goodsType){
        return goodsService.insertGoodsType(goodsType);
    }

    @RequestMapping(value = "/goods/type",method = RequestMethod.GET)
    public HandleResult<List<GoodsType>> listGoodsType(){
        return goodsService.findAllGoodsType();
    }

    @RequestMapping(value = "/goods/type/{id}",method = RequestMethod.DELETE)
    public HandleResult<Map> deleteGoodsType(@PathVariable(value = "id")long id){
        return goodsService.deleteGoodsTypeById(id);
    }

    @RequestMapping(value = "/goods/brand",method = RequestMethod.POST)
    public HandleResult<Brand> addBrand(@RequestBody Brand brand){
        return goodsService.insertBrand(brand);
    }

    @RequestMapping(value = "/goods/brand/{goods_type}",method = RequestMethod.GET)
    public HandleResult<List<Brand>> listBrandByGoodsTypeId(@PathVariable(value = "goods_type") long goodsType){
        return goodsService.findAllBrand(goodsType);
    }

    @RequestMapping(value = "/goods/brand", method = RequestMethod.GET)
    public HandleResult<List<Brand>> listBrand(){
        return goodsService.findAllBrand(-1);
    }

    @RequestMapping(value = "/goods/brand/{id}",method = RequestMethod.DELETE)
    public HandleResult<Map> deleteBrand(@PathVariable(value = "id") long id){
        return goodsService.deleteBrandById(id);
    }

    @RequestMapping(value = "/goods",method = RequestMethod.POST)
    public HandleResult<Goods> addGoods(@RequestBody Goods goods){
        return goodsService.insertGoods(goods);
    }

    @RequestMapping(value = "/goods",method = RequestMethod.PUT)
    public HandleResult<Goods> modifyGoods(@RequestBody Goods goods){
        return goodsService.modifyGoods(goods);
    }

    @RequestMapping(value = "/goods/{page}/{page_size}",method = RequestMethod.GET)
    public HandleResult<List<Goods>> listGoods(HttpServletRequest request,@PathVariable(value = "page")int page,@PathVariable(value = "page_size")int pageSize,@RequestParam(name = "param",required = false) String queryParam){
        GoodQuery goodQuery;
        try {
            if (!StringUtils.isEmpty(queryParam)){
                goodQuery = objectMapper.readValue(queryParam,GoodQuery.class);
            }else {
                goodQuery = new GoodQuery();
            }
        }catch (IOException e){
            goodQuery = new GoodQuery();
        }
        String uid = request.getHeader("uid");
        return goodsService.findAllGoods(page,pageSize,goodQuery,uid);
    }

    @RequestMapping(value = "/goods",method = RequestMethod.DELETE)
    public HandleResult<Map> deleteGoods(long id){
        return goodsService.deleteGoodsById(id);
    }





}
