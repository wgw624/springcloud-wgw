package org.weiyada.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.weiyada.entity.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;

/**
 * <p>
 * 角色菜单配置 服务类
 * </p>
 *
 * @author 
 * @since 2021-04-01
 */
public interface MenuRoleService extends IService<MenuRole> {
    /**
     *
     *@description:
     * @param menuRole
     *@return: org.weiyada.entity.res.BooleanRes
     *@author: weiguangwei
     *@time: 2021/4/1 3:29 下午
     */
    BooleanRes saveOrUpdateMenu(MenuRole menuRole);

    /**
     *
     *@description:
     * @param requestPage
     *@return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<org.weiyada.entity.MenuRole>
     *@author: weiguangwei
     *@time: 2021/4/1 3:31 下午
     */
    Page<MenuRole> getAllMenu(RequestPage requestPage);
}
