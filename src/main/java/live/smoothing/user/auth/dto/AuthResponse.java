package live.smoothing.user.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private Long authId;
    private String authInfo;

    public AuthResponse(String authInfo) {
        this.authInfo = authInfo;
    }
}
