package io.shifu.project1.services;

import io.shifu.project1.model.Story;

import java.util.List;

public interface StoryService {

    void save(Story story);

    Story findBySlug(String slug);

    List<Story> findAll();
}
