package com.boiledcoffee.sjwatch.service.goods;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.Banner;

import java.util.List;
import java.util.Map;

/**
 * Created by ${juha} on 2019/1/2.
 *
 */
public interface IBannerService {
    HandleResult<Banner> insertBanner(Banner banner);

    HandleResult<Banner> modifyBanner(Banner banner);

    HandleResult<Map> deleteBannerById(long id);

    HandleResult<List<Banner>> findAllBanner(int limit);
}
