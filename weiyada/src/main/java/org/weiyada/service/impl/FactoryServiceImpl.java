package org.weiyada.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.Factory;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;
import org.weiyada.mapper.FactoryMapper;
import org.weiyada.service.FactoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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
    public BooleanRes saveOrUpdateFactory(Factory factory) {
        BooleanRes booleanRes = new BooleanRes();
        booleanRes.setMsg("更新成功");
        LambdaQueryWrapper<Factory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Factory::getName,factory.getName());
        Factory factoryObj = this.getOne(lambdaQueryWrapper);
        if(ObjectUtils.isEmpty(factory.getId())){
            if(!ObjectUtils.isEmpty(factoryObj)){
               booleanRes.setFlag(false);
               booleanRes.setMsg("【"+factory.getName()+"】工厂已经存在");
               return booleanRes;
            }
            factory.setCreateTime(Calendar.getInstance().getTimeInMillis());
            booleanRes.setMsg("保存成功");
        }else if(!factory.getName().equals(factoryObj.getName())){
            booleanRes.setFlag(false);
            booleanRes.setMsg("【"+factory.getName()+"】工厂已经存在");
            return booleanRes;
        }
        booleanRes.setFlag(this.saveOrUpdate(factory));
        return booleanRes;
    }

    @Override
    public Page<Factory> getAllFactory(RequestPage requestPage) {
        LambdaQueryWrapper<Factory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        return this.page(new Page<Factory>(requestPage.getPageIndex(),requestPage.getPageSize()),lambdaQueryWrapper);
    }
}
