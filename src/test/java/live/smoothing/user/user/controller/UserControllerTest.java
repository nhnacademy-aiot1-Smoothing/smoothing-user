package live.smoothing.user.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.user.role.dto.response.RoleResponse;
import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.request.UserInfoModifyRequest;
import live.smoothing.user.user.dto.request.UserPWModifyRequest;
import live.smoothing.user.user.dto.response.UserDetailResponse;
import live.smoothing.user.user.dto.response.UserResponseTemplate;
import live.smoothing.user.user.dto.response.UserSimpleResponse;
import live.smoothing.user.user.entity.UserState;
import live.smoothing.user.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Constructor;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    String ID = "USER";
    String PW = "PW12345689";
    String NAME = "U_S_E_R";
    String EMAIL = "user@gmail.com";
    UserState USER_STATE = UserState.NOT_APPROVED;
    Long USER_POINT = 100000L;
    LocalDateTime LAST_ACCESS = LocalDateTime.now();

    String ROLE_USER = "ROLE_USER";
    String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("유저 계정 생성 API 테스트")
    void createUser() throws Exception {

        Constructor<UserCreateRequest> constructor = UserCreateRequest.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        UserCreateRequest userRequest = constructor.newInstance();

        ReflectionTestUtils.setField(userRequest, "userId", ID);
        ReflectionTestUtils.setField(userRequest, "userPassword", PW);
        ReflectionTestUtils.setField(userRequest, "userName", NAME);
        ReflectionTestUtils.setField(userRequest, "userEmail", EMAIL);

        String requestJson = objectMapper.writeValueAsString(userRequest);

        doNothing().when(userService).createUser(any(UserCreateRequest.class));

        mockMvc.perform(post("/api/user/signup")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("유저 회원 가입 완료"));
    }

    @Test
    @DisplayName("로그인 API 테스트")
    void getUserSimpleInfo() throws Exception {

        List<RoleResponse> roleRespons = Arrays.asList(
                new RoleResponse(1L, ROLE_USER),
                new RoleResponse(2L, ROLE_ADMIN)
        );

        UserResponseTemplate<UserSimpleResponse> mockResponse = new UserResponseTemplate<>(
                new UserSimpleResponse(ID, PW),
                roleRespons
        );

        Mockito.when(userService.getUserSimpleInfo(ID)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/user/login")
                        .header("X-USER-ID", ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.user.userId").value(ID))
                .andExpect(jsonPath("$.user.userPassword").value(PW))
                .andExpect(jsonPath("$.roles[0]").value(ROLE_USER))
                .andExpect(jsonPath("$.roles[1]").value(ROLE_ADMIN));
    }

    @Test
    @DisplayName("유저 정보 조회 API 테스트")
    void getUserDetailInfo() throws Exception {

        List<RoleResponse> roleResponse = Arrays.asList(
                new RoleResponse(1L, ROLE_USER),
                new RoleResponse(2L, ROLE_ADMIN)
        );

        UserResponseTemplate<UserDetailResponse> mockResponse = new UserResponseTemplate<>(
                new UserDetailResponse(ID, PW, NAME, EMAIL, USER_STATE, USER_POINT, LAST_ACCESS),
                roleResponse
        );

        Mockito.when(userService.getUserDetailInfo(ID)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/user/profile")
                        .header("X-USER-ID", ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.user.userId").value(ID))
                .andExpect(jsonPath("$.user.userPassword").value(PW))
                .andExpect(jsonPath("$.user.userName").value(NAME))
                .andExpect(jsonPath("$.user.userEmail").value(EMAIL))
                .andExpect(jsonPath("$.roles[0]").value(ROLE_USER))
                .andExpect(jsonPath("$.roles[1]").value(ROLE_ADMIN));
    }

    @Test
    @DisplayName("유저 비밀번호 수정 API 테스트")
    void userPasswordModify() throws Exception {

        Constructor<UserPWModifyRequest> constructor = UserPWModifyRequest.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        UserPWModifyRequest request = constructor.newInstance();

        ReflectionTestUtils.setField(request, "userPassword", PW);

        String requestJson = objectMapper.writeValueAsString(request);

        doNothing().when(userService).modifyUserPassword(ID, request);

        mockMvc.perform(put("/api/user/profile/password")
                        .header("X-USER-ID", ID)
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("유저 비밀번호 변경 완료"));
    }

    @Test
    @DisplayName("유저 정보 수정 API 테스트")
    void userInfoModify() throws Exception {

        Constructor<UserInfoModifyRequest> constructor = UserInfoModifyRequest.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        UserInfoModifyRequest request = constructor.newInstance();

        ReflectionTestUtils.setField(request, "userName", NAME);
        ReflectionTestUtils.setField(request, "userEmail", EMAIL);

        String requestJson = objectMapper.writeValueAsString(request);

        doNothing().when(userService).modifyUserInfo(ID, request);

        mockMvc.perform(put("/api/user/profile")
                        .header("X-USER-ID", ID)
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("유저 정보 변경 완료"));
    }


    @Test
    @DisplayName("유저 정보 삭제 API 테스트")
    void userInactive() throws Exception {

        mockMvc.perform(delete("/api/user/inactive")
                        .header("X-USER-ID", ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("유저 비활성화 완료"));
    }
}
