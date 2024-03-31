package live.smoothing.user.user.service;

import live.smoothing.user.adapter.AuthAdapter;
import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.auth.dto.AuthResponse;
import live.smoothing.user.auth.entity.Auth;
import live.smoothing.user.auth.repository.AuthRepository;
import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.request.UserInfoModifyRequest;
import live.smoothing.user.user.dto.request.UserPWModifyRequest;
import live.smoothing.user.user.dto.response.PasswordDto;
import live.smoothing.user.user.dto.response.UserDetailResponse;
import live.smoothing.user.user.dto.response.UserResponseTemplate;
import live.smoothing.user.user.dto.response.UserSimpleResponse;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import live.smoothing.user.userauth.dto.UserAuthRequest;
import live.smoothing.user.userauth.entity.UserAuth;
import live.smoothing.user.userauth.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final UserAuthRepository userAuthRepository;
    private final AuthAdapter adapter;

    @Override
    @Transactional
    public void createUser(UserCreateRequest request) {

        boolean exists = userRepository.existsById(request.getUserId());

        if(exists){
            throw new ServiceException(ErrorCode.DUPLICATED_USER);
        }

        PasswordDto response = adapter.encodingPassword(new PasswordDto(request.getUserPassword()))
                .orElseThrow(() -> new ServiceException(ErrorCode.ENCODING_FAIL));

        User user = request.toEntity(response.getPassword());

        for (UserAuthRequest userAuthRequest : request.getUserAuths()) {
            Auth auth = authRepository.getReferenceById(userAuthRequest.getUserAuthId());
            UserAuth userAuth = new UserAuth(auth, user);
            user.getUserAuths().add(userAuth);
        }
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseTemplate<UserSimpleResponse> getUserSimpleInfo(String userId) {

        UserSimpleResponse userSimpleResponse = userRepository.findSimpleByUserId(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        List<UserAuth> auths = userAuthRepository.findByUser_UserId(userId);

        List<AuthResponse> authResponses = auths.stream()
                .map(userAuth -> new AuthResponse(userAuth.getAuth().getAuthId(), userAuth.getAuth().getAuthInfo()))
                .collect(Collectors.toList());

        return new UserResponseTemplate<>(userSimpleResponse, authResponses);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseTemplate<UserDetailResponse> getUserDetailInfo(String userId) {

        UserDetailResponse userDetailResponse = userRepository.findDetailByUserId(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        List<UserAuth> auths = userAuthRepository.findByUser_UserId(userId);

        List<AuthResponse> authResponses = auths.stream()
                .map(userAuth -> new AuthResponse(userAuth.getAuth().getAuthId(), userAuth.getAuth().getAuthInfo()))
                .collect(Collectors.toList());

        return new UserResponseTemplate<>(userDetailResponse, authResponses);
    }

    @Override
    @Transactional
    public void modifyUserInfo(String userId, UserInfoModifyRequest request) {

        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        Optional.ofNullable(request.getUserName()).ifPresent(user::modifyUserName);
        Optional.ofNullable(request.getUserEmail()).ifPresent(user::modifyUserEmail);
    }

    @Override
    @Transactional
    public void modifyUserPassword(String userId, UserPWModifyRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        Optional.ofNullable(request.getUserPassword()).ifPresent(user::modifyUserPassword);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        user.setDeleteState(Boolean.TRUE);
    }
}
