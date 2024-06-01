package live.smoothing.user.userrole.service;

import live.smoothing.user.role.dto.response.RoleResponse;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import live.smoothing.user.userrole.dto.request.UserRoleModifyRequest;
import live.smoothing.user.userrole.dto.response.UserIdListResponse;

import java.util.List;

public interface UserRoleService {

    void createUserRole(UserRoleCreateRequest request);

    void modifyUserRole(UserRoleModifyRequest request);

    List<RoleResponse> getUserRolesByUserId(String userId);

    UserIdListResponse getUserIdsByRoleId(Long roleId);

    void deleteUserRole(Long userRoleId);

}
