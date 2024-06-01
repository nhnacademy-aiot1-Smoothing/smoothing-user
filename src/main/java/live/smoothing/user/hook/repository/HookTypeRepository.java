package live.smoothing.user.hook.repository;

import live.smoothing.user.hook.entity.HookType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HookTypeRepository extends JpaRepository<HookType, Integer> {

    boolean existsHookTypeByHookTypeName(String hookTypeName);

}
