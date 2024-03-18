package respring.dorering.rest.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
        if(userRepository.existsByUserId(enrollDTO.getUserId())){
            throw new CustomException("이미 등록된 아이디입니다.");
        }

        Optional<User> latestWithdrawnUser = userRepository.findLatestWithdrawnByPhone(enrollDTO.getPhone());
        if (latestWithdrawnUser.isPresent()) {
            User withdrawnUser = latestWithdrawnUser.get();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date withdrawalDate = sdf.parse(withdrawnUser.getWithdrawalDate());
                long diffInMillies = Math.abs(new Date().getTime() - withdrawalDate.getTime());
                long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                if (diffInDays < 30) {
                    throw new CustomException("재가입 가능 기간이 아닙니다. 재가입까지 " + (30 - diffInDays) + "일이 남았습니다.");
                }
            } catch (ParseException e) {
                log.error("날짜 파싱 중 오류 발생", e);
                throw new CustomException("날짜 처리 중 오류가 발생했습니다.");
            }
        }

        try {
            User user = User.builder()
                    .userId(enrollDTO.getUserId())
                    .password(passwordEncoder.encode(enrollDTO.getPassword()))
                    .userName(enrollDTO.getUserName())
                    .phone(enrollDTO.getPhone())
                    .enrollDate(new Date())
                    .userRole("user") // 'userRole' 필드에 기본값 "USER"을 명시적으로 설정
                    .withdrawalStatus("N") // 'withdrawalStatus' 필드 설정은 그대로 유지
                    .build();
            userRepository.save(user);
            return new UserIdDTO(user.getUserId());
        } catch (Exception e){
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

    // 예시를 위한 userWithdrawal 메서드
    @Transactional
    public void userWithdrawal(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException("해당하는 회원 정보가 존재하지 않습니다"));
        user.setWithdrawalStatus("Y");
        String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        user.setWithdrawalDate(currentDateTime);
        userRepository.save(user);
    }

    public Optional<User> findUserByStatus(String userId) {
        return userRepository.findByUserId(userId)
                .map(user -> {
                    // 여기서는 추가적인 비즈니스 로직을 적용할 수 있습니다.
                    // 예를 들어, 사용자 상태에 따라 다른 처리를 할 수 있습니다.
                    return user;
                });
    }

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void deleteWithdrawnUsers() {
        userRepository.deleteWithdrawnUsersOlderThanOneMonth();
    }


}