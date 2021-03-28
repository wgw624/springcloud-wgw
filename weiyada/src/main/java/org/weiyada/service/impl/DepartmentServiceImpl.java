package org.weiyada.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.Department;
import org.weiyada.entity.req.RequestPage;
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
    public Boolean saveOrUpdateDepart(Department department) {
        if(ObjectUtils.isEmpty(department.getId())){
            department.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        return this.saveOrUpdate(department);
    }

    @Override
    public Page<Department> getAllDepartmeng(RequestPage requestPage) {
        return this.page(new Page<Department>(requestPage.getPageIndex(),requestPage.getPageSize()));
    }
}
