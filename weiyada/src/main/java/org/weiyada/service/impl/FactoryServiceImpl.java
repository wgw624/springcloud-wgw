package org.weiyada.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.Factory;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.mapper.FactoryMapper;
import org.weiyada.service.FactoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-25
 */
@Service
public class FactoryServiceImpl extends ServiceImpl<FactoryMapper, Factory> implements FactoryService {

    @Override
    public boolean saveOrUpdateFactory(Factory factory) {
        if(ObjectUtils.isEmpty(factory.getId())){
            factory.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        return this.saveOrUpdate(factory);
    }

    @Override
    public Page<Factory> getAllFactory(RequestPage requestPage) {
        LambdaQueryWrapper<Factory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        return this.page(new Page<Factory>(requestPage.getPageIndex(),requestPage.getPageSize()),lambdaQueryWrapper);
    }
}
