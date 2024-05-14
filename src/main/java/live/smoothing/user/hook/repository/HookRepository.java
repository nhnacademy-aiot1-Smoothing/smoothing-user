package live.smoothing.user.hook.repository;

import live.smoothing.user.hook.entity.Hook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HookRepository extends JpaRepository<Hook, Integer> {

    Hook findByUser_UserId(String userId);

    boolean existsByUser_UserId(String userId);

}
