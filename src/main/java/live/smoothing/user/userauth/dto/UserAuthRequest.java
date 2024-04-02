package live.smoothing.user.userauth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthRequest {

    @NotNull
    private Long userAuthId;

    public UserAuthRequest(Long userAuthId) {

        this.userAuthId = userAuthId;
    }
}
