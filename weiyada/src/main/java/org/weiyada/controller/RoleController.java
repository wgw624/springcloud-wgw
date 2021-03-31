package org.weiyada.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weiyada.base.Result;
import org.weiyada.entity.Role;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;
import org.weiyada.service.RoleService;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/31 11:45 上午
 */
@RequestMapping("role")
@RestController
@Api(tags="角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("保存或新增角色")
    @PostMapping("/saveOrUpdate")
    public Result<BooleanRes> saveOrUpdate(Role role){
        return Result.successResult(roleService.saveOrUpdateRole(role));
    }

    @ApiOperation("查询角色")
    @GetMapping("queryRole")
    public Result<Page<Role>> getRole(RequestPage requestPage){
      return Result.successResult(roleService.getRole(requestPage));
    }
}
