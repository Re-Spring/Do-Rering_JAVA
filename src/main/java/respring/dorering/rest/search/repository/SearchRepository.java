package respring.dorering.rest.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import respring.dorering.rest.search.entity.FairyTale; // FairyTale 엔티티 클래스를 임포트합니다.

import java.util.List;

// @Repository 어노테이션은 이 인터페이스가 스프링의 리포지토리로서 기능하게 하며,
// 스프링 컨테이너에 의해 관리되는 빈(bean)임을 나타냅니다.
@Repository
public interface SearchRepository extends JpaRepository<FairyTale, Long> {

    // findByTitleContaining 메서드는 동화 제목에 특정 키워드가 포함된 모든 엔티티를 찾기 위한 쿼리 메서드입니다.
    // 스프링 데이터 JPA는 메서드 이름을 분석하여, 'title' 필드에 'title' 매개변수 값이 포함된 엔티티를 검색하는 쿼리를 자동으로 생성합니다.
    List<FairyTale> findByTitleContaining(String title);


    // 필요에 따라 추가적인 쿼리 메소드를 정의할 수 있으며, 스프링 데이터 JPA는 메서드 이름 규칙에 따라 적절한 쿼리를 자동으로 생성합니다.
}