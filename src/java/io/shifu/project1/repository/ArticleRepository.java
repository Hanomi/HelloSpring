package io.shifu.project1.repository;

import io.shifu.project1.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findBySlug(String slug);

    void deleteBySlug(String slug);
}
