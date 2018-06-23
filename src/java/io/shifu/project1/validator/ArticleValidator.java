package io.shifu.project1.validator;

import io.shifu.project1.model.Article;
import io.shifu.project1.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link Article} class,
 * implements {@link Validator}
 */

@Component
public class ArticleValidator implements Validator {

    @Autowired
    private ArticleService articleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Article.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Article article = (Article) o;

        //validate title
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");

        //validate data
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty");

        //validate slug
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slug", "NotEmpty");
        if (articleService.findBySlug(article.getSlug()) != null) {
            errors.rejectValue("slug", "Duplicate.storyForm.slug");
        }
    }
}
