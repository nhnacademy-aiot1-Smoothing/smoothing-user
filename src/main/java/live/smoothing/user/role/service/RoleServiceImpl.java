package live.smoothing.user.role.service;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.role.dto.RoleResponse;
import live.smoothing.user.role.dto.CreateRoleRequest;
import live.smoothing.user.role.dto.UpdateRoleRequest;
import live.smoothing.user.role.entity.Role;
import live.smoothing.user.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void createRole(CreateRoleRequest request) {

        boolean exists = roleRepository.existsByRoleInfo(request.getRoleInfo());

        if (exists) {
            throw new ServiceException(ErrorCode.DUPLICATED_ROLE);
        }
        roleRepository.save(new Role(request.getRoleInfo()));
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponse getRole(Long roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ServiceException(ErrorCode.ROLE_NOT_FOUND));

        return new RoleResponse(role.getRoleId(), role.getRoleInfo());
    }

    @Override
    @Transactional
    public void updateRole(long roleId, UpdateRoleRequest request) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ServiceException(ErrorCode.ROLE_NOT_FOUND));

        role.modifyRoleInfo(request.getRoleInfo());

        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(Long roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ServiceException(ErrorCode.ROLE_NOT_FOUND));

        roleRepository.delete(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream()
                .map(role -> new RoleResponse(role.getRoleId(), role.getRoleInfo()))
                .collect(Collectors.toList());
    }
}
