package live.smoothing.user.role.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateRoleRequest {

    @NotNull
    @Size(max = 30, message = "최대 30자 까지 입니다")
    private String roleInfo;

    public UpdateRoleRequest(String roleInfo) {
        this.roleInfo = roleInfo;
    }
}
