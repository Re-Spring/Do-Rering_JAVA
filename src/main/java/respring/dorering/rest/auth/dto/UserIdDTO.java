package respring.dorering.rest.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import respring.dorering.rest.auth.entity.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserIdDTO {
    private String userId;

    public static UserIdDTO of(User user){
        return UserIdDTO.builder()
                .userId(user.getUserId())
                .build();
    }
}
