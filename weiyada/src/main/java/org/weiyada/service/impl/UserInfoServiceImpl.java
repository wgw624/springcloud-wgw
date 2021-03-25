package org.weiyada.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.UserInfo;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.mapper.UserInfoMapper;
import org.weiyada.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public boolean saveOrUpdateUserInfo(UserInfo userInfo) {
        if(ObjectUtils.isEmpty(userInfo.getId())){
            userInfo.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        return this.saveOrUpdate(userInfo);
    }

    @Override
    public Page<UserInfo> getAllUser(RequestPage requestPage) {
        return this.page(new Page<UserInfo>(requestPage.getPageIndex(),requestPage.getPageSize()),
                Wrappers.<UserInfo>lambdaQuery());
    }
}
