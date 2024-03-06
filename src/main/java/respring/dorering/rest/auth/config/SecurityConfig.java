package respring.dorering.rest.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import respring.dorering.rest.auth.jwt.JwtAccessDeniedHandler;
import respring.dorering.rest.auth.jwt.JwtAuthenticationEntryPoint;
import respring.dorering.rest.auth.jwt.TokenProvider;
import respring.dorering.rest.auth.jwt.JWTFilter;

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
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler))
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/auth/**", "/**").permitAll()
                                .anyRequest().authenticated())
                .apply(new JwtSecurityConfig(tokenProvider));
//                .addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // AuthenticationManagerBean을 직접 노출시키기 위한 설정
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
