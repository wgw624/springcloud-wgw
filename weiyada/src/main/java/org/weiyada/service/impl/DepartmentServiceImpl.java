package org.weiyada.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.Department;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;
import org.weiyada.mapper.DepartmentMapper;
import org.weiyada.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-25
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public BooleanRes saveOrUpdateDepart(Department department) {
        BooleanRes booleanRes = new BooleanRes();
        booleanRes.setMsg("更新成功");
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getDepartName,department.getDepartName());
        Department depObj = this.getOne(lambdaQueryWrapper);

        if(ObjectUtils.isEmpty(department.getId())){
            if(!ObjectUtils.isEmpty(depObj)){
                booleanRes.setMsg("【"+department.getDepartName()+"】部门已存在");
                booleanRes.setFlag(false);
                return booleanRes;
            }
            department.setCreateTime(Calendar.getInstance().getTimeInMillis());
            booleanRes.setMsg("保存成功");
        }else if(!department.getDepartName().equals(depObj.getDepartName())){
            booleanRes.setMsg("【"+department.getDepartName()+"】部门已存在");
            booleanRes.setFlag(false);
            return booleanRes;
        }
        booleanRes.setFlag(this.saveOrUpdate(department));
        return booleanRes;
    }

    @Override
    public Page<Department> getAllDepartmeng(RequestPage requestPage) {
        return this.page(new Page<Department>(requestPage.getPageIndex(),requestPage.getPageSize()));
    }
}
