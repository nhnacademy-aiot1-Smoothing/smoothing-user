package live.smoothing.user.role.service;


import live.smoothing.user.role.dto.RoleResponse;
import live.smoothing.user.role.dto.CreateRoleRequest;
import live.smoothing.user.role.dto.UpdateRoleRequest;

import java.util.List;

public interface RoleService {
    void createRole(CreateRoleRequest request);

    RoleResponse getRole(Long roleId);
    void updateRole(long roleId, UpdateRoleRequest request);
    void deleteRole(Long roleId);

    List<RoleResponse> getAllRoles();
}
