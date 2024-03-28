package live.smoothing.user.auth.dto;

import lombok.Getter;

@Getter
public class CreateAuthRequest {
    private String authInfo;

    public CreateAuthRequest(String authInfo) {
        this.authInfo = authInfo;
    }
}
