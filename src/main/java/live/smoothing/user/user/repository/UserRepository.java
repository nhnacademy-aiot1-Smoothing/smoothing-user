package live.smoothing.user.user.repository;

import live.smoothing.user.user.dto.response.UserDetailResponse;
import live.smoothing.user.user.dto.response.UserSimpleResponse;
import live.smoothing.user.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, CustomUserRepository {

    Optional<UserSimpleResponse> findSimpleByUserId(String userId);

    Optional<UserDetailResponse> findDetailByUserId(String userId);
}
