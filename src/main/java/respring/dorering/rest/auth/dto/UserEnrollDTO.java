package respring.dorering.rest.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import respring.dorering.rest.auth.entity.User;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEnrollDTO {

    private Long userCode;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String userId;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String password;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String userName;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String phone;

    private Date enrollDate;

    private String userRole;

    private String userVoiceId;

    private String withdrawalStatus;

    private String withdrawalDate;

    public User toUser(PasswordEncoder passwordEncoder){
        return User.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phone(phone)
                .enrollDate(new Date())
                .userRole("user")
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(userId, password);
    }
}