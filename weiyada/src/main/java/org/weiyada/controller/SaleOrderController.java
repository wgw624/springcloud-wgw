package org.weiyada.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.weiyada.base.Result;
import org.weiyada.entity.SaleOrder;
import org.weiyada.entity.req.QuerySaleOrderReq;
import org.weiyada.service.SaleOrderService;
import springfox.documentation.service.Tags;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/24 3:23 下午
 */
@RestController
@RequestMapping("/saleOrder")
@Api(tags="销售订单控制类")
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @PostMapping("saveOrUpdate")
    @ApiOperation("保存或更新销售订单")
    public Result<Boolean> saveOrUpdate(@RequestBody SaleOrder saleOrder){
        return Result.successResult(saleOrderService.saveOrUpdateSaleOrder(saleOrder));
    }

    @GetMapping("getSaleOrder")
    @ApiOperation("查询销售订单")
    public Result<Page<SaleOrder>> getSaleOrder(QuerySaleOrderReq querySaleOrderReq){
        return Result.successResult(saleOrderService.getAllSaleOrder(querySaleOrderReq));
    }
}
