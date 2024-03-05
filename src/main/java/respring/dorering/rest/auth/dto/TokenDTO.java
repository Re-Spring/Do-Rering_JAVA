package respring.dorering.rest.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {
    private String userId;
    private String userName;
    private String userRole;
    private String userVoiceId;
    private String grantType;
    private String accessToken;
    private Long tokenExpiresIn;
}
