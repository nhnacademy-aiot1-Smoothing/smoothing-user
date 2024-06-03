package live.smoothing.user.hook.repository;

import live.smoothing.user.hook.entity.HookEventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HookEventTypeRepository extends JpaRepository<HookEventType, Integer> {

}
