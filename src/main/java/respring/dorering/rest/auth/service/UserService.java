package respring.dorering.rest.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import respring.dorering.rest.auth.dto.TokenDTO;
import respring.dorering.rest.auth.dto.UpdateUserDTO;
import respring.dorering.rest.auth.dto.UserEnrollDTO;
import respring.dorering.rest.auth.dto.UserIdDTO;
import respring.dorering.rest.auth.entity.User;
import respring.dorering.rest.auth.exception.CustomException;
import respring.dorering.rest.auth.jwt.TokenProvider;
import respring.dorering.rest.auth.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
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

        // optionalUser가 존재하는지 (db에 사용자가 있는지) 확인
        if(optionalUser.isPresent()){
            // optional에서 실제 사용자 객체를 가져온다
            User user = optionalUser.get();

            if(user.getWithdrawalStatus() != null && user.getWithdrawalStatus() == "Y") {
                throw new CustomException("탈퇴한 회원입니다");
            }

            if(!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
                throw new CustomException("잘못된 비밀번호입니다");
            }

//            // 사용자 권한 설정 (여기서는 예시로 USER 권한 부여)
//            List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//            log.info("어디까지 오는지 확인1");
//            log.info(grantedAuthorities.toString());
//            // 직접 UserDetails 객체 생성
//            UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), grantedAuthorities);
//            log.info("어디까지 오는지 확인2");
//            log.info(String.valueOf(userDetails));
//            // UserDetails를 사용하여 Authentication 객체 생성
//            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            log.info("어디까지 오는지 확인3");
//            log.info(String.valueOf(authentication));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.info(authentication.getPrincipal().toString());
//            log.info(authentication.getAuthorities().toString());
//            log.info(authentication.getName());
//            log.info("어디까지 오는지 확인4");

            // ID와 패스워드로 인증 토큰 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUserId(), userDTO.getPassword());

            // 인증 과정 수행
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // SecurityContext에 인증 정보 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Token 생성
            return tokenProvider.generateTokenDTO(authentication);
        } else {
            throw new CustomException("해당하는 회원 정보가 존재하지 않습니다");
        }
    }

    public User findUserId(String userName, String phoneNum) {
        try {
            return userRepository.findUserByUserNameAndPhone(userName, phoneNum);
        } catch (Exception e){
            log.error("아이디 찾기 에러 : ", e);
            throw new CustomException("회원 정보를 찾을 수 없습니다");
        }
    }

    public User findPassword(String userName, String userId, String phoneNum) {
        try {
            return userRepository.findUserByUserNameAndUserIdAndPhone(userName, userId, phoneNum);
        } catch (Exception e){
            log.error("비밀번호 찾기 에러 : ", e);
            throw new CustomException("회원 정보를 찾을 수 없습니다");
        }
    }

    public void setNewPassword(String userId, String newPassword) {
        try {
            User user = userRepository.findByUserId(userId).orElseThrow(IllegalArgumentException::new);
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } catch (Exception e){
            log.error("비밀번호 변경 에러 : ", e);
            throw new CustomException("비밀번호 변경 중 오류가 발생했습니다");
        }
    }

    public void updateUser(UpdateUserDTO updateDTO) {
        User user = userRepository.findByUserId(updateDTO.getUserId())
                .orElseThrow(() -> new CustomException("User not found with userId " + updateDTO.getUserId()));
        user.setUserName(updateDTO.getUserName());
        user.setPhone(updateDTO.getPhone());
        userRepository.save(user);
    }


}