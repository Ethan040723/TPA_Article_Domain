package com.article.domain;

import java.util.List;

public interface IDAOArticle {

    public List<Article> getAll();
    public Article getById(String id);
    public Article saveArticle(Article newarticle);
    public boolean deleteArticle(String id);
}
