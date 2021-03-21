package org.weiyada.service.impl;

import org.weiyada.entity.ProductionOrder;
import org.weiyada.mapper.ProductionOrderMapper;
import org.weiyada.service.ProductionOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
