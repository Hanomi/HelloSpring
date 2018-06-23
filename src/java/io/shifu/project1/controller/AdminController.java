package io.shifu.project1.controller;

import io.shifu.project1.model.Story;
import io.shifu.project1.services.StoryService;
import io.shifu.project1.validator.StoryValidator;
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
    private StoryValidator storyValidator;

    @Autowired
    private StoryService storyService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("storyForm", new Story());

        model.addAttribute("title", "Admin page");
        return "admin/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String registration(@ModelAttribute("storyForm") Story storyForm, BindingResult bindingResult, Model model) {
        storyValidator.validate(storyForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/admin";
        }

        storyService.save(storyForm);

        model.addAttribute("message", "Story: \"" + storyForm.getTitle() + "\" added, slug: " + storyForm.getSlug());
        model.addAttribute("storyForm", new Story());

        model.addAttribute("title", "Admin page");
        return "admin/admin";
    }
}
