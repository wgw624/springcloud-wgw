package org.weiyada.service.impl;

import org.weiyada.entity.Department;
import org.weiyada.mapper.DepartmentMapper;
import org.weiyada.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
