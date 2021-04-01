package org.weiyada.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.Role;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;
import org.weiyada.mapper.RoleMapper;
import org.weiyada.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public BooleanRes saveOrUpdateRole(Role role) {
        BooleanRes booleanRes = new BooleanRes();
        booleanRes.setMsg("更新成功");

        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Role::getRoleName,role.getRoleName());
        Role roleObj = this.getOne(lambdaQueryWrapper);

        if(ObjectUtils.isEmpty(role.getId())){
            if(!ObjectUtils.isEmpty(roleObj)){
                booleanRes.setFlag(false);
                booleanRes.setMsg("该角色【"+role.getRoleName()+"】已经存在");
                return booleanRes;
            }
            role.setCreateTime(Calendar.getInstance().getTimeInMillis());
            booleanRes.setMsg("保存成功");
//            更新roleObj 一定存在
        }else if(!role.getId().equals(roleObj.getId())){
            booleanRes.setFlag(false);
            booleanRes.setMsg("该角色【"+role.getRoleName()+"】已经存在");
            return booleanRes;
        }
        booleanRes.setFlag(this.saveOrUpdate(role));
        return booleanRes;
    }

    @Override
    public Page<Role> getRole(RequestPage requestPage) {
        return this.page(new Page<Role>(requestPage.getPageIndex(),requestPage.getPageSize()));
    }
}
