package io.shifu.project1.validator;

import io.shifu.project1.model.Story;
import io.shifu.project1.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link Story} class,
 * implements {@link Validator}
 */

@Component
public class StoryValidator implements Validator {

    @Autowired
    private StoryService storyService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Story.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Story story = (Story) o;

        //validate title
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");

        //validate data
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty");

        //validate slug
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slug", "NotEmpty");
        if (storyService.findBySlug(story.getSlug()) != null) {
            errors.rejectValue("slug", "Duplicate.storyForm.slug");
        }
    }
}
