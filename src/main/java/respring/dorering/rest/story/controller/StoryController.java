package respring.dorering.rest.story.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/stories")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping
    public List<Story> getAllStories() {

        return storyService.getAllStories();
    }

    // 장르별 동화 목록 조회를 위한 엔드포인트
    @GetMapping("/genre/{genre}")
    public List<Story> getStoriesByGenre(@PathVariable String genre) {

        return storyService.getStoriesByGenre(genre);
    }

    @GetMapping("/{fairytaleCode}")
    public ResponseEntity<StoryDetailDTO> getStoryDetails(@PathVariable Integer fairytaleCode) {
        StoryDetailDTO storyDetails = storyService.getStoryDetails(fairytaleCode);
        return ResponseEntity.ok(storyDetails);
    }


}
