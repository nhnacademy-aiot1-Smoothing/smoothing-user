package live.smoothing.user.role.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoleResponse {

    private Long roleId;
    private String roleInfo;

    public RoleResponse(String roleInfo) {

        this.roleInfo = roleInfo;
    }
}
