package org.weiyada.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.weiyada.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
