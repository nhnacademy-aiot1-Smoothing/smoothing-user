package live.smoothing.user.userrole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.user.role.dto.response.RoleResponse;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import live.smoothing.user.userrole.dto.request.UserRoleModifyRequest;
import live.smoothing.user.userrole.service.UserRoleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRoleController.class)
class UserRoleControllerTest {

    private final String userId = "user";
    private final Long roleId = 1L;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserRoleService userRoleService;

    @InjectMocks
    private UserRoleController userRoleController;
    @Test
    @DisplayName("회원 권한 생성 테스트")
    void createUserRole() throws Exception {

        UserRoleCreateRequest request = new UserRoleCreateRequest(userId, Collections.singletonList(roleId));

        mockMvc.perform(post("/api/user/userRole")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value("회원 권한 설정 완료"));

    }

    @Test
    @DisplayName("특정 사용자의 회원 권한 조회 테스트")
    void getUserRolesByUserId() throws Exception {

        List<RoleResponse> userRoleResponses = Collections.singletonList(new RoleResponse(1L, "ROLE_USER"));

        when(userRoleService.getUserRolesByUserId(userId)).thenReturn(userRoleResponses);

        mockMvc.perform(get("/api/user/userRole/list?userId=" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].roleId").value(1L))
                .andExpect(jsonPath("$[0].roleInfo").value("ROLE_USER"));
    }

    @Test
    @DisplayName("회원 권한 수정 테스트")
    void modifyUserRole() throws Exception {

        UserRoleModifyRequest request = new UserRoleModifyRequest(userId, Collections.singletonList(roleId));

        mockMvc.perform(put("/api/user/userRole")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value("회원 권한 수정 완료"));

        verify(userRoleService, times(1)).modifyUserRole(any(UserRoleModifyRequest.class));
    }

    @Test
    @DisplayName("회원 권한 삭제 테스트")
    void deleteUserRole() throws Exception {

        Long userRoleId = 1L;

        mockMvc.perform(delete("/api/user/userRole/delete/{userRoleId}", userRoleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value(("회원 권한 삭제 완료")));

        verify(userRoleService, times(1)).deleteUserRole(userRoleId);
    }
}