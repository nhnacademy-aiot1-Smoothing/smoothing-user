package live.smoothing.user.user.dto.response;

import live.smoothing.user.auth.dto.AuthResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class UserResponseTemplate {

    private final Object user;
    private final List<AuthResponse> auths;

    public UserResponseTemplate(Object user, List<AuthResponse> auths) {
        this.user = user;
        this.auths = auths;
    }
}
