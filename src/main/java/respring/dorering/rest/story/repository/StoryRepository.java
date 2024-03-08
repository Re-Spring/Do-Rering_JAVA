// StoryRepository.java

package respring.dorering.rest.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import respring.dorering.rest.story.entity.Story;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    // 장르에 따라 동화 목록을 찾는 메서드
    List<Story> findByFairytaleGenre(String fairytaleGenre);

}
