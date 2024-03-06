package respring.dorering.rest.story.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.service.StoryService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping("/fairytales")
    public List<Story> findAllStory() {
        List<Story> storyList = storyService.findAllStory();
        log.info(storyList.toString());

        return storyList;
    }
}
