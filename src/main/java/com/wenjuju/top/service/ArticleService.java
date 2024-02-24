package com.wenjuju.top.service;

import com.wenjuju.top.bean.Article;
import com.wenjuju.top.bean.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
