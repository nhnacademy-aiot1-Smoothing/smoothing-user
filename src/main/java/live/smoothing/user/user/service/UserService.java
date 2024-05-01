package live.smoothing.user.user.service;

import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.request.UserInfoModifyRequest;
import live.smoothing.user.user.dto.request.UserPWModifyRequest;
import live.smoothing.user.user.dto.request.UserPasswordRequest;
import live.smoothing.user.user.dto.response.UserDetailResponse;
import live.smoothing.user.user.dto.response.UserNameResponse;
import live.smoothing.user.user.dto.response.UserResponseTemplate;
import live.smoothing.user.user.dto.response.UserSimpleResponse;

public interface UserService {

    void createUser(UserCreateRequest request);

    UserResponseTemplate<UserSimpleResponse> getUserSimpleInfo(String userId);

    UserResponseTemplate<UserDetailResponse> getUserDetailInfo(String userId);

    void modifyUserInfo(String userId, UserInfoModifyRequest request);

    void modifyUserPassword(String userId, UserPWModifyRequest request);

    void deleteUser(String userId);

    boolean isCorrectUserPassword(String userId, UserPasswordRequest request);

    String getUserName(String userId);
}
