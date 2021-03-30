package org.weiyada.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.weiyada.entity.Audience;
import org.weiyada.entity.UserInfo;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.req.UserLoginReq;
import org.weiyada.entity.res.BooleanRes;
import org.weiyada.entity.res.UserLoginRes;
import org.weiyada.mapper.UserInfoMapper;
import org.weiyada.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.weiyada.util.CookieUtil;
import org.weiyada.util.EncryptUtil;
import org.weiyada.util.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private Audience audience;

    @Override
    public BooleanRes saveOrUpdateUserInfo(UserInfo userInfo) {

        BooleanRes booleanRes = new BooleanRes();
        List<UserInfo> list = this.list(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginName,userInfo.getLoginName()));
        if(ObjectUtils.isEmpty(userInfo.getId())){
            if(list.size()>0){
                booleanRes.setMsg("{ "+userInfo.getLoginName()+ " }登录名已经存在");
                return booleanRes;
            }
            booleanRes.setMsg("新增成功");
            userInfo.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }else{
            if(list.size()==1 && list.get(0).getId().equals(userInfo.getId())){
                booleanRes.setMsg("更新成功");
            }else{
                booleanRes.setMsg("{ "+userInfo.getLoginName()+ " }登录名已经存在");
                booleanRes.setFlag(false);
                return booleanRes;
            }
        }
        String salt = EncryptUtil.getSalt();
        String encryptPassword = EncryptUtil.getSaltMd5(userInfo.getPassword(),salt);
        userInfo.setPassword(encryptPassword);
        userInfo.setSalt(salt);
        booleanRes.setFlag(this.saveOrUpdate(userInfo));
        return booleanRes;

    }

    @Override
    public Page<UserInfo> getAllUser(RequestPage requestPage) {
        return this.page(new Page<UserInfo>(requestPage.getPageIndex(),requestPage.getPageSize()),
                Wrappers.<UserInfo>lambdaQuery());
    }

    @Override
    public UserLoginRes login(HttpServletRequest req, HttpServletResponse res, UserLoginReq userLoginReq) {
        BooleanRes booleanRes = new BooleanRes();
        UserLoginRes userLoginRes = new UserLoginRes();
        UserInfo user = this.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginName,userLoginReq.getLoginName()));
        if(!ObjectUtils.isEmpty(user)){
           String salt = user.getSalt();
           String encrytpPassword = EncryptUtil.getSaltMd5(userLoginReq.getPassword(),salt);
           boolean flag = encrytpPassword.equals(user.getPassword());
           if(flag){
               booleanRes.setFlag(flag);
               booleanRes.setMsg("登录成功");
               String token = JwtTokenUtil.createJWT(user,audience);
               userLoginRes.setUserInfo(user);
               userLoginRes.setToken(token);
               CookieUtil.addCookie(req,res,"Authorization",token);
           }else{
               booleanRes.setFlag(false);
               booleanRes.setMsg("密码错误");
           }
           userLoginRes.setBooleanRes(booleanRes);
//           写cookie
           return userLoginRes;
        }else{
            booleanRes.setFlag(false);
            booleanRes.setMsg("用户名不存在");
            userLoginRes.setBooleanRes(booleanRes);
        }

        return userLoginRes;
    }

    @Override
    public BooleanRes logout(HttpServletRequest req, HttpServletResponse res) {
        String tokenName = "Authorization";
        CookieUtil.delCookie(req,res,tokenName);
        BooleanRes booleanRes = new BooleanRes();
        booleanRes.setFlag(true);
        booleanRes.setMsg("退出成功");
        return booleanRes;
    }
}
