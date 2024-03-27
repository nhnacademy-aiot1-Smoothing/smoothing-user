package live.smoothing.user.user.service;

import live.smoothing.user.user.dto.request.CreateUserRequest;
import live.smoothing.user.user.dto.response.UserResponseTemplate;

public interface UserService {

    void createUser(CreateUserRequest request);

    UserResponseTemplate getUserSimpleInfo(String userId);

    UserResponseTemplate getUserDetailInfo(String userId);
}
