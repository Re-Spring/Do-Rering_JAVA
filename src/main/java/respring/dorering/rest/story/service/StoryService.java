package respring.dorering.rest.story.service;

import org.springframework.stereotype.Service;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.repository.StoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
    private final StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public List<Story> getAllStories() {
        return storyRepository.findAllByOrderByFairytaleCodeDesc();
    }

    public List<Story> getStoriesByAdminRole() {
        return storyRepository.findStoriesByAdminRole();
    }

    public List<Story> getStoriesByUserRole() {
        return storyRepository.findStoriesByUserRole();
    }

    public List<Story> getStoriesByGenreAndUserRole(String genre) {
        return storyRepository.findByFairytaleGenreAndUserRole(genre);
    }

    public Optional<StoryDetailDTO> getStoryDetailByFairytaleCode(Integer fairytaleCode) {
        return storyRepository.findStoryByFairytaleCode(fairytaleCode);
    }

    public List<Story> getStoriesByUserCode(Integer userCode) {
        return storyRepository.findStoriesByUserCode(userCode);
    }
}
