package respring.dorering.rest.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import respring.dorering.rest.auth.jwt.JwtAccessDeniedHandler;
import respring.dorering.rest.auth.jwt.JwtAuthenticationEntryPoint;
import respring.dorering.rest.auth.jwt.TokenProvider;

// 스프링 설정 클래스
@RequiredArgsConstructor
@EnableWebSecurity
@Component
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider tokenProvider;


    // 스프링 빈으로 등록하기 위한 어노테이션
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder의 인스턴스를 생성하여 반환
        // 이 객체를 PasswordEncoder 타입의 빈으로 애플리케이션 컨텍스트에 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // 기본적인 HTTP 로그인 방식을 비활성화
                .csrf().disable() // CSRF(크로스 사이트 요청 위조) 보호 기능을 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않고, 상태를 유지하지 않는 정책을 사용

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증 실패 시 처리를 위한 진입점
                .accessDeniedHandler(jwtAccessDeniedHandler) // 접근 거부 처리 핸들러

                .and()
                .authorizeRequests()
                .requestMatchers("/auth/**","/**").permitAll() // "/auth/**"와 "/**" 경로에 대한 요청은 모두 허용
                .anyRequest().authenticated() // 그 외의 모든 요청은 인증을 요구

                .and()
                .apply(new JwtSecurityConfig(tokenProvider)); // JWT를 사용한 인증 방식 적용

        return http.build();
    }
}
