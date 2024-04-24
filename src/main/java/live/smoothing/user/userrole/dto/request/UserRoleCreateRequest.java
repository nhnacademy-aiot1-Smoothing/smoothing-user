package live.smoothing.user.userrole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleCreateRequest {

    private String userId;
    private List<Long> roleIds;
}
