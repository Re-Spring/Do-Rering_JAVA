package respring.dorering.rest.story.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;
import respring.dorering.rest.story.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // 웹 애플리케이션의 CORS 정책을 설정합니다. 여기서는 localhost:3000에서 오는 요청을 허용합니다.
@RestController // 이 클래스를 REST 컨트롤러로 지정합니다. 이 클래스의 메서드들은 HTTP 요청을 처리하게 됩니다.
@RequestMapping("/stories") // 이 컨트롤러의 기본 URL을 '/stories'로 설정합니다. 즉, 이 컨트롤러의 모든 엔드포인트는 '/stories'로 시작하게 됩니다.
public class StoryController {

    @Autowired // 스프링에게 StoryService 객체를 이 클래스에 자동으로 주입하도록 요청합니다.
    private StoryService storyService;

    @GetMapping // HTTP GET 요청을 '/stories' URL로 매핑합니다. 이 메서드는 모든 이야기를 조회하는 역할을 합니다.
    public List<Story> getAllStories() {
        return storyService.getAllStories(); // StoryService를 통해 모든 이야기를 조회하고 결과를 반환합니다.
    }

    @GetMapping("/genre/{genre}") // HTTP GET 요청을 '/stories/genre/{genre}' URL로 매핑합니다. 여기서 {genre}는 변수로, 장르에 따라 다른 결과를 조회할 수 있습니다.
    public List<Story> getStoriesByGenre(@PathVariable String genre) { // @PathVariable을 사용해 URL의 {genre} 부분을 메서드의 인자로 사용합니다.
        return storyService.getStoriesByGenre(genre); // 해당 장르의 이야기를 조회하여 반환합니다.
    }

    @GetMapping("/{fairytaleCode}") // HTTP GET 요청을 '/stories/{fairytaleCode}' URL로 매핑합니다. 여기서 {fairytaleCode}는 동화 코드를 나타내는 변수입니다.
    public ResponseEntity<StoryDetailDTO> getStoryDetails(@PathVariable Integer fairytaleCode) { // @PathVariable을 사용해 URL의 {fairytaleCode} 부분을 메서드의 인자로 사용합니다.
        StoryDetailDTO storyDetails = storyService.getStoryDetails(fairytaleCode); // 해당 동화 코드의 상세 정보를 조회합니다.
        return ResponseEntity.ok(storyDetails); // 조회된 상세 정보를 HTTP 응답 본문에 담아 클라이언트에 반환합니다.
    }

    // 사용자 ID로 해당 사용자가 만든 동화 리스트 조회
    @GetMapping("/usercode/{userCode}")
    public List<Story> getStoriesByUserCode(@PathVariable Integer userCode) {
        return storyService.getStoriesByUserCode(userCode);
    }
}
