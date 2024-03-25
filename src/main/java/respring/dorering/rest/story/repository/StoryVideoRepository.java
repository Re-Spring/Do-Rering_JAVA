package respring.dorering.rest.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import respring.dorering.rest.story.entity.FairytaleVideoInfo;

import java.util.Optional;

public interface StoryVideoRepository extends JpaRepository<FairytaleVideoInfo, Integer> {
    @Query("SELECT sv FROM FairytaleVideoInfo sv WHERE sv.story.fairytaleCode = :fairytaleCode")
    Optional<FairytaleVideoInfo> findByStory_FairytaleCode(@Param("fairytaleCode") Integer fairytaleCode);
}