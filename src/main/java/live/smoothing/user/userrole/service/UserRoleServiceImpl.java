package live.smoothing.user.userrole.service;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.role.dto.response.RoleResponse;
import live.smoothing.user.role.entity.Role;
import live.smoothing.user.role.repository.RoleRepository;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import live.smoothing.user.userrole.dto.request.UserRoleModifyRequest;
import live.smoothing.user.userrole.dto.response.UserIdListResponse;
import live.smoothing.user.userrole.entity.UserRole;
import live.smoothing.user.userrole.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void createUserRole(UserRoleCreateRequest request) { // 이건 맨 처음 회원 승인 할 때만 쓸 듯

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        for (Long roleId : request.getRoleIds()) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new ServiceException(ErrorCode.ROLE_NOT_FOUND));

            userRoleRepository.save(new UserRole(user, role));
        }
    }

    @Override
    public void modifyUserRole(UserRoleModifyRequest request) { // 회원 목록페이지에서 권한 변경할 때 사용

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        for (Long roleId : request.getRoleIds()) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new ServiceException(ErrorCode.ROLE_NOT_FOUND));

            List<UserRole> userRoles = userRoleRepository.findByUser_UserIdAndRole_RoleId(request.getUserId(), roleId);
            userRoleRepository.deleteAll(userRoles);

            userRoleRepository.save(new UserRole(user, role));
        }
    }

    @Override
    public List<RoleResponse> getUserRolesByUserId(String userId) {

        boolean isExists = userRepository.findById(userId).isPresent();

        if(!isExists) {
            throw new ServiceException(ErrorCode.USER_NOT_FOUND);
        }

        List<UserRole> userRoles = userRoleRepository.findByUser_UserId(userId);

        return userRoles.stream()
                .map(userRole -> new RoleResponse(userRole.getRole().getRoleId(),
                        userRole.getRole().getRoleInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public UserIdListResponse getUserIdsByRoleId(Long roleId) {

        List<UserRole> userRoles = userRoleRepository.findByRole_RoleId(roleId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        String roleInfo = optionalRole.map(Role::getRoleInfo).orElse("Role not found");
        List<String> userIds = userRoles.stream()
                .map(userRole -> userRole.getUser().getUserId())
                .collect(Collectors.toList());

        return new UserIdListResponse(roleInfo, userIds);
    }

    @Override
    public void deleteUserRole(Long userRoleId) {

        userRoleRepository.deleteById(userRoleId);
    }
}