package live.smoothing.user.userrole.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoleCreateRequest {

    private String userId;
    private List<Long> roleIds;


    public UserRoleCreateRequest(String userId, List<Long> roleIds) {

        this.userId = userId;
        this.roleIds = roleIds;
    }
}
