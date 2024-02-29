package com.wenjuju.top.controller;

import com.wenjuju.top.bean.Category;
import com.wenjuju.top.bean.Result;

import com.wenjuju.top.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "文章分类")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    @Operation(summary = "添加文章分类", description = "添加文章")
    public Result add(@RequestBody @Validated Category category){
        categoryService.add(category);
        return Result.success();
    }
    //查询分类
    @GetMapping
    @Operation(summary = "获取文章分类", description = "获取文章分类")
    public Result<List<Category>>list(){
       List <Category> cs= categoryService.list();
       return Result.success(cs);
    }
    //根据id 获取分类详情
    @Operation(summary = "获取分类详情", description = "获取分类详情")
//    @Parameters({
//            @Parameter(name = "username", description = "账号", required = true, in = ParameterIn.QUERY),
//            @Parameter(name = "password", description = "密码", required = true, in = ParameterIn.QUERY))
    @GetMapping("/detail")
    public Result<Category>datail(Integer id){
       Category c= categoryService.finById(id);
        return Result.success(c);
    }
    //更新文章分类
    @PutMapping
    @Operation(summary = "更新文章分类", description = "更新文章分类")
    public Result update(@RequestBody @Validated Category category){
       categoryService.update(category);
       return Result.success();
    }
}
