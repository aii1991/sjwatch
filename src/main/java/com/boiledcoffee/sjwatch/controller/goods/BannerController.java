package com.boiledcoffee.sjwatch.controller.goods;

import com.boiledcoffee.sjwatch.controller.ApiBaseController;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.Banner;
import com.boiledcoffee.sjwatch.service.goods.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ${juha} on 2019/1/2.
 *
 */
@RestController
public class BannerController extends ApiBaseController{
    @Autowired
    IBannerService bannerService;

    @RequestMapping(value = "/banner",method = RequestMethod.POST)
    public HandleResult<Banner> addBanner(@RequestBody Banner banner){
        return bannerService.insertBanner(banner);
    }

    @RequestMapping(value = "/banner",method = RequestMethod.PUT)
    public HandleResult<Banner> modifyBanner(@RequestBody Banner banner){
        return bannerService.modifyBanner(banner);
    }

    @RequestMapping(value = "/banner/{id}",method = RequestMethod.DELETE)
    public HandleResult<Map> deleteBannerById(@PathVariable long id){
        return bannerService.deleteBannerById(id);
    }

    @RequestMapping(value = "/banner/{limit}",method = RequestMethod.GET)
    public  HandleResult<List<Banner>> findAllBanner(@PathVariable int limit){
        return bannerService.findAllBanner(limit);
    }
}
