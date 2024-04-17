package live.smoothing.user.userrole.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoleModifyRequest {

    private String userId;
    private Long roleId;

    public UserRoleModifyRequest(String userId, Long roleId) {

        this.userId = userId;
        this.roleId = roleId;
    }
}
