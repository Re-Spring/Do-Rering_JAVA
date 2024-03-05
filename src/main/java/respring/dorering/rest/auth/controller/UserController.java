package respring.dorering.rest.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import respring.dorering.rest.auth.dto.TokenDTO;
import respring.dorering.rest.auth.dto.UserEnrollDTO;
import respring.dorering.rest.auth.dto.UserIdDTO;
import respring.dorering.rest.auth.exception.CustomException;
import respring.dorering.rest.auth.service.UserService;

@Slf4j
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/enroll")
    public ResponseEntity<UserIdDTO> userEnroll(@RequestBody UserEnrollDTO enrollDTO) {
        log.info("요청과 넘어온 데이터 확인 : "+ enrollDTO);
        return ResponseEntity.ok(userService.userEnroll(enrollDTO));
    }

    // CustomException에 대한 예외 처리 핸들러
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // HTTP 상태 코드 400으로 응답
    public String handleCustomException(CustomException ex) {
        // 예외 메시지를 반환하여 클라이언트에게 전달
        log.info(ex.getMessage());
        return ex.getMessage();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> userLogin(@RequestBody UserEnrollDTO userDTO) {
        log.info("컨트롤러 유저DTO 확인 : {}", userDTO.getUserId());
        log.info("컨트롤러 유저DTO 객체 확인 : {}", userDTO);
        TokenDTO tokenDTO = userService.userLogin(userDTO);

        if(tokenDTO != null) {
                return ResponseEntity.ok(tokenDTO);
        } else {
            log.info(String.valueOf(tokenDTO));
            throw new CustomException("알 수 없는 오류가 발생했습니다.");
        }
    }
}