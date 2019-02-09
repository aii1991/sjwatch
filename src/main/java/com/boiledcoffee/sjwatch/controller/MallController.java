package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Banner;
import com.boiledcoffee.sjwatch.model.entity.Brand;
import com.boiledcoffee.sjwatch.model.entity.GoodsWithBLOBs;
import com.boiledcoffee.sjwatch.model.entity.Order;
import com.boiledcoffee.sjwatch.model.query.GoodsQuery;
import com.boiledcoffee.sjwatch.service.goods.IBannerService;
import com.boiledcoffee.sjwatch.service.goods.IGoodsService;
import com.boiledcoffee.sjwatch.service.order.IOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${juha} on 2018/12/4.
 */
@Controller
public class MallController {
    @Autowired
    IGoodsService goodsService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    IOrderService orderService;
    @Autowired
    IBannerService bannerService;

    @RequestMapping(value = "/")
    public String entry(){
        return "forward:/mall/index";
    }


    @RequestMapping(value = "/mall/index")
    public String index(HttpServletRequest request,Model model){
        String uid = request.getHeader("uid");

        //左边菜单栏品牌列表
        HandleResult<List<Brand>> brandHandleResult = goodsService.findAllBrand(1);
        List<Brand> brands;
        if (brandHandleResult.isError()){
            return "error";
        }
        brands = brandHandleResult.getResult();
        if (brands == null) {
            brands = new ArrayList<>();
        }else if (brands.size() > 9){
            brands = brandHandleResult.getResult().subList(0,9);
        }
        model.addAttribute("brandList",brands);

        //热门商品
        GoodsQuery hotGoodsQuery = new GoodsQuery();
        hotGoodsQuery.setIsHot(1);
        HandleResult<PageRspData<GoodsWithBLOBs>> hotGoodsHandleResult = goodsService.findAllGoods(0,3, hotGoodsQuery,uid);
        if (brandHandleResult.isError()){
            return "error";
        }
        model.addAttribute("hotGoods",hotGoodsHandleResult.getResult().getList());

        //首页商品
        Map<String,List<GoodsWithBLOBs>> goods = new HashMap<>();
        Map<String,Brand> brandMap = new HashMap<>();

        GoodsQuery goodsQuery = new GoodsQuery();
        goodsQuery.setSortTime(2);
        for(Brand brand : brands){
            goodsQuery.settBId(brand.getId());
            HandleResult<PageRspData<GoodsWithBLOBs>> goodsHandleResult = goodsService.findAllGoods(0,6, goodsQuery,uid);
            if (!goodsHandleResult.isError()){
                goods.put(brand.getName(),goodsHandleResult.getResult().getList());
                brandMap.put(brand.getName(),brand);
            }
        }
        model.addAttribute("goodsMap",goods);
        model.addAttribute("brandMap",brandMap);

        HandleResult<List<Banner>> handleBannerResult = bannerService.findAllBanner(4);
        if (!handleBannerResult.isError()){
            model.addAttribute("bannerList",handleBannerResult.getResult());
        }
        return "home/index";
    }

