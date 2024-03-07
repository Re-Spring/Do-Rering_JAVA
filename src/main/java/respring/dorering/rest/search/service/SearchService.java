package respring.dorering.rest.search.service;

import respring.dorering.rest.search.dto.SearchDTO;
import respring.dorering.rest.search.entity.FairyTale;
import respring.dorering.rest.search.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SearchService는 검색 기능에 대한 비즈니스 로직을 담당하는 서비스 계층의 클래스입니다.
 * 클라이언트의 요청을 받아 처리하고, 검색 결과를 SearchDTO 리스트로 변환하여 반환합니다.
 */
@Service // 이 어노테이션은 클래스가 서비스 계층의 컴포넌트임을 나타냅니다.
public class SearchService {

    private final SearchRepository searchRepository;

    // 생성자를 통한 FairyTaleRepository의 의존성 주입입니다.
    @Autowired
    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    /**
     * 주어진 키워드를 포함하는 제목의 동화를 검색하고 결과를 SearchDTO 리스트로 변환하여 반환합니다.
     *
     * @param keyword 검색할 키워드입니다.
     * @return 검색된 동화 정보를 담은 SearchDTO 리스트입니다.
     */
    public List<SearchDTO> searchByKeyword(String keyword) {
        // 데이터베이스에서 키워드를 포함하는 동화를 검색합니다.
        List<FairyTale> fairyTales = searchRepository.findByTitleContaining(keyword);

        // 검색된 FairyTale 엔티티 리스트를 SearchDTO 리스트로 변환합니다.
        return fairyTales.stream()
                .map(fairyTale -> new SearchDTO(
                        fairyTale.getTitle(),
                        fairyTale.getThumbnail(),
                        fairyTale.getSummary()))
                .collect(Collectors.toList());
    }

    // 추가적으로 다른 비즈니스 로직을 여기에 구현할 수 있습니다.
}