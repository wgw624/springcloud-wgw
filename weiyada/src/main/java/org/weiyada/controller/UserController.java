package org.weiyada.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weiyada.base.Result;
import org.weiyada.entity.UserInfo;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.req.UserLoginReq;
import org.weiyada.entity.res.BooleanRes;
import org.weiyada.service.UserInfoService;

@RestController
@RequestMapping("userInf")
@Api(tags = "用户控制类")
@Slf4j
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @PostMapping("/saveOrUpdate")
    @ApiOperation("新增或者更新用户")
    public Result<BooleanRes> saveUser(@RequestBody UserInfo userInfo){
        return Result.successResult(userInfoService.saveOrUpdateUserInfo(userInfo));
    }

    @PostMapping("/queryUser")
    @ApiOperation("查询用户")
    public Result<Page<UserInfo>> queryAllUser(RequestPage requestPage){
       return Result.successResult(userInfoService.getAllUser(requestPage));
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result<BooleanRes> userLogin(UserLoginReq userLoginReq){
        return Result.successResult(userInfoService.login(userLoginReq));
    }
}
