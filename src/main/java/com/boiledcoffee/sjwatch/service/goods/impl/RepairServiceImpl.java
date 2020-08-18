package com.boiledcoffee.sjwatch.service.goods.impl;

import com.boiledcoffee.sjwatch.dao.RepairMapper;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Repair;
import com.boiledcoffee.sjwatch.service.goods.IRepairService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${juha} on 2020/8/17.
 *
 */
@Service("repairService")
public class RepairServiceImpl implements IRepairService{
    @Autowired
    RepairMapper repairMapper;
    
    @Override
    public HandleResult<Repair> insertRepair(Repair repair) {
        HandleResult<Repair> handleResult = new HandleResult<>();
        long repairId = repairMapper.insertSelective(repair);
        try{
            if (repairId > 0){
                handleResult.setResult(repair);
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
    public HandleResult<Repair> modifyRepair(Repair repair) {
        HandleResult<Repair> handleResult = new HandleResult<>();
        long resultId = repairMapper.updateByPrimaryKeySelective(repair);
        try{
            if (resultId > 0){
                handleResult.setResult(repair);
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
    public HandleResult<Map> deleteRepairById(long id) {
        HandleResult<Map> handleResult = new HandleResult<>();
        try{
            int result = repairMapper.deleteByPrimaryKey(id);
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
    public HandleResult<PageRspData<Repair>> findAllRepair(int page,int pageSize) {
        HandleResult<PageRspData<Repair>> handleResult = new HandleResult<>();
        int offSet = (page - 1) * pageSize;
        if (offSet < 0){
            offSet = 0;
        }
        try {
            Page page1 = PageHelper.startPage(page, pageSize, true);

            List<Repair> orderList = repairMapper.findAllRepairs(offSet, pageSize);
            long total = page1.getTotal();
            PageRspData<Repair> pageRspData = new PageRspData<>(total,orderList);
            handleResult.setResult(pageRspData);

        }catch (Exception e){
            e.printStackTrace();
            handleResult.setErrorMsg(e.getMessage());
        }
        return handleResult;
    }
}
