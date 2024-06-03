package live.smoothing.user.hook.repository;

import live.smoothing.user.hook.entity.UserHook;
import live.smoothing.user.hook.entity.UserHookEventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHookEventTypeRepository extends JpaRepository<UserHookEventType, Integer> {

    void deleteByUserHook(UserHook userHook);

    List<UserHookEventType> findByUserHook(UserHook userHook);
}
