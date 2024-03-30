package live.smoothing.user.user.dto.request;

import live.smoothing.user.user.entity.User;
import live.smoothing.user.userauth.dto.UserAuthRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequest {

    @NotNull
    @Size(max = 50, message = "최대 50자 까지 입니다")
    private String userId;

    @NotNull
    @Size(min = 10, max = 50, message = "10 ~ 100자 까지 입니다")
    private String userPassword;

    @NotNull
    @Size(max = 30, message = "최대 30자 까지 입니다")
    private String userName;

    @NotNull
    @Size(max = 100, message = "최대 100자 까지 입니다")
    private String userEmail;

    @NotNull
    @Size(min = 1, message = "최소 하나의 권한을 선택해야 합니다")
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
