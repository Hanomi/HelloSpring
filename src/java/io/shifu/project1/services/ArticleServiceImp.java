package io.shifu.project1.services;

import io.shifu.project1.dao.ArticleDao;
import io.shifu.project1.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleServiceImp(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public Article findBySlug(String slug) {
        return articleDao.findBySlug(slug);
    }
}
