package live.smoothing.user.role.repository;

import live.smoothing.user.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleInfo(String roleInfo);
}
