package live.smoothing.user.user.service;

import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.request.UserInfoModifyRequest;
import live.smoothing.user.user.dto.request.UserPWModifyRequest;
import live.smoothing.user.user.dto.response.UserResponseTemplate;

public interface UserService {

    void createUser(UserCreateRequest request);

    UserResponseTemplate getUserSimpleInfo(String userId);

    UserResponseTemplate getUserDetailInfo(String userId);

    void modifyUserInfo(String userId, UserInfoModifyRequest request);

    void modifyUserPassword(String userId, UserPWModifyRequest request);

    void deleteUser(String userId);

    UserResponseTemplate getUser(String userId);

}
