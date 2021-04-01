package org.weiyada.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.MenuRole;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;
import org.weiyada.mapper.MenuRoleMapper;
import org.weiyada.service.MenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 角色菜单配置 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-04-01
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Override
    public BooleanRes saveOrUpdateMenu(MenuRole menuRole) {
        BooleanRes booleanRes = new BooleanRes();
        booleanRes.setMsg("更新成功");
        LambdaQueryWrapper<MenuRole> lambdaQueryWrapper  = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ObjectUtils.isEmpty(menuRole.getMenuCode()),MenuRole::getMenuCode,menuRole.getMenuCode());
        MenuRole menu = this.getOne(lambdaQueryWrapper);

        if(ObjectUtils.isEmpty(menuRole.getId())){
            if(!ObjectUtils.isEmpty(menu)){
                booleanRes.setMsg("menuCode["+menuRole.getMenuCode()+"] 已经存在");
                booleanRes.setFlag(false);
              return booleanRes;
            }
            menuRole.setCreateTime(Calendar.getInstance().getTimeInMillis());
            booleanRes.setMsg("保存成功");
//            更新 menu 一定存在
        }else if(!menuRole.getMenuCode().equals(menu.getMenuCode())){
            booleanRes.setMsg("menuCode["+menuRole.getMenuCode()+"] 已经存在");
            booleanRes.setFlag(false);
            return booleanRes;
        }
        booleanRes.setFlag(this.saveOrUpdate(menuRole));
        return booleanRes;
    }

    @Override
    public Page<MenuRole> getAllMenu(RequestPage requestPage) {
        return this.page(new Page<MenuRole>(requestPage.getPageIndex(),requestPage.getPageSize()));
    }
}
