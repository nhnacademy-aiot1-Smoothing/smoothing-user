package live.smoothing.user.user.dto.response;

import live.smoothing.user.role.dto.RoleResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponseTemplate<T> {

    private final T user;
    private final List<String> roles;

    public UserResponseTemplate(T user, List<RoleResponse> auths) {
        this.user = user;
        this.roles = auths.stream().map(RoleResponse::getRoleInfo)
                .collect(Collectors.toList());
    }
}
