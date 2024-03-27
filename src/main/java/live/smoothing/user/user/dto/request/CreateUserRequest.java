package live.smoothing.user.user.dto.request;

import live.smoothing.user.userauth.dto.UserAuthRequest;
import live.smoothing.user.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserRequest {

    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private List<UserAuthRequest> userAuths;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .userEmail(userEmail)
                .build();
    }
}
