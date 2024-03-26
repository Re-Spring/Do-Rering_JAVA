package respring.dorering.rest.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import respring.dorering.rest.story.entity.FairytaleVideoInfo;

public interface VideoRepository extends JpaRepository<FairytaleVideoInfo, Integer> {
    // 필요한 추가 쿼리 메서드를 여기에 정의할 수 있습니다.
}
