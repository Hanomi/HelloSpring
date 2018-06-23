package io.shifu.project1.controller;

import io.shifu.project1.model.Article;
import io.shifu.project1.services.ArticleService;
import io.shifu.project1.validator.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for Admin page
 */

@Controller
public class AdminController {

    @Autowired
    private ArticleValidator articleValidator;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("title", "Admin page");
        return "admin/admin";
    }

    @RequestMapping(value = "/admin/articles", method = RequestMethod.GET)
    public String articles(Model model) {
        //load all articles
        model.addAttribute("listArticles", articleService.findAll());

        model.addAttribute("title", "Articles");
        return "admin/articles";
    }

    @RequestMapping(value = "/admin/articles/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("articleForm", new Article());

        model.addAttribute("title", "Add article");
        return "admin/add";
    }

    @RequestMapping(value = "/admin/articles/add", method = RequestMethod.POST)
    public String addSave(@ModelAttribute("articleForm") Article articleForm, BindingResult bindingResult, Model model) {
        articleValidator.validate(articleForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/admin";
        }

        articleService.save(articleForm);

        model.addAttribute("message", "Article: \"" + articleForm.getTitle() + "\" added, slug: " + articleForm.getSlug());
        model.addAttribute("articleForm", new Article());

        model.addAttribute("title", "Add article");
        return "admin/add";
    }
}
