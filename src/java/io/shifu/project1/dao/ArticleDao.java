package io.shifu.project1.dao;

import io.shifu.project1.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article, Long> {
    Article findBySlug(String slug);
}
