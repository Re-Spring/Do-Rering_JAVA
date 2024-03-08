package respring.dorering.rest.story.service;

import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    private static final Logger logger = LoggerFactory.getLogger(StoryService.class);

    @Autowired
    private StoryRepository storyRepository;

    public List<Story> getAllStories() {
        try {
            List<Story> stories = storyRepository.findAll();
            return stories;
        } catch (Exception e) {
            logger.error("Error while fetching stories", e); // 로그 출력
            throw e;
        }
    }

    // 장르별로 동화 목록을 조회하는 메서드
    public List<Story> getStoriesByGenre(String genre) {
        return storyRepository.findByFairytaleGenre(genre);
    }

    // 특정 동화 상세 정보를 조회하는 메서드
    public Optional<Story> getStoryByFairytaleFileCode(Integer fairytaleFileCode) {
        return storyRepository.findById(fairytaleFileCode);
    }
}
