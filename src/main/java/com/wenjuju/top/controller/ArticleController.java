package com.wenjuju.top.controller;

import com.wenjuju.top.bean.Article;
import com.wenjuju.top.bean.PageBean;
import com.wenjuju.top.bean.Result;
import com.wenjuju.top.service.ArticleService;
import com.wenjuju.top.utils.jwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Tag(name = "文章管理")

@RestController
@RequestMapping("/article")
public class ArticleController {
//    @GetMapping("/list")
//    public Result <String> list(@RequestHeader(name = "Authorization") String token, HttpServletResponse response){
//       try {
//           Map<String ,Object>claims= jwtUtil.parseToken(token);
//           return Result.success("文章的信息");
//       }catch (Exception e){
//           response.setStatus(401);
//           return Result.error("token异常");
//       }
//    }
//    public Result <String> list(String token, HttpServletResponse response){//使用拦截器
//            return Result.success("文章的信息");
//    }

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @Operation(summary = "添加文章", description = "添加文章")
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }
    @GetMapping
    @Operation(summary = "获取所有文章", description = "获取所有文章")
    @Parameters({
            @Parameter(name = "pageNum", description = "\t页码,示例值(1)", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "pageSize", description = "每页限制数 此值<0时不分页,示例值(10)", required = true, in = ParameterIn.QUERY)})
    public Result<PageBean<Article>>list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state){

      PageBean<Article> pb=  articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
}
