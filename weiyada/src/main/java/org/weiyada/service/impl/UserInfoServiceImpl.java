package org.weiyada.service.impl;

import org.weiyada.entity.UserInfo;
import org.weiyada.mapper.UserInfoMapper;
import org.weiyada.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
