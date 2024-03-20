package respring.dorering.rest.admin.service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import respring.dorering.rest.admin.config.ApiKeyConfiguration;
import respring.dorering.rest.admin.dto.VoiceDTO;
import respring.dorering.rest.admin.entity.Voice;
import respring.dorering.rest.admin.repository.AdminRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private ApiKeyConfiguration apiConfiguration;

    public List<VoiceDTO> selectAllExpiredVoices() {
        List<Voice> allExpVoices = adminRepository.findAll();

        allExpVoices.sort(Comparator.comparingInt(Voice::getNo));
        Collections.reverse(allExpVoices);

        return allExpVoices.stream()
                .map(list -> modelMapper.map(list, VoiceDTO.class))
                .collect(Collectors.toList());
    }

    public boolean deleteExpVoiceId(String voiceId) {
        try {
            HttpResponse<String> response = Unirest.delete("https://api.elevenlabs.io/v1/voices/" + voiceId)
                    .header("xi-api-key", apiConfiguration.getElevenKey())
                    .asString();
            if(response.getStatusText().equals("ok")){
                adminRepository.deleteByVoiceId(voiceId);
                return true;
            } else {
                log.info("[AdminService] deleteExpVoiceId error at HttpResponse");
                return false;
            }
        } catch (Exception e) {
            log.error("[AdminService] deleteExpVoiceId error : " + e);
            return false;
        }
    }
}
