package org.weiyada.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.weiyada.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
public interface RoleService extends IService<Role> {
    /**
     *
     *@description:
     * @param role
     *@return: org.weiyada.entity.res.BooleanRes
     *@author: weiguangwei
     *@time: 2021/3/31 11:53 上午
     */
    BooleanRes saveOrUpdateRole(Role role);

    /**
     *
     *@description:
     * @param requestPage
     *@return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<org.weiyada.entity.Role>
     *@author: weiguangwei
     *@time: 2021/3/31 11:53 上午
     */
    Page<Role> getRole(RequestPage requestPage);
}
