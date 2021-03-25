package org.weiyada.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.weiyada.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.weiyada.entity.req.RequestPage;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     *
     *@description:
     * @param userInfo
     *@return: boolean
     *@author: weiguangwei
     *@time: 2021/3/25 2:52 下午
     */
    boolean saveOrUpdateUserInfo(UserInfo userInfo);

    /**
     *
     *@description: 查询所有用户信息
     * @param requestPage
     *@return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<org.weiyada.entity.UserInfo>
     *@author: weiguangwei
     *@time: 2021/3/25 2:53 下午
     */
    Page<UserInfo> getAllUser(RequestPage requestPage);
}
