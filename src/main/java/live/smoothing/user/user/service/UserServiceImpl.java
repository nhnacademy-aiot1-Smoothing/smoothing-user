package live.smoothing.user.user.service;

import live.smoothing.user.adapter.AuthAdapter;
import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.role.dto.RoleResponse;
import live.smoothing.user.role.entity.Role;
import live.smoothing.user.role.repository.RoleRepository;
import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.request.UserInfoModifyRequest;
import live.smoothing.user.user.dto.request.UserPWModifyRequest;
import live.smoothing.user.user.dto.response.PasswordDto;
import live.smoothing.user.user.dto.response.UserDetailResponse;
import live.smoothing.user.user.dto.response.UserResponseTemplate;
import live.smoothing.user.user.dto.response.UserSimpleResponse;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import live.smoothing.user.userrole.dto.UserRoleRequest;
import live.smoothing.user.userrole.entity.UserRole;
import live.smoothing.user.userrole.repository.UserRoleRepository;
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
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
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

        for (UserRoleRequest userRoleRequest : request.getUserRoles()) {
            Role role = roleRepository.getReferenceById(userRoleRequest.getUserRoleId());
            UserRole userRole = new UserRole(role, user);
            user.getUserRoles().add(userRole);
        }
        userRepository.save(user);
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

        PasswordDto response = adapter.encodingPassword(new PasswordDto(request.getUserPassword()))
                .orElseThrow(() -> new ServiceException(ErrorCode.ENCODING_FAIL));

        user.modifyUserPassword(response.getPassword());
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        user.setDeleteState(Boolean.TRUE);
    }
}
