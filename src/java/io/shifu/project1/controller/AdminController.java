package io.shifu.project1.controller;

import io.shifu.project1.model.Article;
import io.shifu.project1.services.ArticleService;
import io.shifu.project1.validator.ArticleEditValidator;
import io.shifu.project1.validator.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    private ArticleEditValidator articleEditValidator;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("title", "Admin page");
        return "admin/admin";
    }

    @RequestMapping(value = "/admin/articles", method = RequestMethod.GET)
    public String articles(Model model) {
        model.addAttribute("listArticles", articleService.findAll());
        model.addAttribute("title", "Articles");
        return "admin/articles";
    }

    @RequestMapping(value = "/admin/articles/add")
    public String add(Model model) {
        model.addAttribute("articleForm", new Article());
        model.addAttribute("title", "Add article");
        return "admin/add";
    }

    @RequestMapping(value = "/admin/articles/add/save", method = RequestMethod.POST)
    public String addSave(@ModelAttribute("articleForm") Article articleForm, BindingResult bindingResult) {
        articleValidator.validate(articleForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/add";
        }

        articleService.save(articleForm);
        return "redirect:/admin/articles";
    }

    @RequestMapping(value = "/admin/articles/edit/{slug}")
    public String editElement(@PathVariable("slug") String slug, Model model) {
        Article currentArticle = articleService.findBySlug(slug);
        model.addAttribute("articleForm", currentArticle);
        model.addAttribute("title", "Edit: " + currentArticle.getTitle());
        return "admin/edit";
    }

    @RequestMapping(value="/admin/articles/edit/save",method = RequestMethod.POST)
    public String editSave(@ModelAttribute("articleForm") Article article, BindingResult bindingResult){
        articleEditValidator.validate(article, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }

        articleService.save(article);
        return "redirect:/admin/articles";
    }

    @RequestMapping(value = "/admin/articles/delete/{slug}")
    public String delete(@PathVariable("slug") String slug) {
        articleService.delete(slug);
        return "redirect:/admin/articles";
    }

    @RequestMapping(value = "/404")
    public String show() {
        return "errorPages/404";
    }
}
