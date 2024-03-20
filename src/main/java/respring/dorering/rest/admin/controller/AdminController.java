package respring.dorering.rest.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import respring.dorering.rest.admin.dto.VoiceDTO;
import respring.dorering.rest.admin.service.AdminService;
import respring.dorering.rest.auth.exception.CustomException;

import java.util.List;

@Slf4j
@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/expVoice")
    public ResponseEntity<List<VoiceDTO>> selectAllExpiredVoices(){

        try {
            List<VoiceDTO> allExpVoices = adminService.selectAllExpiredVoices();
            log.info("[AdminController] selectAllExpiredVoices allExpVoices : " + allExpVoices);
            return ResponseEntity.ok(allExpVoices);
        } catch (Exception e) {
            log.error("[AdminController] selectAllExpiredVoices error : " + e);
            throw new CustomException("An error occurs at AdminController selectAllExpiredVoices");
        }
    }

    @DeleteMapping("/deleteVoice/{voiceId}")
    public ResponseEntity<?> deleteExpVoiceId(@PathVariable String voiceId){
        boolean isSuccess = adminService.deleteExpVoiceId(voiceId);
        if(isSuccess) {
            log.info("[AdminController] deleteExpVoiceId : Voice ID successfully deleted.");
            return ResponseEntity.ok().body("Voice ID successfully deleted.");
        } else {
            throw new CustomException("An error occurs at AdminController deleteExpVoiceId");
        }
    }
}
