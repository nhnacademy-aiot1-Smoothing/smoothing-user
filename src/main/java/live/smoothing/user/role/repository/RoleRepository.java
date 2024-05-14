package live.smoothing.user.role.repository;

import live.smoothing.user.role.dto.response.RoleResponse;
import live.smoothing.user.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleInfo(String roleInfo);

    @Query("SELECT new live.smoothing.user.role.dto.response.RoleResponse(r.roleId, r.roleInfo) FROM Role r JOIN UserRole ur ON r.roleId = ur.role.roleId WHERE ur.user.userId = :userId")
    List<RoleResponse> getRoleByUserId(String userId);
}
