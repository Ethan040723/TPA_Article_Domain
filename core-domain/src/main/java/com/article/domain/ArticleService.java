package com.article.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleService {

    @Autowired
    IDAOArticle daoArticle;

    public List<Article> getAllArticle(){
         List<Article> articles= daoArticle.getAll();

        return articles;
    }

    public Article getArticle(String id){
        Article article = daoArticle.getById(id);

        return article;
    }

    public Article saveArticle(Article article){
        Article articlesave = daoArticle.saveArticle(article);

        return articlesave;
    }
    public boolean deleteArticle(String id){
        boolean result = daoArticle.deleteArticle(id);

        return result;
    }
}
