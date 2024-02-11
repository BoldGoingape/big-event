package com.wenjuju.top.service.imp;
//实现类
import com.wenjuju.top.bean.Category;
import com.wenjuju.top.mapper.CategoryMapper;
import com.wenjuju.top.service.CategoryService;
import com.wenjuju.top.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    //新增分类
    @Override
    public void add(Category category) {
        //补充属性参数
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object>map = ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        category.setCreateUser(id);
        System.out.println(category);
    categoryMapper.add(category);
    }
//获取文章列表
    @Override
    public List<Category> list() {
        //当前用户id
        Map<String,Object>map = ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
       return categoryMapper.list(id);
    }

    @Override
    public Category finById(Integer id) {
        Category c=categoryMapper.findByid(id);
        return c;
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
       categoryMapper.update(category);
    }
}
