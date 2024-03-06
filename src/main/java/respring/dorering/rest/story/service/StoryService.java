package respring.dorering.rest.story.service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.repository.StoryRepository;

import java.util.List;


@Service
public class StoryService {
    @PersistenceContext
    private final EntityManager entityManager;
    private final StoryRepository storyRepository;


    public StoryService(EntityManager entityManager, StoryRepository storyRepository) {
        this.entityManager = entityManager;
        this.storyRepository = storyRepository;
    }

    public List<Story> findAllStory(){
        return storyRepository.findAll();
    }


}
