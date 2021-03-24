package org.weiyada.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.weiyada.entity.ProductionOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.weiyada.entity.req.QueryProductionOrderReq;

/**
 * <p>
 * 生产订单表 服务类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
public interface ProductionOrderService extends IService<ProductionOrder> {
    /**
     *
     *@description: 保存或更新生产订单
     * @param productionOrder
     *@return: boolean
     *@author: weiguangwei
     *@time: 2021/3/24 3:06 下午
     */
    boolean saveOrUpdateProductionOrder(ProductionOrder productionOrder);

    /**
     *
     *@description: 查询生产订单
     * @param queryProductionOrderReq
     *@return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<org.weiyada.entity.ProductionOrder>
     *@author: weiguangwei
     *@time: 2021/3/24 3:06 下午
     */
    Page<ProductionOrder> getProductionOrder(QueryProductionOrderReq queryProductionOrderReq);
}
