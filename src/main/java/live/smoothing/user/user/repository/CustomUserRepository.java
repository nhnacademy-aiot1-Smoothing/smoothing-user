package live.smoothing.user.user.repository;

import live.smoothing.user.user.dto.WaitingUser;

import java.util.List;

public interface CustomUserRepository {

    List<WaitingUser> findWaitingUsers(int page, int size);

    List<WaitingUser> findWaitingUsers();
}
