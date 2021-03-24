package org.weiyada.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.weiyada.entity.req.QuerySaleOrderReq;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.SaleOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 销售订单表 服务类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
public interface SaleOrderService extends IService<SaleOrder> {
    /**
     *
     *@description: 保存或更新销售订单
     * @param saleOrder
     *@return: boolean
     *@author: weiguangwei
     *@time: 2021/3/24 1:59 下午
     */
    boolean saveOrUpdateSaleOrder(SaleOrder saleOrder);
    /**
     *
     *@description: 查询所有的订单
     * @param requestPage
     *@return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<org.weiyada.entity.SaleOrder>
     *@author: weiguangwei
     *@time: 2021/3/24 1:59 下午
     */
    Page<SaleOrder> getAllSaleOrder(QuerySaleOrderReq querySaleOrderReq);
}
