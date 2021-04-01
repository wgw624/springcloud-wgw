package org.weiyada.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.weiyada.base.Result;
import org.weiyada.entity.MenuRole;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;
import org.weiyada.service.MenuRoleService;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/4/1 3:23 下午
 */
@Api(tags = "角色菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuRoleController {

    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation("保存或更新菜单")
    @PostMapping("saveOrUpdate")
    public Result<BooleanRes> saveOrUpdate(@RequestBody MenuRole menuRole){
        return Result.successResult(menuRoleService.saveOrUpdateMenu(menuRole));
    }

    @GetMapping("/get")
    @ApiOperation("查询所有菜单")
    public Result<Page<MenuRole>> getMenu(RequestPage requestPage){
        return Result.successResult(menuRoleService.getAllMenu(requestPage));
    }
}
