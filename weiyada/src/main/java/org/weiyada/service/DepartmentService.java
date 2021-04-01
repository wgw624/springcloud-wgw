package org.weiyada.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.weiyada.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author 
 * @since 2021-03-25
 */
public interface DepartmentService extends IService<Department> {
    /**
     *
     *@description:
     * @param department
     *@return: java.lang.Boolean
     *@author: weiguangwei
     *@time: 2021/4/1 4:16 下午
     */
    BooleanRes saveOrUpdateDepart(Department department);

    /**
     *
     *@description:
     * @param requestPage
     *@return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<org.weiyada.entity.Department>
     *@author: weiguangwei
     *@time: 2021/4/1 4:16 下午
     */
    Page<Department> getAllDepartmeng(RequestPage requestPage);
}
