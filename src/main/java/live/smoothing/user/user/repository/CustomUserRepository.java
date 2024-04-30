package live.smoothing.user.user.repository;

import live.smoothing.user.user.dto.WaitingUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {

    Page<WaitingUser> findWaitingUsers(int page, int size);
}
