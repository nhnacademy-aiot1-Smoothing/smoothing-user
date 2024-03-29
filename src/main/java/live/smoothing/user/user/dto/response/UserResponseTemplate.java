package live.smoothing.user.user.dto.response;

import live.smoothing.user.auth.dto.AuthResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponseTemplate {

    private final Object user;
    private final List<String> auths;

    public UserResponseTemplate(Object user, List<AuthResponse> auths) {
        this.user = user;
        this.auths = auths.stream().map(AuthResponse::getAuthInfo)
                .collect(Collectors.toList());
    }
}
