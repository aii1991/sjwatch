package com.boiledcoffee.sjwatch.controller.goods;

import com.boiledcoffee.sjwatch.controller.ApiBaseController;
import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.model.communication.PageRspData;
import com.boiledcoffee.sjwatch.model.entity.Repair;
import com.boiledcoffee.sjwatch.service.goods.IRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by ${juha} on 2020/8/17.
 *
 */
@RestController
public class RepairController extends ApiBaseController {

    @Autowired
    IRepairService repairService;

    @RequestMapping(value = "/repair",method = RequestMethod.POST)
    public HandleResult<Repair> addRepair(@RequestBody Repair Repair){
        return repairService.insertRepair(Repair);
    }

    @RequestMapping(value = "/repair",method = RequestMethod.PUT)
    public HandleResult<Repair> modifyRepair(@RequestBody Repair Repair){
        return repairService.modifyRepair(Repair);
    }

    @RequestMapping(value = "/repair/{id}",method = RequestMethod.DELETE)
    public HandleResult<Map> deleteRepairById(@PathVariable long id){
        return repairService.deleteRepairById(id);
    }

    @RequestMapping(value = "/repair/{page}/{page_size}",method = RequestMethod.GET)
    public  HandleResult<PageRspData<Repair>> findAllRepair(@PathVariable int page,@PathVariable(value = "page_size") int pageSize){
        return repairService.findAllRepair(page,pageSize);
    }    
    
}
