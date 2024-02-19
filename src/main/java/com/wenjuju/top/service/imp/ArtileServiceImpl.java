package com.wenjuju.top.service.imp;

import com.wenjuju.top.bean.Article;
import com.wenjuju.top.mapper.ArticleMapper;
import com.wenjuju.top.service.ArticleService;
import com.wenjuju.top.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
}
