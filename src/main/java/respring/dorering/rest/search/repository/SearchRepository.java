
package respring.dorering.rest.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 이 어노테이션은 인터페이스가 스프링 데이터 JPA의 Repository임을 나타냅니다.
@Repository
public interface SearchRepository<FairyTale> extends JpaRepository<FairyTale, Long> {

    // 제목에 지정된 키워드를 포함하는 동화 목록을 검색하는 쿼리 메소드입니다.
    // 메소드 이름의 규칙을 따르면, 스프링 데이터 JPA는 적절한 쿼리를 자동으로 생성합니다.
    List<FairyTale> findByTitleContaining(String title);

    // 추후 필요에 따라 더 많은 메서드를 추가할 수 있습니다.
}