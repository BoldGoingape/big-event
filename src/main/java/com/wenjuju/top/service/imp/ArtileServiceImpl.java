package com.wenjuju.top.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wenjuju.top.bean.Article;
import com.wenjuju.top.bean.PageBean;
import com.wenjuju.top.mapper.ArticleMapper;
import com.wenjuju.top.service.ArticleService;
import com.wenjuju.top.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service

public class ArtileServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建pageBean对象
         PageBean<Article>pb=new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper
     Map<String,Object>map=ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
       List<Article>as= articleMapper.list(userId,categoryId,state);
       //page中提供了方法，可以获取PageHelper分页查询后，得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
