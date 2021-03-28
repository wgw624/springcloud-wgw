package org.weiyada.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.weiyada.base.Result;
import org.weiyada.entity.Factory;
import org.weiyada.entity.req.RequestPage;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/25 3:34 下午
 */
@Api(tags = "工厂控制类")
@RequestMapping("factory")
@RestController
public class FactoryController {

    @ApiOperation("保存或更新工厂")
    @PostMapping("saveOrUpdate")
    public Result<Boolean> saveOrUpdate(@RequestBody Factory factory){
        return null;
    }

    @ApiOperation("查询所有的工厂")
    @GetMapping("getAll")
    public Result<Page<Factory>> getAllFactory(RequestPage requestPage){
        return null;
    }

    @ApiOperation("test")
    @GetMapping("getTest")
    public Result<Boolean> getTest(){
        Boolean flag = true;
        return Result.successResult(flag);
    }
}
