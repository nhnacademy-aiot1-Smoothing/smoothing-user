package live.smoothing.user.hook.repository;

import live.smoothing.user.hook.entity.UserHook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHookRepository extends JpaRepository<UserHook, Integer> {

    UserHook findByUser_UserIdAndHookListId(String userId, Integer hookListId);

    List<UserHook> findByUser_UserId(String userId);
}
