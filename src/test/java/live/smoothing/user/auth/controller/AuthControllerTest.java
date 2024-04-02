package live.smoothing.user.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.user.auth.dto.AuthResponse;
import live.smoothing.user.auth.dto.CreateAuthRequest;
import live.smoothing.user.auth.dto.UpdateAuthRequest;
import live.smoothing.user.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    Long AUTH_ID = 1L;
    String ROLE_USER = "ROLE_USER";
    String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthService authService;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("권한명 조회")
    void getAuth() throws Exception {

        AuthResponse authResponse = new AuthResponse(AUTH_ID, ROLE_USER);

        Mockito.when(authService.getAuth(AUTH_ID)).thenReturn(authResponse);

        mockMvc.perform(get("/api/auth/{authId}", AUTH_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.authId").value(AUTH_ID))
                .andExpect(jsonPath("$.authInfo").value(ROLE_USER));
    }

    @Test
    @DisplayName("권한 목록 조회")
    void getAllAuths() throws Exception {

        List<AuthResponse> authResponseList = Arrays.asList(
                new AuthResponse(AUTH_ID, ROLE_USER),
                new AuthResponse(AUTH_ID + 1, ROLE_ADMIN)
        );

        Mockito.when(authService.getAllAuths()).thenReturn(authResponseList);

        mockMvc.perform(get("/api/auth/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].authId").value(AUTH_ID))
                .andExpect(jsonPath("$[0].authInfo").value(ROLE_USER))
                .andExpect(jsonPath("$[1].authId").value(AUTH_ID + 1))
                .andExpect(jsonPath("$[1].authInfo").value(ROLE_ADMIN));
    }

    @Test
    @DisplayName("권한 정보 생성")
    void createAuth() throws Exception {

        CreateAuthRequest request = new CreateAuthRequest(ROLE_USER);

        String requestJson = objectMapper.writeValueAsString(request);

        doNothing().when(authService).createAuth(request);

        mockMvc.perform(post("/api/auth")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("권한 정보 생성 완료"));
    }

    @Test
    @DisplayName("권한 정보 업데이트")
    void updateAuth() throws Exception {

        UpdateAuthRequest request = new UpdateAuthRequest(ROLE_ADMIN);

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/api/auth/{authId}", AUTH_ID)
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("권한명 수정 완료"));
    }

    @Test
    @DisplayName("권한 정보 삭제")
    void deleteAuth() throws Exception {

        doNothing().when(authService).deleteAuth(AUTH_ID);

        mockMvc.perform(delete("/api/auth/{authId}", AUTH_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("권한 정보 삭제 완료"));
    }
}