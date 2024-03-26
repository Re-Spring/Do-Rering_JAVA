package respring.dorering.rest.story.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.service.StoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/stories")
public class StoryController {
    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping
    public List<Story> getAllStories() {
        return storyService.getAllStories();
    }

    @GetMapping("/role/admin")
    public List<Story> getStoriesByAdminRole() {
        return storyService.getStoriesByAdminRole();
    }

    @GetMapping("/role/user")
    public List<Story> getStoriesByUserRole() {
        return storyService.getStoriesByUserRole();
    }

    @GetMapping("/genre/{genre}")
    public List<Story> getStoriesByGenre(@PathVariable String genre) {
        return storyService.getStoriesByGenreAndUserRole(genre);
    }

    @GetMapping("/detail/{fairytaleCode}")
    public ResponseEntity<?> getStoryDetailByFairytaleCode(@PathVariable Integer fairytaleCode) {
        Optional<StoryDetailDTO> storyDetail = storyService.getStoryDetailByFairytaleCode(fairytaleCode);

        return storyDetail
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usercode/{userCode}")
    public ResponseEntity<List<Story>> getStoriesByUserCode(@PathVariable Integer userCode) {
        List<Story> stories = storyService.getStoriesByUserCode(userCode);
        if (stories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stories);
    }

    // 비디오 경로 조회 엔드포인트
    @GetMapping("/video/{videoFileCode}")
    public ResponseEntity<?> getVideoPathByVideoFileCode(@PathVariable Integer videoFileCode) {
        return storyService.findVideoPathByVideoFileCode(videoFileCode)
                .map(path -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("videoPath", path);
                    return ResponseEntity.ok().body(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}