package live.smoothing.user.userrole.repository;

import live.smoothing.user.userrole.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUser_UserIdAndRole_RoleId(String userId, Long roleId);

    List<UserRole> findByUser_UserId(String userId);

    List<UserRole> findByRole_RoleId(Long roleId);
}
