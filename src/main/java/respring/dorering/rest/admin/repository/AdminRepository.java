package respring.dorering.rest.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import respring.dorering.rest.admin.entity.Voice;

public interface AdminRepository extends JpaRepository<Voice, Integer> {
    void deleteByVoiceId(String voiceId);
}
