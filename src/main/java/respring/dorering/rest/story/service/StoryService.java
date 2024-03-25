package respring.dorering.rest.story.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.FairytaleVideoInfo;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.repository.FairytaleVideoInfoRepository;
import respring.dorering.rest.story.repository.StoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
    private final StoryRepository storyRepository;
    private final FairytaleVideoInfoRepository videoInfoRepository;

    public StoryService(StoryRepository storyRepository, FairytaleVideoInfoRepository videoInfoRepository) {
        this.storyRepository = storyRepository;
        this.videoInfoRepository = videoInfoRepository;}

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

    // 동화 생성 로직에 비디오 정보 처리 추가
    @Transactional
    public Story createStoryWithVideo(Story story, String videoPath) {
        // Story 엔티티 저장 (처음에는 videoInfo 없이 저장)
        Story savedStory = storyRepository.save(story);

        // 비디오 정보 생성 및 저장
        FairytaleVideoInfo videoInfo = new FairytaleVideoInfo();
        // videoInfo.setVideoFileCode(); // 필요하다면 여기서 설정
        videoInfo.setVideoPath(videoPath);
        videoInfo.setStory(savedStory); // Story 엔티티와의 연결 설정
        FairytaleVideoInfo savedVideoInfo = videoInfoRepository.save(videoInfo);

        // 저장된 비디오 정보를 Story 엔티티에 설정하고 업데이트
        savedStory.setVideoInfo(savedVideoInfo);
        return storyRepository.save(savedStory); // 변경된 사항을 다시 저장
    }
}
