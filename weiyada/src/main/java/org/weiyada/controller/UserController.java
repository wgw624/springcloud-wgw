package org.weiyada.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.weiyada.base.Result;
import org.weiyada.entity.UserAuth;
import org.weiyada.entity.UserInfo;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.req.UserLoginReq;
import org.weiyada.entity.res.BooleanRes;
import org.weiyada.entity.res.UserLoginRes;
import org.weiyada.service.UserInfoService;
import org.weiyada.util.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
@Api(tags = "用户管理")
@Slf4j
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/saveOrUpdate")
    @ApiOperation("新增或者更新用户")
    public Result<BooleanRes> saveUser(@RequestBody UserInfo userInfo){
        return Result.successResult(userInfoService.saveOrUpdateUserInfo(userInfo));
    }

    @GetMapping("/queryUser")
    @ApiOperation("查询用户")
    public Result<Page<UserInfo>> queryAllUser(RequestPage requestPage){
       return Result.successResult(userInfoService.getAllUser(requestPage));
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result<UserLoginRes> userLogin(HttpServletRequest req, HttpServletResponse res, @RequestBody UserLoginReq userLoginReq){
        return Result.successResult(userInfoService.login(req,res,userLoginReq));
    }

    @ApiOperation("用户登出")
    @PostMapping("logout")
    public Result<BooleanRes> userLogout(HttpServletRequest req, HttpServletResponse res){
        return Result.successResult(userInfoService.logout(req,res));
    }
}
