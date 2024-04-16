package live.smoothing.user.userrole.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoleRequest {

    @NotNull
    private Long userRoleId;

    public UserRoleRequest(Long userRoleId) {

        this.userRoleId = userRoleId;
    }
}
