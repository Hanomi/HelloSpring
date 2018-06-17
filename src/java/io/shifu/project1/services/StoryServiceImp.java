package io.shifu.project1.services;

import io.shifu.project1.dao.StoryDao;
import io.shifu.project1.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryServiceImp implements StoryService {

    private final StoryDao storyDao;

    @Autowired
    public StoryServiceImp(StoryDao storyDao) {
        this.storyDao = storyDao;
    }

    @Override
    public void save(Story story) {
        storyDao.save(story);
    }

    @Override
    public Story findBySlug(String slug) {
        return storyDao.findBySlug(slug);
    }
}
