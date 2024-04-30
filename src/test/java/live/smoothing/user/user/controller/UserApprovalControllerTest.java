package live.smoothing.user.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.user.service.UserApprovalService;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserApprovalController.class)
class UserApprovalControllerTest {

    private final String userId = "user";
    private final Long roleId = 1L;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserApprovalService userApprovalService;

    @Test
    @DisplayName("회원 승인 요청 목록 조회 테스트")
    void getWaitingUserList() throws Exception {

        int page = 0;
        int size = 10;

        List<WaitingUser> waitingUserList = Collections.emptyList();
        when(userApprovalService.waitingUserList(page, size)).thenReturn(waitingUserList);

        mockMvc.perform(get("/api/user/waitingUserList")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("회원 승인 테스트")
    void approveUser() throws Exception {

        UserRoleCreateRequest request = new UserRoleCreateRequest(userId, Collections.singletonList(roleId));

        mockMvc.perform(put("/api/user/approve")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value("회원 승인 완료"));
    }

    @Test
    @DisplayName("회원 승인 요청 거절 테스트")
    void rejectUser() throws Exception {

        mockMvc.perform(delete("/api/user/reject/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value("회원 승인 요청 거절"));
    }
}