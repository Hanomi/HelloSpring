package io.shifu.project1.services;

import io.shifu.project1.model.Article;

import java.util.List;

public interface ArticleService {

    void save(Article article);

    Article findBySlug(String slug);

    List<Article> findAll();
}
