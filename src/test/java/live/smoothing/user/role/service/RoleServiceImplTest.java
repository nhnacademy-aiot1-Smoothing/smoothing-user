package live.smoothing.user.role.service;

import live.smoothing.user.role.dto.response.RoleResponse;
import live.smoothing.user.role.dto.request.CreateRoleRequest;
import live.smoothing.user.role.dto.request.UpdateRoleRequest;
import live.smoothing.user.role.entity.Role;
import live.smoothing.user.role.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoleServiceImplTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRole() {
        CreateRoleRequest request = new CreateRoleRequest("Test Role");
        Role saveRole = new Role("Test Role");

        when(roleRepository.save(any(Role.class))).thenReturn(saveRole);

        // when
        roleService.createRole(request);

        // then
        verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    void getRole() {
        long roleId = 1L;
        Role role = new Role("Test Role");
        ReflectionTestUtils.setField(role, "roleId", roleId);
        Mockito.doReturn(Optional.of(role)).when(roleRepository).findById(roleId);

        // when
        RoleResponse result = roleService.getRole(roleId);

        // then
        assertNotNull(result); // 결과가 null이 아닌지 확인
        assertEquals(roleId, result.getRoleId()); // roleId가 일치하는지 확인
        assertEquals("Test Role", result.getRoleInfo()); // roleInfo가 일치하는지 확인
    }

    @Test
    void updateRole() {
        // given
        long roleId = 1L;
        UpdateRoleRequest request = new UpdateRoleRequest("Updated Role");
        Role role = new Role("Test Role");
        Mockito.doReturn(Optional.of(role)).when(roleRepository).findById(roleId);

        // when
        roleService.updateRole(roleId, request);
        ReflectionTestUtils.setField(role, "roleInfo", "Updated Role");

        // then
        verify(roleRepository, times(1)).findById(roleId);
        verify(roleRepository, times(1)).save(role);
        assertEquals("Updated Role", role.getRoleInfo());
    }

    @Test
    void deleteRole() {
        // given
        long roleId = 1L;
        Role role = new Role("Test Role");
        Mockito.doReturn(Optional.of(role)).when(roleRepository).findById(roleId);

        // when
        roleService.deleteRole(roleId);

        // then
        verify(roleRepository, times(1)).delete(role);
    }
}