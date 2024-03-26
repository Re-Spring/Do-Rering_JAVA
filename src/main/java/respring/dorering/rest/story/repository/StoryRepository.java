package respring.dorering.rest.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;
import java.util.List;
import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    @Query("SELECT s FROM Story s JOIN s.userCode u WHERE u.userRole = 'admin'")
    List<Story> findStoriesByAdminRole();

    @Query("SELECT s FROM Story s JOIN s.userCode u WHERE u.userRole = 'user'")
    List<Story> findStoriesByUserRole();

    @Query("SELECT s FROM Story s JOIN s.userCode u WHERE u.userRole = 'user' AND s.fairytaleGenre = :genre")
    List<Story> findByFairytaleGenreAndUserRole(@Param("genre") String genre);

    List<Story> findAllByOrderByFairytaleCodeDesc();

    // StoryRepository.java 내 수정 필요한 부분
    @Query("SELECT new respring.dorering.rest.story.dto.StoryDetailDTO(s.fairytaleCode, s.fairytaleTitle, s.fairytaleGenre, s.fairytaleThumb, s.fairytaleSummary, u.userId, v.videoFileCode) FROM Story s JOIN s.userCode u LEFT JOIN s.videoInfo v WHERE s.fairytaleCode = :fairytaleCode")
    Optional<StoryDetailDTO> findStoryByFairytaleCode(@Param("fairytaleCode") Integer fairytaleCode);

    @Query("SELECT s FROM Story s WHERE s.userCode.userCode = :userCode")
    List<Story> findStoriesByUserCode(@Param("userCode") Integer userCode);
}