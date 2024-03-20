package respring.dorering.rest.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;

import java.util.List;
import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    // 장르별로 동화를 조회하는 메서드
    List<Story> findByFairytaleGenre(String fairytaleGenre);

    // 동화 코드를 사용하여 상세 정보를 조회하는 메서드
    @Query("SELECT new respring.dorering.rest.story.dto.StoryDetailDTO(s.fairytaleCode, s.fairytaleTitle, s.fairytaleGenre, s.fairytaleThumb, s.fairytaleSummary, s.user.userId) FROM Story s WHERE s.fairytaleCode = :fairytaleCode")
    Optional<StoryDetailDTO> findStoryDetailsWithUserId(@Param("fairytaleCode") Integer fairytaleCode);

    // user_code와 일치하는 동화 조회
    List<Story> findByUser_UserCode(Integer userCode);
}
