package live.smoothing.user.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.user.auth.controller.AuthController;
import live.smoothing.user.auth.dto.AuthResponse;
import live.smoothing.user.auth.dto.CreateAuthRequest;
import live.smoothing.user.auth.dto.UpdateAuthRequest;
import live.smoothing.user.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAuth() throws Exception {
        // given
        Long authId = 1L;
        AuthResponse authResponse = new AuthResponse(authId, "Test Auth");
        when(authService.getAuth(authId)).thenReturn(authResponse);

        // when, then
        mockMvc.perform(get("/api/auth/{authId}", authId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authId").value(authId))
                .andExpect(jsonPath("$.authInfo").value("Test Auth"));

        // then
    }

    @Test
    void getAllAuths() throws Exception {
        // given

        // when
        mockMvc.perform(get("/api/auth/list"))
                .andExpect(status().isOk());

        // then
    }

    @Test
    void createAuth() throws Exception {
        // given
        CreateAuthRequest request = new CreateAuthRequest("Test Auth");

        // when, then
        mockMvc.perform(post("/api/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isCreated());
        // then
    }

    @Test
    void updateAuth() throws Exception {
        // given
        Long authid = 1L;
        UpdateAuthRequest request = new UpdateAuthRequest("Update Auth");

        // when, then
        mockMvc.perform(put("/api/auth/{authId}", authid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isOk());

        // then
    }

    @Test
    void deleteAuth() throws Exception {
        // given
        Long authId = 1L;

        // when, then
        mockMvc.perform(delete("/api/auth/{authId}", authId))
                .andExpect(status().isOk());
        // then
    }
}