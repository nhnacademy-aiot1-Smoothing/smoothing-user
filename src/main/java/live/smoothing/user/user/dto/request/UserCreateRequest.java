package live.smoothing.user.user.dto.request;

import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.entity.UserState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    @Email
    @NotNull
    private String userEmail;

    public User toEntity(String userPassword) {

        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .userEmail(userEmail)
                .userState(UserState.DISAPPROVAL)
                .userPoint(0L)
                .lastAccess(LocalDateTime.now())
                .build();
    }
}
