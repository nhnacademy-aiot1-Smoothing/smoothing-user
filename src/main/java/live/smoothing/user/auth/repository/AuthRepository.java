package live.smoothing.user.auth.repository;

import live.smoothing.user.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    boolean existsByAuthInfo(String authInfo);
}
