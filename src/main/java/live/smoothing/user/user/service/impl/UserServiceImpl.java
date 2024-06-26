package live.smoothing.user.user.service.impl;

import live.smoothing.user.adapter.AuthAdapter;
import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.rabbitmq.MessageSender;
import live.smoothing.user.rabbitmq.dto.FcmMessage;
import live.smoothing.user.rabbitmq.dto.HookMessage;
import live.smoothing.user.role.dto.response.RoleResponse;
import live.smoothing.user.user.dto.UserInfoListResponse;
import live.smoothing.user.user.dto.request.*;
import live.smoothing.user.user.dto.response.*;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.entity.UserState;
import live.smoothing.user.user.repository.UserRepository;
import live.smoothing.user.user.service.UserService;
import live.smoothing.user.userrole.entity.UserRole;
import live.smoothing.user.userrole.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final AuthAdapter adapter;
    private final MessageSender messageSender;

    @Override
    @Transactional
    public void createUser(UserCreateRequest request) {

        boolean exists = userRepository.existsById(request.getUserId());

        if(exists) {
            throw new ServiceException(ErrorCode.DUPLICATED_USER);
        }

        PasswordEncodingResponse response = adapter.encodingPassword(new PasswordEncodingRequest(request.getUserPassword()))
                .orElseThrow(() -> new ServiceException(ErrorCode.ENCODING_FAIL));

        User user = request.toEntity(response.getEncodedPassword());

        userRepository.save(user);

        messageSender.sendMessageToHookQueue(new HookMessage("ROLE_ADMIN", "회원 승인 요청이 있습니다."));
        messageSender.sendMessageToFcmQueue(new FcmMessage("알림", "회원 승인 요청이 있습니다."));

    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseTemplate<UserSimpleResponse> getUserSimpleInfo(String userId) {

        UserSimpleResponse userSimpleResponse = userRepository.findSimpleByUserId(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        List<UserRole> roles = userRoleRepository.findByUser_UserId(userId);

        List<RoleResponse> roleResponse = roles.stream()
                .map(userRole -> new RoleResponse(userRole.getRole().getRoleInfo()))
                .collect(Collectors.toList());

        return new UserResponseTemplate<>(userSimpleResponse, roleResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseTemplate<UserDetailResponse> getUserDetailInfo(String userId) {

        UserDetailResponse userDetailResponse = userRepository.findDetailByUserId(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        List<UserRole> roles = userRoleRepository.findByUser_UserId(userId);

        List<RoleResponse> roleResponse = roles.stream()
                .map(userRole -> new RoleResponse(userRole.getRole().getRoleId(), userRole.getRole().getRoleInfo()))
                .collect(Collectors.toList());

        return new UserResponseTemplate<>(userDetailResponse, roleResponse);
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

        PasswordEncodingResponse response = adapter.encodingPassword(new PasswordEncodingRequest(request.getUserPassword()))
                .orElseThrow(() -> new ServiceException(ErrorCode.ENCODING_FAIL));

        user.modifyUserPassword(response.getEncodedPassword());
    }

    @Override
    public void modifyUserEmail(String userId, UserEmailModifyRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        user.modifyUserEmail(request.getUserEmail());
        userRepository.save(user);
    }

    @Override
    public void modifyUserName(String userId, UserNameModifyRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        user.modifyUserName(request.getUserName());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) { // 회원 탈퇴

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        user.modifyUserState(UserState.WITHDRAWAL);
    }

    @Override
    public boolean isCorrectUserPassword(String userId, UserPasswordRequest request) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        String requestPassword = request.getUserPassword();
        String userPassword = user.getUserPassword();

        return encoder.matches(requestPassword, userPassword);
    }

    @Override
    public String getUserName(String userId) {

        UserNameResponse response = userRepository.findUserNameByUserId(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        return response.getUserName();
    }

    @Override
    public UserProfileResponse getUserProfile(String userId) {

        UserProfileResponse response = userRepository.findProfileByUserId(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        return response;
    }

    @Override
    public UserInfoListResponse findAllUsers(Pageable pageable) {

        Page<UserInfoResponse> userInfos = userRepository.getUserInfos(pageable);
        return new UserInfoListResponse(userInfos.getContent(),userInfos.getTotalPages());
    }

    @Override
    public boolean isExistUser(String userId) {

        return userRepository.existsById(userId);
    }

    @Override
    public UserStateResponse getUserState(String userId) {

        UserState userState = userRepository.getUserState(userId);

        return new UserStateResponse(userState.name());
    }
}
