package live.smoothing.user.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateAuthRequest {
    private String authInfo;

    public CreateAuthRequest(String authInfo) {
        this.authInfo = authInfo;
    }
}
