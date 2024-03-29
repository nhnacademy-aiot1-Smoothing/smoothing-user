package live.smoothing.user.user.dto.response;

import live.smoothing.user.auth.dto.AuthResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponseTemplate<T> {

    private final T user;
    private final List<String> auths;

    public UserResponseTemplate(T user, List<AuthResponse> auths) {
        this.user = user;
        this.auths = auths.stream().map(AuthResponse::getAuthInfo)
                .collect(Collectors.toList());
    }
}
