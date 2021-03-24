package org.weiyada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weiyada.base.Result;
import org.weiyada.entity.SaleOrder;
import org.weiyada.service.SaleOrderService;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/24 3:23 下午
 */
@RestController
@RequestMapping("/saleOrder")
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @RequestMapping("saveOrUpdate")
    public Result<Boolean> saveOrUpdate(SaleOrder saleOrder){
        return Result.successResult(saleOrderService.saveOrUpdateSaleOrder(saleOrder));
    }
}
