package respring.dorering.rest.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import respring.dorering.rest.story.entity.Story; // Story 엔터티의 정확한 패키지 경로를 사용하세요.

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    }
