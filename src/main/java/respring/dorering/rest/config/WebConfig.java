package respring.dorering.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해
                .allowedOrigins("http://localhost:3000") // 이 출처 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "OPTIONS" , "PATCH") // 허용할 HTTP 메서드 지정
                .maxAge(3600); // preflight 결과를 캐시할 시간(초) 지정
//                .allowCredentials(true)
    }
}
