package respring.dorering.rest.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import respring.dorering.rest.auth.dto.TokenDTO;
import respring.dorering.rest.auth.dto.UserEnrollDTO;
import respring.dorering.rest.auth.dto.UserIdDTO;
import respring.dorering.rest.auth.entity.User;
import respring.dorering.rest.auth.exception.CustomException;
import respring.dorering.rest.auth.jwt.TokenProvider;
import respring.dorering.rest.auth.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManagerBuilder managerBuilder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public UserIdDTO userEnroll(UserEnrollDTO enrollDTO) {

        // userId에 대한 중복 검사
        if(userRepository.existsByUserId(enrollDTO.getUserId())){
            throw new CustomException("이미 등록된 아이디입니다.");
        }
        // phone에 대한 중복 검사
        if(userRepository.existsByPhone(enrollDTO.getPhone())){
            throw new CustomException("이미 등록된 번호입니다.");
        }
        try {
            // User 객체 생성 및 저장
            User user = enrollDTO.toUser(passwordEncoder);
            return UserIdDTO.of(userRepository.save(user));
        } catch (Exception e){
            // 예외 발생 시 로깅 후 사용자 정의 예외를 던짐
            log.error("가입 에러 : ", e);
            throw new CustomException("가입 시도 중 오류가 발생했습니다");
        }
    }

    public TokenDTO userLogin(UserEnrollDTO userDTO){
        // 사용자 ID로 사용자 정보를 레포지토리에서 조회
        Optional<User> optionalUser = userRepository.findByUserId(userDTO.getUserId());
        log.info(String.valueOf(optionalUser));
        log.info("어디까지 오는지 확인1");
        // optionalUser가 존재하는지 (db에 사용자가 있는지) 확인
        if(optionalUser.isPresent()){
            // optional에서 실제 사용자 객체를 가져온다
            User user = optionalUser.get();
            log.info("어디까지 오는지 확인2");
            if(user.getWithdrawalStatus() != null && user.getWithdrawalStatus() == "Y") {
                throw new CustomException("탈퇴한 회원입니다");
            }

            if(!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
                throw new CustomException("잘못된 비밀번호입니다");
            }
            log.info("어디까지 오는지 확인3");
            // 사용자 DTO를 인증 토큰으로 변환
            UsernamePasswordAuthenticationToken authenticationToken = userDTO.toAuthentication();
            log.info("어디까지 오는지 확인4");
            log.info(String.valueOf(authenticationToken));
            // 인증 토큰을 기반으로 사용자를 인증
//            Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            log.info("어디까지 오는지 확인5");
            return tokenProvider.generateTokenDTO(authentication);
        } else {
            throw new CustomException("해당하는 회원 정보가 존재하지 않습니다");
        }
    }
}