package live.smoothing.user.userrole.service;


import live.smoothing.user.role.entity.Role;
import live.smoothing.user.role.repository.RoleRepository;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import live.smoothing.user.userrole.dto.request.UserRoleModifyRequest;
import live.smoothing.user.userrole.dto.response.UserRoleResponse;
import live.smoothing.user.userrole.entity.UserRole;
import live.smoothing.user.userrole.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserRoleServiceImplTest {

    private final String userId = "user";
    private final Long roleId = 1L;


    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    UserRoleRepository userRoleRepository;

    @InjectMocks
    UserRoleServiceImpl userRoleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createUserRole() {

        UserRoleCreateRequest request = new UserRoleCreateRequest(userId, Collections.singletonList(roleId));

        User user = User.builder()
                .userId(userId)
                .build();

        Role role = new Role("ROLE_USER");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        assertDoesNotThrow(() -> userRoleService.createUserRole(request));

        verify(userRoleRepository, times(1)).save(any(UserRole.class));
    }

    @Test
    void modifyUserRole() {

        UserRoleModifyRequest request = new UserRoleModifyRequest(userId, Collections.singletonList(roleId));

        User user = User.builder()
                .userId(userId)
                .build();

        Role role = new Role("ROLE_USER");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        assertDoesNotThrow(() -> userRoleService.modifyUserRole(request));

        verify(userRoleRepository, times(1)).save(any(UserRole.class));
    }

    @Test
    void getUserRolesByUserId() {

        User user = User.builder()
                .userId(userId)
                .build();

        Role role = new Role("ROLE_USER");

        UserRole userRole = new UserRole(user, role);
        List<UserRole> userRoles = Collections.singletonList(userRole);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRoleRepository.findByUser_UserId(userId)).thenReturn(userRoles);

        List<UserRoleResponse> responses = userRoleService.getUserRolesByUserId(userId);

        assertEquals(userId, responses.get(0).getUserId());
        assertEquals("ROLE_USER", responses.get(0).getRoleInfo());
    }

    @Test
    void deleteUserRole() {

        User user = User.builder()
                .userId(userId)
                .build();

        Role role = new Role("ROLE_USER");

        UserRole userRole = new UserRole(user, role);

        when(userRoleRepository.findById(userRole.getUserRoleId())).thenReturn(Optional.of(userRole));

        assertDoesNotThrow(() -> userRoleService.deleteUserRole(userRole.getUserRoleId()));

        verify(userRoleRepository, times(1)).deleteById(userRole.getUserRoleId());
    }
}