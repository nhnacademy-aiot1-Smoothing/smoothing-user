package live.smoothing.user.user.dto.response;

import live.smoothing.user.user.entity.UserState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserDetailResponse {

    private final String userId;
    private final String userPassword;
    private final String userName;
    private final String userEmail;
    private final UserState userState;
    private final Long userPoint;
    private final LocalDateTime lastAccess;

}
