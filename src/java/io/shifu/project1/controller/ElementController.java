package io.shifu.project1.controller;

import io.shifu.project1.model.Article;
import io.shifu.project1.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for Element page
 */


@Controller
@RequestMapping("element")
public class ElementController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/{slug}", method = RequestMethod.GET)
    public String welcome(Model model, @PathVariable("slug") String slug) {
        //load story by slug
        Article currentArticle = articleService.findBySlug(slug);
        model.addAttribute("currentArticle", currentArticle);

        model.addAttribute("title", currentArticle.getTitle());
        return "element";
    }
}
