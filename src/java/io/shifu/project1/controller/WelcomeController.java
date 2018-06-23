package io.shifu.project1.controller;

import io.shifu.project1.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for Welcome page
 */

@Controller
public class WelcomeController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        //load all story
        model.addAttribute("listArticles", articleService.findAll());

        model.addAttribute("title", "Welcome");
        return "welcome";
    }
}
