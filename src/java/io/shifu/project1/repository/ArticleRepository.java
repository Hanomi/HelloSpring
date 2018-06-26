package io.shifu.project1.repository;

import io.shifu.project1.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findBySlug(String slug);

    void deleteBySlug(String slug);

    //MySQL 8
    @Query(value = "select id, title, LEFT(content, 100) as content, slug from articles", nativeQuery = true)
    List<Article> findAllCutted();
}
