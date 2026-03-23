package com.article;

import com.article.domain.Article;
import com.article.domain.IDAOArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@Profile("jpa")
public class DAOArticleJpa implements IDAOArticle {

    @Autowired
    private ArticleJpaRepository articleJpaRepository;

    @Override
    public List<Article> getAll(){
        List<ArticleJpa> Jpa = articleJpaRepository.findAll();
        List<Article> articles = new ArrayList<Article>();

        for (ArticleJpa articlesJpa : Jpa) {
            Article article = new Article();
            article.id = articlesJpa.id;
            article.title = articlesJpa.title;
            article.desc = articlesJpa.desc;
            articles.add(article);
        }
        return articles;
    }

    @Override
    public Article getById(String id){
        ArticleJpa articleJpa = articleJpaRepository.findById(id).orElse(null);

        if (articleJpa == null) {
            return null;
        }
        else {
            Article article = new Article();
            article.id = articleJpa.id;
            article.title = articleJpa.title;
            article.desc = articleJpa.desc;

            return article;
        }
    }



    @Override
    public Article saveArticle(Article article) {
        List<ArticleJpa> Jpa = articleJpaRepository.findAll();

        if (article.id == null){
            String articleId = UUID.randomUUID().toString();
            for (ArticleJpa articleJpa : Jpa){
                if (Objects.equals(article.title, articleJpa.title)){
                    return null;
                }
            }
            ArticleJpa newArticleJpa = new ArticleJpa();
            newArticleJpa.id = articleId;
            newArticleJpa.title = article.title;
            newArticleJpa.desc = article.desc;
            articleJpaRepository.save(newArticleJpa);

            Article articleCreated = new Article();
            articleCreated.id = newArticleJpa.id;
            articleCreated.title = newArticleJpa.title;
            articleCreated.desc = newArticleJpa.desc;

            return articleCreated;
        }
        else {
            ArticleJpa articleJpa = articleJpaRepository.findById(article.id).orElse(null);
            for (ArticleJpa articlesJpa : Jpa){
                if (Objects.equals(article.title, articlesJpa.title)){
                    return null;
                }
            }

            articleJpa.title = article.title;
            articleJpa.desc = article.desc;

            articleJpaRepository.save(articleJpa);

            return article;
        }
    }

    @Override
    public boolean deleteArticle(String id) {
        ArticleJpa articleJpa = articleJpaRepository.findById(id).orElse(null);

        if (articleJpa == null) {
            return false;
        } else {
            articleJpaRepository.deleteById(id);
            return true;
        }
    }

}