    @RequestMapping(value = "/mall/search/{page}")
    public String search(HttpServletRequest request,Model model,@PathVariable int page,@RequestParam(name = "searchName",required = false) String searchName
            ,@RequestParam(name = "brandId",required = false) Long brandId,@RequestParam(name = "sex",required = false) Integer sex
            ,@RequestParam(name = "sortSalePrice",required = false) Integer sortSalePrice,@RequestParam(name = "isHot",required = false) Integer isHot
            ,@RequestParam(name = "isRecommend",required = false) Integer isRecommend){
        final int pageSize = 12;
        String uid = request.getHeader("uid");
        GoodsQuery goodsQuery = new GoodsQuery();
        String reqParam;
        if (!StringUtils.isEmpty(searchName)){
            goodsQuery.setName(searchName);
            reqParam = "?searchName=" + searchName;
        }else {
            reqParam = "";
        }

        if (brandId != null && brandId > 0){
            goodsQuery.settBId(brandId);
            if (StringUtils.isEmpty(reqParam)){
                reqParam += "?brandId="+brandId;
            }else {
                reqParam += "&brandId=" + brandId;
            }
        }

        if (sex != null && sex > 0){
            goodsQuery.setSex(sex);
            if (StringUtils.isEmpty(reqParam)){
                reqParam += "?sex="+ sex;
            }else {
                reqParam += "&sex=" + sex;
            }
        }

        if (sortSalePrice != null && sortSalePrice > 0){
            goodsQuery.setSortSalePrice(sortSalePrice);
            if (StringUtils.isEmpty(reqParam)){
                reqParam += "?sortSalePrice="+ sortSalePrice;
            }else {
                reqParam += "&sortSalePrice=" + sortSalePrice;
            }
        }

        if (isHot != null && isHot > 0){
            goodsQuery.setIsHot(isHot);
            if (StringUtils.isEmpty(reqParam)){
                reqParam += "?isHot="+ isHot;
            }else {
                reqParam += "&isHot=" + isHot;
            }
        }

        if (isRecommend != null && isRecommend > 0){
            goodsQuery.setIsRecommend(isRecommend);
            if (StringUtils.isEmpty(reqParam)){
                reqParam += "?isRecommend="+ isRecommend;
            }else {
                reqParam += "&isRecommend=" + isRecommend;
            }
        }

        HandleResult<PageRspData<GoodsWithBLOBs>> handleResult = goodsService.findAllGoods(page,pageSize,goodsQuery,uid);
        HandleResult<List<Brand>> brandHandleResult = goodsService.findAllBrand(1);
        if (!brandHandleResult.isError()){
            model.addAttribute("brandList",brandHandleResult.getResult());
        }else {
            model.addAttribute("brandList",new ArrayList<Brand>());
        }

        if (!handleResult.isError()){
            PageRspData<GoodsWithBLOBs> searchResult = handleResult.getResult();
            List<GoodsWithBLOBs> goodsList = searchResult.getList();
            for (GoodsWithBLOBs goods : goodsList){
                goods.setSaleNumber((int)(1000 * Math.random()));
            }



            final long total = searchResult.getTotal();
            final int totalPage = (int)((total + pageSize - 1) / pageSize);
            List<Integer> pageList = new ArrayList<>();

            if (totalPage < 5){
                for (int i=1; i<=totalPage; i++){
                    pageList.add(i);
                }
            }else {
                if (page - 5 >= 1 && page + 5 <= totalPage){
                    for (int i=page - 5; i<=page + 4; i++){
                        pageList.add(i);
                    }
                }else if (page - 5 <= 1){
                    for (int i=1; i<page + 5 + Math.abs(page-5) + 1; i++){
                        pageList.add(i);
                    }
                }else{
                    for (int i=page-9; i<=totalPage; i++){
                        pageList.add(i);
                    }
                }
            }

            model.addAttribute("reqSearchName",searchName);
            model.addAttribute("reqBrandId",brandId == null ? 0 : brandId);
            model.addAttribute("reqSex",sex == null ? 0 : sex);
            model.addAttribute("reqSearchName",searchName);
            model.addAttribute("reqSortSalePrice",sortSalePrice == null ? 0 : sortSalePrice);
            model.addAttribute("reqParam",reqParam);
            model.addAttribute("currentPage",page);
            model.addAttribute("goodsList",goodsList);
            model.addAttribute("pageList",pageList);
            return "home/search";
        }
        return "error";
    }

    @RequestMapping(value = "/mall/detail/{goodsId}")
    public String detail(HttpServletRequest request,Model model,@PathVariable(name = "goodsId") long goodsId){
        String uid = request.getHeader("uid");
        HandleResult<GoodsWithBLOBs> goodsHandleResult = goodsService.getGoodsById(goodsId);
        if (!goodsHandleResult.isError()){
            model.addAttribute("goods",goodsHandleResult.getResult());
            double salePrice = goodsHandleResult.getResult().getSalePrice();
            model.addAttribute("price",salePrice + 1000);
        }

        GoodsQuery goodsQuery = new GoodsQuery();
        goodsQuery.setIsRecommend(1);
        HandleResult<PageRspData<GoodsWithBLOBs>> reGoodsHandleResult = goodsService.findAllGoods(1,5,goodsQuery,uid);
        if (!reGoodsHandleResult.isError()){
            model.addAttribute("reGoodsList",reGoodsHandleResult.getResult().getList());
        }

        return "home/introduction";
    }

    @RequestMapping(value = "/mall/pay/{goodsId}")
    public String pay(HttpServletRequest request,Model model,@PathVariable(name = "goodsId") long goodsId){
        HandleResult<GoodsWithBLOBs> goodsHandleResult = goodsService.getGoodsById(goodsId);
        if (!goodsHandleResult.isError()){
            model.addAttribute("goods",goodsHandleResult.getResult());
        }
        return "home/pay";
    }
}
