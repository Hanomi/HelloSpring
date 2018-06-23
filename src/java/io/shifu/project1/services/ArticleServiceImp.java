package io.shifu.project1.services;

import io.shifu.project1.repository.ArticleRepository;
import io.shifu.project1.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImp(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findBySlug(String slug) {
        return articleRepository.findBySlug(slug);
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public void delete(String slug) {
        articleRepository.deleteBySlug(slug);
    }
}
