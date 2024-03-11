package respring.dorering.rest.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import respring.dorering.rest.auth.dto.TokenDTO;
import respring.dorering.rest.auth.dto.UserEnrollDTO;
import respring.dorering.rest.auth.dto.UserIdDTO;
import respring.dorering.rest.auth.entity.User;
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
        log.info("토큰 확인 : {}", tokenDTO);

        if(tokenDTO != null) {
                return ResponseEntity.ok(tokenDTO);
        } else {
            log.info(String.valueOf(tokenDTO));
            throw new CustomException("로그인 시도 중 오류가 발생했습니다");
        }
    }

    @GetMapping("/findId/{userName}/{phoneNum}")
    public ResponseEntity<String> findUserId(@PathVariable String userName, @PathVariable String phoneNum) {
        User user = userService.findUserId(userName, phoneNum);
        if(user != null && user.getUserId() != null) {
            log.info(String.valueOf(user));
            return ResponseEntity.ok(user.getUserId());
        } else {
            throw new CustomException("회원 정보를 찾을 수 없습니다");
        }
    }

    @GetMapping("/findPw/{userName}/{userId}/{phoneNum}")
    public ResponseEntity<?> findPassword(@PathVariable String userName, @PathVariable String userId, @PathVariable String phoneNum) {
        User user = userService.findPassword(userName, userId, phoneNum);
        if(user != null && user.getUserId() != null) {
            return ResponseEntity.status(HttpStatus.OK).body("user 확인");
        } else {
            throw new CustomException("회원 정보를 찾을 수 없습니다");
        }
    }

    @PostMapping("/setPwd")
    public ResponseEntity<?> setNewPassword(@RequestParam String userId, @RequestParam String newPassword) {
        log.info(userId, newPassword);
        try {
            userService.setNewPassword(userId, newPassword);
            return ResponseEntity.ok("password 변경 완료");
        } catch (Exception e) {
            log.info("비밀번호 찾기 controller 오류 : " + e);
            throw new CustomException("비밀번호 변경 중 오류가 발생했습니다");
        }
    }
}