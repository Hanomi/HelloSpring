package io.shifu.project1.services;

import io.shifu.project1.model.Article;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleService {

    void save(Article article);

    Article findBySlug(String slug);

    List<Article> findAll();

    Article findById(Long id);

    List<Article> findAllCutted();

    @Transactional
    void delete(String slug);
}
