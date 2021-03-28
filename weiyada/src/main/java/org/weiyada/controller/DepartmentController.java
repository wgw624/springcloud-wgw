package org.weiyada.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.weiyada.base.Result;
import org.weiyada.entity.Department;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.service.DepartmentService;

@RestController
@Api(tags = "部门控制类")
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("保存或更新部门")
    @PostMapping("saveOrUpdate")
    public Result<Boolean> saveOrUpdate(@RequestBody Department department){
        return Result.successResult(departmentService.saveOrUpdate(department));
    }

    @ApiOperation("获取所有的部门")
    @GetMapping("getAll")
    public Result<Page<Department>> getAllDepartment(RequestPage requestPage){
        return Result.successResult(departmentService.getAllDepartmeng(requestPage));
    }
}
