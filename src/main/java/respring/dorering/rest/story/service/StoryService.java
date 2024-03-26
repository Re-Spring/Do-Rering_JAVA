package respring.dorering.rest.story.service;

import org.springframework.stereotype.Service;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.FairytaleVideoInfo;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.repository.StoryRepository;
import respring.dorering.rest.story.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
    private final StoryRepository storyRepository;
    private final VideoRepository videoRepository;

    // 생성자를 통한 의존성 주입
    public StoryService(StoryRepository storyRepository, VideoRepository videoRepository) {
        this.storyRepository = storyRepository;
        this.videoRepository = videoRepository;
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

    // 비디오 파일 코드를 기반으로 비디오 경로 조회
    // 비디오 파일 코드를 기반으로 비디오 경로 조회
    public Optional<String> getVideoPathByFileCode(Integer videoFileCode) {
        return videoRepository.findById(videoFileCode)
                .map(FairytaleVideoInfo::getVideoPath); // 비디오 경로를 조회합니다.
    }

    // videoFileCode를 사용하여 videoPath 조회
    // 비디오 파일 코드를 사용하여 비디오 경로 조회
    public Optional<String> findVideoPathByVideoFileCode(Integer videoFileCode) {
        return videoRepository.findById(videoFileCode)
                .map(FairytaleVideoInfo::getVideoPath);
    }
}
