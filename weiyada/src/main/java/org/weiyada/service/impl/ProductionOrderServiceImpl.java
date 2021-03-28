package org.weiyada.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.ProductionOrder;
import org.weiyada.entity.req.QueryProductionOrderReq;
import org.weiyada.mapper.ProductionOrderMapper;
import org.weiyada.service.ProductionOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * <p>
 * 生产订单表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
@Service
public class ProductionOrderServiceImpl extends ServiceImpl<ProductionOrderMapper, ProductionOrder> implements ProductionOrderService {

    @Autowired
    private ProductionOrderMapper productionOrderMapper;

    @Override
    public boolean saveOrUpdateProductionOrder(ProductionOrder productionOrder) {
        if(ObjectUtils.isEmpty(productionOrder.getId())){
            productionOrder.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        return this.saveOrUpdate(productionOrder);
    }

    @Override
    public Page<ProductionOrder> getProductionOrder(QueryProductionOrderReq queryProductionOrderReq) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        Page page = productionOrderMapper.selectPage(new Page<ProductionOrder>(queryProductionOrderReq.getPageIndex(),queryProductionOrderReq.getPageSize()),lambdaQueryWrapper);
        return page;
    }
}
