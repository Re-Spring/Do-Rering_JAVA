package respring.dorering.rest.story.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.repository.StoryRepository;

import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    public List<Story> getStoriesByGenre(String genre) {
        return storyRepository.findByFairytaleGenre(genre);
    }

    public StoryDetailDTO getStoryDetails(Integer fairytaleCode) {
        return storyRepository.findStoryDetailsWithUserId(fairytaleCode)
                .orElseThrow(() -> new RuntimeException("Story not found with code: " + fairytaleCode));
    }
}
