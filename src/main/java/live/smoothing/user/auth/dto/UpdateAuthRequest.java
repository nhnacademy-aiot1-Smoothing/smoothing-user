package live.smoothing.user.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAuthRequest {
    private String authInfo;

    public UpdateAuthRequest(String authInfo) {
        this.authInfo = authInfo;
    }
}
