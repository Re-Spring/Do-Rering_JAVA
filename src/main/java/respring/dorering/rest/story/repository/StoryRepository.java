package respring.dorering.rest.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import respring.dorering.rest.story.dto.StoryDetailDTO;
import respring.dorering.rest.story.entity.Story;

import java.util.List;
import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story, Integer> {

    // 'admin' 역할을 가진 사용자의 동화 조회
    @Query("SELECT s FROM Story s JOIN s.userCode u WHERE u.userRole = 'admin'")
    List<Story> findStoriesByAdminRole();

    // 'user' 역할을 가진 사용자의 동화 조회
    @Query("SELECT s FROM Story s JOIN s.userCode u WHERE u.userRole = 'user'")
    List<Story> findStoriesByUserRole();
    // 장르별로 동화를 조회하는 메서드
    List<Story> findByFairytaleGenre(String fairytaleGenre);

    @Query("SELECT s FROM Story s JOIN s.userCode u WHERE u.userRole = 'user' AND s.fairytaleGenre = :genre")
    List<Story> findByFairytaleGenreAndUserRole(@Param("genre") String genre);

    // fairytale_code를 기준으로 역순으로 정렬하여 모든 스토리를 조회
    List<Story> findAllByOrderByFairytaleCodeDesc();



    // 동화 코드를 사용하여 상세 정보를 조회하는 메서드
    @Query("SELECT new respring.dorering.rest.story.dto.StoryDetailDTO(s.fairytaleCode, s.fairytaleTitle, s.fairytaleGenre, s.fairytaleThumb, s.fairytaleSummary, s.userCode.userId) FROM Story s WHERE s.fairytaleCode = :fairytaleCode")
    Optional<StoryDetailDTO> findStoryDetailsWithUserId(@Param("fairytaleCode") Integer fairytaleCode);

    // user_code와 일치하는 동화 조회
    List<Story> findByUserCode_UserCode(Integer userCode);


}
