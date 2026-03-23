package com.article.mock;

import com.article.domain.IDAOArticle;
import com.article.domain.Article;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Profile("mock")
public class DAOArticleMock implements IDAOArticle {

    private final List<Article> articles = new ArrayList<>();

    public DAOArticleMock() {
        Article a1 = new Article();
        a1.id = "1";
        a1.title = "First Article";
        a1.desc = "desc premeier Article";

        Article a2 = new Article();
        a2.id = "2";
        a2.title = "Second Article";
        a2.desc = "desc deuxième Article";

        articles.add(a1);
        articles.add(a2);
    }

    @Override
    public List<Article> getAll() {
        return new ArrayList<>(articles);
    }

    @Override
    public Article getById(String id){
        return articles.stream()
                .filter(a -> a.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Article saveArticle(Article newarticle){
        boolean titre = articles.stream()
                .anyMatch(a -> a.title.equals(newarticle.title) && !a.id.equals(newarticle.id));

        if (titre) {
            return null;
        }

        if (newarticle.id == null) {
            newarticle.id = UUID.randomUUID().toString();
            articles.add(newarticle);
            return newarticle;
        } else {
            Article articlesave = getById(newarticle.id);
            if (articlesave != null) {
                articlesave.title = newarticle.title;
                articlesave.desc = newarticle.desc;
                return articlesave;
            }
            return null;
        }
    }
    @Override
    public boolean deleteArticle(String id) {
        return articles.removeIf(a -> a.id.equals(id));
    }
}

