package respring.dorering.rest.story.service;

import org.springframework.stereotype.Service;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.repository.StoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service // 이 클래스가 스프링 서비스 계층의 컴포넌트임을 나타냅니다.
public class StoryService {
    private static final Logger log = LoggerFactory.getLogger(StoryService.class);

    private final StoryRepository storyRepository; // 스토리 레포지토리 의존성 주입

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public List<Story> getAllStories() {
        return storyRepository.findAll(); // 모든 스토리를 조회합니다.
    }

    public List<Story> getStoriesByGenre(String genre) {
        return storyRepository.findByFairytaleGenre(genre); // 장르별로 스토리를 조회합니다.
    }

    public StoryDetailDTO getStoryDetails(Integer fairytaleCode) {
        // 특정 동화의 상세 정보를 조회합니다. 해당 동화가 없는 경우 예외를 발생시킵니다.
        return storyRepository.findStoryDetailsWithUserId(fairytaleCode)
                .orElseThrow(() -> new RuntimeException("Story not found with code: " + fairytaleCode));
    }

    // userCode를 기반으로 동화 리스트 조회
    public List<Story> getStoriesByUserCode(Integer userCode) {
        return storyRepository.findByUserCode_UserCode(userCode);
    }
}
