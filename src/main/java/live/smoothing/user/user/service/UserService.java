package live.smoothing.user.user.service;

import live.smoothing.user.user.dto.UserInfoListResponse;
import live.smoothing.user.user.dto.request.*;
import live.smoothing.user.user.dto.response.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    void createUser(UserCreateRequest request);

    UserResponseTemplate<UserSimpleResponse> getUserSimpleInfo(String userId);

    UserResponseTemplate<UserDetailResponse> getUserDetailInfo(String userId);

    void modifyUserInfo(String userId, UserInfoModifyRequest request);

    void modifyUserPassword(String userId, UserPWModifyRequest request);

    void modifyUserEmail(String userId, UserEmailModifyRequest request);

    void modifyUserName(String userId, UserNameModifyRequest request);

    void deleteUser(String userId);

    boolean isCorrectUserPassword(String userId, UserPasswordRequest request);

    String getUserName(String userId);

    UserProfileResponse getUserProfile(String userId);

    UserInfoListResponse findAllUsers(Pageable pageable);

    boolean isExistUser(String userId);

    UserStateResponse getUserState(String userId);
}
