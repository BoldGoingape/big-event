package com.wenjuju.top.service;

import com.wenjuju.top.bean.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category finById(Integer id);

    void update(Category category);
}
