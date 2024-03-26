package respring.dorering.rest.search.service;

import lombok.extern.slf4j.Slf4j;
import respring.dorering.rest.search.dto.SearchDTO;
import respring.dorering.rest.search.entity.FairyTale;
import respring.dorering.rest.search.repository.SearchRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 클래스를 서비스 계층의 컴포넌트로 선언하며, 스프링 컨테이너가 이 클래스의 인스턴스를 빈으로 관리합니다.
public class SearchService {

    private final SearchRepository searchRepository; // SearchRepository의 인스턴스를 저장하기 위한 final 필드 선언

    // 스프링의 의존성 주입을 통해 SearchRepository의 인스턴스를 생성자로 주입받습니다.
    // @Autowired 어노테이션은 단일 생성자에서는 생략 가능하지만, 명시적으로 의존성 주입을 나타내기 위해 사용됩니다.
    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    // 주어진 키워드로 제목을 검색하고, 검색 결과를 DTO로 변환하여 반환하는 메서드입니다.
    public List<SearchDTO> searchByKeyword(String keyword) {
        // 제목에 키워드를 포함하는 모든 동화를 검색합니다.
        List<FairyTale> fairyTales = searchRepository.findByTitleContaining(keyword);

        // 검색 결과를 DTO 리스트로 변환하여 반환합니다.
        return fairyTales.stream()
                .map(fairyTale -> new SearchDTO(
                        fairyTale.getTitle(),
                        fairyTale.getThumbnail(),
                        fairyTale.getSummary(),
                        fairyTale.getFairytaleCode()))

                .collect(Collectors.toList());
    }

    // 추가적인 비즈니스 로직이 필요할 경우, 여기에 메서드를 구현할 수 있습니다.
}