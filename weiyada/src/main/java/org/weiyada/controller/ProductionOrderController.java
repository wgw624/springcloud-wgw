package org.weiyada.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.weiyada.base.Result;
import org.weiyada.entity.ProductionOrder;
import org.weiyada.entity.UserAuth;
import org.weiyada.entity.req.QueryProductionOrderReq;
import org.weiyada.service.ProductionOrderService;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/24 4:48 下午
 */
@RestController
@RequestMapping("productionOrder")
@Api(tags = "生产订单管理")
public class ProductionOrderController {

    @Autowired
    private ProductionOrderService productionOrderService;

    @Autowired
    private UserAuth userAuth;

    @PostMapping("saveOrUpdate")
    @ApiOperation("保存或更新生产订单")
    public Result<Boolean> saveOrUpdate(@RequestBody ProductionOrder productionOrder){
        productionOrder.setCreateUserId(userAuth.getUserId());
        return Result.successResult(productionOrderService.saveOrUpdateProductionOrder(productionOrder));
    }

    @ApiOperation("查询生产订单")
    @GetMapping("getProductionOrder")
    public Result<Page<ProductionOrder>> getProductionOrder(QueryProductionOrderReq queryProductionOrderReq){
        return Result.successResult(productionOrderService.getProductionOrder(queryProductionOrderReq));
    }
}
