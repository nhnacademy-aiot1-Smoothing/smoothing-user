package live.smoothing.user.userauth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthRequest {

    private Long userAuthId;

    public UserAuthRequest(Long userAuthId) {

        this.userAuthId = userAuthId;
    }
}
