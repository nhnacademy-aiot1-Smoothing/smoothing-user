package live.smoothing.user.userrole.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoleCreateRequest {

    private String userId;
    private Long roleId;

    public UserRoleCreateRequest(String userId, Long roleId) {

        this.userId = userId;
        this.roleId = roleId;
    }
}
