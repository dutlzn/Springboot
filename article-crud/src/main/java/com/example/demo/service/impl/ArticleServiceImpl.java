//package com.example.demo.service.impl;
//
//import com.example.demo.entity.Article;
//import com.example.demo.repository.ArticleRepository;
//import com.example.demo.service.ArticleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 文章服务实现类
// */
//@Service
//public class ArticleServiceImpl implements ArticleService {
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    /*
//    获取文章列表
//     */
//    @Override
//    public List<Article> getArticleList() {
//        return articleRepository.findAll();
//    }
//
//    /*
//    通过id获取文章
//     */
//    @Override
//    public Article findArticleById(long id) {
//        return articleRepository.findById(id);
//    }
//}
