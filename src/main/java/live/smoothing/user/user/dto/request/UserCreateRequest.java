package live.smoothing.user.user.dto.request;

import live.smoothing.user.user.entity.User;
import live.smoothing.user.userauth.dto.UserAuthRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequest {

    @NotNull
    private String userId;

    @NotNull
    private String userPassword;

    @NotNull
    private String userName;

    private String userEmail;
    private List<@Valid UserAuthRequest> userAuths;

    public User toEntity(String hashingPw){
        return User.builder()
                .userId(userId)
                .userPassword(hashingPw)
                .userName(userName)
                .userEmail(userEmail)
                .deleteState(false)
                .build();
    }
}
