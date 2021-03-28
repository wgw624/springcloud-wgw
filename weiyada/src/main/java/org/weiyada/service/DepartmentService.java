package org.weiyada.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.weiyada.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import org.weiyada.entity.req.RequestPage;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author 
 * @since 2021-03-25
 */
public interface DepartmentService extends IService<Department> {
    Boolean saveOrUpdateDepart(Department department);
    Page<Department> getAllDepartmeng(RequestPage requestPage);
}
