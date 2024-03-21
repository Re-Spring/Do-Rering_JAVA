package respring.dorering.rest.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ApiKeyConfiguration {

    @Value("${elevenlabs-api.key}")
    private String elevenKey;

    public String getElevenKey() {
        return elevenKey;
    }
}
