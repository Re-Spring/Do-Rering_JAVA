package respring.dorering.rest.search.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import respring.dorering.rest.search.dto.SearchDTO;
import respring.dorering.rest.search.service.SearchService;

import java.util.List;

// 이 클래스는 검색 요청을 처리하기 위한 컨트롤러입니다.
@Slf4j
@RestController
@RequestMapping("/search")// '/search' 경로로 들어오는 요청을 처리합니다.
public class SearchController {

    private final SearchService searchService;

    // SearchService를 주입받는 생성자입니다.
    // @Autowired 어노테이션은 스프링이 자동으로 의존성을 주입하도록 합니다.
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    // 검색어에 따라 결과를 반환하는 GET 요청을 처리하는 메소드입니다.
    // 예를 들어, "/search?keyword=어린왕자" 형태로 요청이 들어옵니다.
    @GetMapping
    public ResponseEntity<List<SearchDTO>> search(@RequestParam String keyword) {
        // 검색 서비스를 호출하여 검색 결과를 가져옵니다.
        List<SearchDTO> searchResults = searchService.searchByKeyword(keyword);
        log.info(String.valueOf(searchResults));
        // 결과를 HTTP 응답 본문에 담아 반환합니다.
        return ResponseEntity.ok(searchResults);
    }
}