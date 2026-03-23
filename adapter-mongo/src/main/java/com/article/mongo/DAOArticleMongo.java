package com.article.mongo;

import com.article.domain.IDAOArticle;
import com.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@Profile("mongo")
public class DAOArticleMongo implements IDAOArticle {

    @Autowired
    ArticleMongoRepository articleMongoRepository;


    @Override
    public List<Article> getAll(){
        List<ArticleMongo> articlesMongo = articleMongoRepository.findAll();
        List<Article> articles = new ArrayList<Article>();

        for (ArticleMongo articleMongo : articlesMongo) {
            Article article = new Article();
            article.id = articleMongo.id;
            article.title = articleMongo.title;
            article.desc = articleMongo.desc;
            articles.add(article);
        }
        return articles;
    }


    @Override
    public Article getById(String id){
        ArticleMongo articleMongo = articleMongoRepository.findById(id).orElse(null);

        if (articleMongo == null) {
            return null;
        }
        else {
            Article article = new Article();
            article.id = articleMongo.id;
            article.title = articleMongo.title;
            article.desc = articleMongo.desc;

            return article;
        }
    }


    @Override
    public Article saveArticle(Article article) {
        List<ArticleMongo> Mongo = articleMongoRepository.findAll();

        if (article.id == null){
            String articleId = UUID.randomUUID().toString();
            for (ArticleMongo articlesMongo : Mongo){
                if (Objects.equals(article.title, articlesMongo.title)){
                    return null;
                }
            }
            ArticleMongo newArticleMongo = new ArticleMongo();
            newArticleMongo.id = articleId;
            newArticleMongo.title = article.title;
            newArticleMongo.desc = article.desc;
            articleMongoRepository.save(newArticleMongo);

            Article articlereponse = new Article();
            articlereponse.id = newArticleMongo.id;
            articlereponse.title = newArticleMongo.title;
            articlereponse.desc = newArticleMongo.desc;

            return articlereponse;
        }
        else {
            ArticleMongo articleMongo = articleMongoRepository.findById(article.id).orElse(null);
            for (ArticleMongo articlesMongo : Mongo){
                if (Objects.equals(article.title, articlesMongo.title)){
                    return null;
                }

            }

            articleMongo.title = article.title;
            articleMongo.desc = article.desc;

            articleMongoRepository.save(articleMongo);

            return article;
        }
    }

    @Override
    public boolean deleteArticle(String id) {
        ArticleMongo articleMongo = articleMongoRepository.findById(id).orElse(null);

        if (articleMongo == null) {
            return false;
        } else {
            articleMongoRepository.deleteById(id);
            return true;
        }
    }
}
