// StoryRepository.java

package respring.dorering.rest.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;

import java.util.List;
import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    // 장르에 따라 동화 목록을 찾는 메서드
    List<Story> findByFairytaleGenre(String fairytaleGenre);

    @Query("SELECT new respring.dorering.rest.story.dto.StoryDetailDTO(s.fairytaleCode, s.fairytaleTitle, s.fairytaleGenre, s.fairytaleThumb, s.fairytaleSummary, s.userCode.userId) FROM Story s WHERE s.fairytaleCode = :fairytaleCode")
    Optional<StoryDetailDTO> findStoryDetailsWithUserId(@Param("fairytaleCode") Integer fairytaleCode);



}
