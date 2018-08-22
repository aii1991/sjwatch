package com.boiledcoffee.sjwatch.service.goods;

import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Brand;
import com.boiledcoffee.sjwatch.model.entity.Goods;
import com.boiledcoffee.sjwatch.model.entity.GoodsType;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.entity.GoodsWithBLOBs;
import com.boiledcoffee.sjwatch.model.query.GoodQuery;

import java.util.List;
import java.util.Map;

/**
 * Created by juha on 2018/1/10.
 */
public interface IGoodsService {
    /**
     * 插入商品
     * @param goods
     * @return
     */
    HandleResult<GoodsWithBLOBs> insertGoods(GoodsWithBLOBs goods);

    /**
     * 修改商品
     * @param goods
     * @return
     */
    HandleResult<GoodsWithBLOBs> modifyGoods(GoodsWithBLOBs goods);

    /**
     * 查找商品
     * @param page
     * @param pageSize
     * @param goodQuery
     * @return
     */
    HandleResult<PageRspData<GoodsWithBLOBs>> findAllGoods(int page,int pageSize,GoodQuery goodQuery,String uid);

    /**
     * 通过商品id删除商品
     * @param id
     * @return
     */
    HandleResult<Map> deleteGoodsById(long id);

    /**
     * 插入商品类型
     * @param goodsType
     * @return
     */
    HandleResult<GoodsType> insertGoodsType(GoodsType goodsType);

    /**
     * 列出所有商品类型
     * @return
     */
    HandleResult<List<GoodsType>> findAllGoodsType();

    /**
     * 删除商品类型
     * @param id
     * @return
     */
    HandleResult<Map> deleteGoodsTypeById(long id);

    /**
     * 插入品牌
     * @return
     */
    HandleResult<Brand> insertBrand(Brand brand);

    /**
     * 通过商品类型查询品牌
     * @return
     */
    HandleResult<List<Brand>> findAllBrand(long type);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    HandleResult<Map> deleteBrandById(long id);
}
