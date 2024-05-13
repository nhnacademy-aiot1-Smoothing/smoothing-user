package live.smoothing.user.user.repository;

import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.user.dto.response.*;
import live.smoothing.user.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, CustomUserRepository {

    Optional<UserSimpleResponse> findSimpleByUserId(String userId);

    Optional<UserDetailResponse> findDetailByUserId(String userId);

    Optional<UserNameResponse> findUserNameByUserId(String userId);

    Optional<UserProfileResponse> findProfileByUserId(String userId);

    @Query("select new live.smoothing.user.user.dto.WaitingUser(u.userId, u.userName, u.lastAccess) from User u where u.userState = live.smoothing.user.user.entity.UserState.NOT_APPROVED")
    Page<WaitingUser> getWaitingUsers(Pageable pageable);

    @Query("select new live.smoothing.user.user.dto.response.UserInfoResponse(u.userId, u.userName, u.userEmail) from User u where u.userState = live.smoothing.user.user.entity.UserState.APPROVED")
    Page<UserInfoResponse> getUserInfos(Pageable pageable);
}
