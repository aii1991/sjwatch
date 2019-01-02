package com.boiledcoffee.sjwatch.service.goods.impl;

import com.boiledcoffee.sjwatch.dao.BannerMapper;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.Banner;
import com.boiledcoffee.sjwatch.service.goods.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${juha} on 2019/1/2.
 *
 */
@Service("bannerService")
public class BannerServiceImpl implements IBannerService{
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public HandleResult<Banner> insertBanner(Banner banner) {
        HandleResult<Banner> handleResult = new HandleResult<>();
        long bannerId = bannerMapper.insertSelective(banner);
        try{
            if (bannerId > 0){
                handleResult.setResult(banner);
            }else {
                handleResult.setErrorMsg("insert banner error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Banner> modifyBanner(Banner banner) {
        HandleResult<Banner> handleResult = new HandleResult<>();
        long resultId = bannerMapper.updateByPrimaryKeySelective(banner);
        try{
            if (resultId > 0){
                handleResult.setResult(banner);
            }else {
                handleResult.setErrorMsg("modify banner error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<Map> deleteBannerById(long id) {
        HandleResult<Map> handleResult = new HandleResult<>();
        try{
            int result = bannerMapper.deleteByPrimaryKey(id);
            if (result > 0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",id);
                handleResult.setResult(map);
            }else {
                handleResult.setErrorMsg("delete banner error");
            }
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }

    @Override
    public HandleResult<List<Banner>> findAllBanner(int limit) {
        HandleResult<List<Banner>> handleResult = new HandleResult<>();
        try {
            List<Banner> banners = bannerMapper.findAllBanner(limit);
            handleResult.setResult(banners);
        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }
}
