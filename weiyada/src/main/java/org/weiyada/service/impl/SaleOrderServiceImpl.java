package org.weiyada.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.req.QuerySaleOrderReq;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.SaleOrder;
import org.weiyada.mapper.SaleOrderMapper;
import org.weiyada.service.SaleOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * <p>
 * 销售订单表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements SaleOrderService {

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Override
    public boolean saveOrUpdateSaleOrder(SaleOrder saleOrder) {
        if(ObjectUtils.isEmpty(saleOrder.getOrderId())){
            saleOrder.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        return this.saveOrUpdate(saleOrder);
    }

    @Override
    public Page<SaleOrder> getAllSaleOrder(QuerySaleOrderReq querySaleOrderReq) {
        LambdaQueryWrapper<SaleOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(!ObjectUtils.isEmpty(querySaleOrderReq.getIsPay()),SaleOrder::getIsPay,querySaleOrderReq.getIsPay());
        lambdaQueryWrapper.eq(!ObjectUtils.isEmpty(querySaleOrderReq.getStates()),SaleOrder::getStates,querySaleOrderReq.getStates());
        Page<SaleOrder> page = saleOrderMapper.selectPage(new Page<SaleOrder>(querySaleOrderReq.getPageIndex(),querySaleOrderReq.getPageSize()),lambdaQueryWrapper);
        return page;
    }
}
