package respring.dorering.rest.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import respring.dorering.rest.story.entity.Story;

public interface StoryRepository extends JpaRepository<Story, Integer> {
}
