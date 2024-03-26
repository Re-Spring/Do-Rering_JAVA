package respring.dorering.rest.auth.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import respring.dorering.rest.auth.dto.TokenDTO;
import respring.dorering.rest.auth.dto.UpdateUserDTO;
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

    @Value("${jwt.secret}")
    private String secretKey;

    private final UserService userService;

    @PostMapping("/enroll")
    public ResponseEntity<UserIdDTO> userEnroll(@RequestBody UserEnrollDTO enrollDTO) {
        log.info("[UserController] userEnroll start");
        return ResponseEntity.ok(userService.userEnroll(enrollDTO));
    }

    // CustomException에 대한 예외 처리 핸들러
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // HTTP 상태 코드 400으로 응답
    public String handleCustomException(CustomException ex) {
        // 예외 메시지를 반환하여 클라이언트에게 전달
        return ex.getMessage();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> userLogin(@RequestBody UserEnrollDTO userDTO) {
        log.info("[UserController] userLogin start");
        TokenDTO tokenDTO = userService.userLogin(userDTO);

        if(tokenDTO != null) {
                return ResponseEntity.ok(tokenDTO);
        } else {
            throw new CustomException("로그인 시도 중 오류가 발생했습니다");
        }
    }

    @GetMapping("/findId/{userName}/{phoneNum}")
    public ResponseEntity<String> findUserId(@PathVariable String userName, @PathVariable String phoneNum) {
        log.info("[UserController] findUserId start");
        User user = userService.findUserId(userName, phoneNum);
        if(user != null && user.getUserId() != null) {
            return ResponseEntity.ok(user.getUserId());
        } else {
            throw new CustomException("회원 정보를 찾을 수 없습니다");
        }
    }

    @GetMapping("/findPw/{userName}/{userId}/{phoneNum}")
    public ResponseEntity<?> findPassword(@PathVariable String userName, @PathVariable String userId, @PathVariable String phoneNum) {
        log.info("[UserController] findPassword start");
        User user = userService.findPassword(userName, userId, phoneNum);
        if(user != null && user.getUserId() != null) {
            return ResponseEntity.status(HttpStatus.OK).body("user 확인");
        } else {
            throw new CustomException("회원 정보를 찾을 수 없습니다");
        }
    }

    @PostMapping("/setPwd")
    public ResponseEntity<?> setNewPassword(@RequestParam String userId, @RequestParam String newPassword) {
        log.info("[UserController] setNewPassword start");
        try {
            userService.setNewPassword(userId, newPassword);
            return ResponseEntity.ok("password 변경 완료");
        } catch (Exception e) {
            throw new CustomException("비밀번호 변경 중 오류가 발생했습니다");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        log.info("[UserController] updateUser start");
        userService.updateUser(updateUserDTO);
        return ResponseEntity.ok("User information updated successfully");
    }

    @DeleteMapping("/withdrawal/{userId}")
    public ResponseEntity<?> userWithdrawal(@PathVariable String userId) {
        log.info("[UserController] userWithdrawal start");
        userService.userWithdrawal(userId);
        return ResponseEntity.ok("회원 탈퇴 처리가 완료되었습니다.");
    }

    @GetMapping("/status/{userId}")
    public ResponseEntity<?> checkUserStatus(@PathVariable String userId) {
        log.info("[UserController] checkUserStatus start");
        return userService.findUserByStatus(userId)
                .map(user -> ResponseEntity.ok().body(user.getWithdrawalStatus()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

}