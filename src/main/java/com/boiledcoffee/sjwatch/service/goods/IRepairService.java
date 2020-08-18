package com.boiledcoffee.sjwatch.service.goods;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Repair;

import java.util.Map;

/**
 * Created by ${juha} on 2020/8/17.
 *
 */
public interface IRepairService {
    HandleResult<Repair> insertRepair(Repair repair);

    HandleResult<Repair> modifyRepair(Repair repair);

    HandleResult<Map> deleteRepairById(long id);

    HandleResult<PageRspData<Repair>> findAllRepair(int page,int pageSize);

}
