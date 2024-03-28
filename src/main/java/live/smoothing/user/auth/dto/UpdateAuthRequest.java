package live.smoothing.user.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateAuthRequest {
    private String authInfo;

    public UpdateAuthRequest(String authInfo) {
        this.authInfo = authInfo;
    }
}
