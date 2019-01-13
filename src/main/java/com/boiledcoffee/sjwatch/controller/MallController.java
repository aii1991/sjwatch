package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Brand;
import com.boiledcoffee.sjwatch.model.entity.GoodsWithBLOBs;
import com.boiledcoffee.sjwatch.model.query.GoodQuery;
import com.boiledcoffee.sjwatch.service.goods.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${juha} on 2018/12/4.
 */
@Controller
public class MallController {
    @Autowired
    IGoodsService goodsService;

    @RequestMapping(value = "/mall/index")
    public String index(HttpServletRequest request,Model model){
        String uid = request.getHeader("uid");

        //左边菜单栏品牌列表
        HandleResult<List<Brand>> brandHandleResult = goodsService.findAllBrand(1);
        List<Brand> brands;
        if (brandHandleResult.isError()){
            return "/error";
        }
        brands = brandHandleResult.getResult();
        if (brands == null) {
            brands = new ArrayList<>();
        }else if (brands.size() > 9){
            brands = brandHandleResult.getResult().subList(0,9);
        }
        model.addAttribute("brandList",brands);

        //热门商品
        GoodQuery hotGoodQuery = new GoodQuery();
        hotGoodQuery.setIsHot(1);
        HandleResult<PageRspData<GoodsWithBLOBs>> hotGoodsHandleResult = goodsService.findAllGoods(0,3,hotGoodQuery,uid);
        if (brandHandleResult.isError()){
            return "error";
        }
        model.addAttribute("hotGoods",hotGoodsHandleResult.getResult().getList());

        //首页商品
        List<GoodsWithBLOBs> goods =  new ArrayList<>();
        GoodQuery goodQuery = new GoodQuery();
        goodQuery.setSortTime(2);
        for(Brand brand : brands){
            goodQuery.settBId(brand.getId());
            HandleResult<PageRspData<GoodsWithBLOBs>> goodsHandleResult = goodsService.findAllGoods(0,6,goodQuery,uid);
            if (!goodsHandleResult.isError()){
                goods.addAll(goodsHandleResult.getResult().getList());
            }
        }
        model.addAttribute("goods",goods);

        return "/home/index";
    }
}
