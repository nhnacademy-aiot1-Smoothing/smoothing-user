package live.smoothing.user.userauth.repository;

import live.smoothing.user.userauth.entity.UserAuth;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAuthRepository extends JpaRepository<UserAuth, UserAuth.Pk> {

    @EntityGraph(attributePaths = {"auth"})
    List<UserAuth> findByUser_UserId(String userId);
}
