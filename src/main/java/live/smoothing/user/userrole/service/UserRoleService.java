package live.smoothing.user.userrole.service;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import live.smoothing.user.userrole.dto.request.UserRoleModifyRequest;
import live.smoothing.user.userrole.dto.response.UserIdListResponse;
import live.smoothing.user.userrole.dto.response.UserRoleResponse;

import java.util.List;

public interface UserRoleService {

    void createUserRole(UserRoleCreateRequest request);

    void modifyUserRole(UserRoleModifyRequest request);

    List<UserRoleResponse> getUserRolesByUserId(String userId);

    UserIdListResponse getUserIdsByRoleId(Long roleId);

    void deleteUserRole(Long userRoleId);

}
