package live.smoothing.user.userauth.repository;

import live.smoothing.user.userauth.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, UserAuth.Pk> {
}
