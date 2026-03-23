package com.article.app;

import com.article.domain.Article;
import com.article.domain.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleRestController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/articles")
    public ArticleReponse<List<Article>> articles() {
        List<Article> articles = articleService.getAllArticle();
        ArticleReponse<List<Article>> reponse = new ArticleReponse<>("2002", "La liste des articles a été récupérée avec succès ", articles);
        return reponse;
    }

    @GetMapping("/articles/{id}")
    public ArticleReponse<Article> getId(@PathVariable String id) {

        Article article = articleService.getArticle(id);
        if (article == null) {
            ArticleReponse<Article> response = new ArticleReponse<>(
                    "7001",
                    "L'article n'existe pas",
                    article);
            return response;
        } else {
            ArticleReponse<Article> response = new ArticleReponse<>(
                    "2002",
                    "L'article a été récupéré avec succès",
                    article);
            return response;
        }

    }

    @PostMapping("/articles/save")
    public ArticleReponse<Article> saveArticle(@RequestBody Article article) {
        String findId = article.id;
        Article articlesave = articleService.saveArticle(article);
        if (articlesave == null) {
            ArticleReponse<Article> response = new ArticleReponse<>(
                    "7006",
                    "Le titre est déjà utilisé",
                    articlesave
            );

            return response;

        } else if (findId == null) {
            ArticleReponse<Article> response = new ArticleReponse<>(
                    "2002",
                    "Article créé avec succès",
                    articlesave
            );

            return response;
        } else {
            ArticleReponse<Article> response = new ArticleReponse<>(
                    "2003",
                    "Article modifié avec succès",
                    articlesave
            );

            return response;
        }

    }
    @DeleteMapping("/articles/{id}")
    public ArticleReponse<Boolean> deleteArticle(@PathVariable String id){
        boolean result = articleService.deleteArticle(id);

        if (result == false){
            ArticleReponse<Boolean> response = new ArticleReponse<>(
                    "7001",
                    "L'article n'existe pas",
                    false
            );

            return response;
        }

        else {
            ArticleReponse<Boolean> response = new ArticleReponse<>(
                    "2002",
                    "L'article a été supprimé avec succès",
                    true
            );
            return response;
        }
    }

}
