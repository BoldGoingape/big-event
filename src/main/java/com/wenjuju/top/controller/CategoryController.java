package com.wenjuju.top.controller;

import com.wenjuju.top.bean.Category;
import com.wenjuju.top.bean.Result;

import com.wenjuju.top.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody @Validated Category category){
        categoryService.add(category);
        return Result.success();
    }
    //查询分类
    @GetMapping
    public Result<List<Category>>list(){
       List <Category> cs= categoryService.list();
       return Result.success(cs);
    }
    //根据id 获取分类详情
    @GetMapping("/detail")
    public Result<Category>datail(Integer id){
       Category c= categoryService.finById(id);
        return Result.success(c);
    }
    //更新文章分类
    @PutMapping
    public Result update(@RequestBody @Validated Category category){
       categoryService.update(category);
       return Result.success();
    }
}
