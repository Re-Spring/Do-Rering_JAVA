package respring.dorering.rest.story.controller;

import org.springframework.web.bind.annotation.*;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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


}
