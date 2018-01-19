package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.model.Brand;
import com.boiledcoffee.sjwatch.model.Goods;
import com.boiledcoffee.sjwatch.model.GoodsType;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by songlive on 2018/1/10.
 */
@RestController
public class GoodsController extends BaseController{
    @Autowired
    IGoodsService goodsService;

    @RequestMapping(value = "/goods/type",method = RequestMethod.POST)
    public HandleResult<GoodsType> addGoodsType(@RequestBody GoodsType goodsType){
        return goodsService.insertGoodsType(goodsType);
    }

    @RequestMapping(value = "/goods/type",method = RequestMethod.GET)
    public HandleResult<List<GoodsType>> listGoodsType(){
        return goodsService.findAllGoodsType();
    }

    @RequestMapping(value = "/goods/type/{id}",method = RequestMethod.DELETE)
    public HandleResult<Long> deleteGoodsType(@PathVariable(value = "id")long id){
        return goodsService.deleteGoodsTypeById(id);
    }

    @RequestMapping(value = "/goods/brand",method = RequestMethod.POST)
    public HandleResult<Brand> addBrand(@RequestBody Brand brand){
        return goodsService.insertBrand(brand);
    }

    @RequestMapping(value = "/goods/brand/{goods_type}",method = RequestMethod.GET)
    public HandleResult<List<Brand>> listBrand(@PathVariable(value = "goods_type",required = false) long goodsType){
        return goodsService.findAllBrand(goodsType);
    }

    @RequestMapping(value = "/goods/brand/{id}",method = RequestMethod.DELETE)
    public HandleResult<Long> deleteBrand(@PathVariable(value = "id") long id){
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

    @RequestMapping(value = "/goods/{page}/{page_size}/{sort}")
    public HandleResult<List<Goods>> listGoods(@PathVariable(value = "page")int page,@PathVariable(value = "page_size")int pageSize,
                                               @PathVariable("sort") int sort,@RequestBody Goods goods){
        return goodsService.findAllGoods(page,pageSize,sort,goods);
    }

    @RequestMapping(value = "/goods",method = RequestMethod.DELETE)
    public HandleResult<Long> deleteGoods(long id){
        return goodsService.deleteGoodsById(id);
    }





}
