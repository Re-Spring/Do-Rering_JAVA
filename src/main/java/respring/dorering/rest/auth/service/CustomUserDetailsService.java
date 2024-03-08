package respring.dorering.rest.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import respring.dorering.rest.auth.entity.User;
import respring.dorering.rest.auth.jwt.CustomUserDetails;
import respring.dorering.rest.auth.repository.UserRepository;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 사용자 이름(username)으로 사용자 정보 조회 로직 구현
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getUserRole().toString());

        return new CustomUserDetails(
                String.valueOf(user.getUserCode()),
                user.getUserName(),
                user.getUserId(),
                user.getUserVoiceId(),
                user.getUserRole(),
                user.getPassword(),
                user.getPhone(),
                user.getEnrollDate(),
                Collections.singleton(grantedAuthority)
        );
    }

//    private UserDetails createUserDetails(User user) {
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getUserRole().toString());
//
//        return new CustomUserDetails(
//                String.valueOf(user.getUserCode()),
//                user.getUserName(),
//                user.getUserId(),
//                user.getUserVoiceId(),
//                user.getUserRole(),
//                user.getPassword(),
//                Collections.singleton(grantedAuthority)
//        );
//    }
}
