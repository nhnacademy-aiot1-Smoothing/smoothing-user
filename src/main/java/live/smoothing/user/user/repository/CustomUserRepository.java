package live.smoothing.user.user.repository;

import live.smoothing.user.user.dto.WaitingUser;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomUserRepository {

    List<WaitingUser> findWaitingUsers(Pageable pageable);
}
