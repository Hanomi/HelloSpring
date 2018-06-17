package io.shifu.project1.dao;

import io.shifu.project1.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryDao extends JpaRepository<Story, Long> {
    Story findBySlug(String slug);
}
