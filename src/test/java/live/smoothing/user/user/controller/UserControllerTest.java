/*
package live.smoothing.user.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.user.adapter.AuthAdapter;
import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.response.PasswordDto;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private PasswordDto passwordDto;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("유저 생성 API 테스트")
    void createUser() throws Exception {
        // given
        UserCreateRequest request = new UserCreateRequest();
        request.setUserId("testUser");
        request.setUserPassword("testPassword");
        request.setUserName("Test User");
        request.setUserEmail("test@example.com");

        PasswordDto passwordDto = new PasswordDto("encodedPassword");)

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        // then
    }

    @Test
    @DisplayName("특정 유저 조회 API 테스트")
    void getUserSimpleInfo() throws Exception {
        // given
        String userId = "testUser";
        User user = new User();
        user.setUserId(userId);
        when(userService.getUser(userId)).thenReturn(user);

        // when
        mockMvc.perform(get("/api/users/{userId}", userId))
                .andExpect(status().isOk());

        // then
    }

    @Test
    @DisplayName("유저 정보 수정 API 테스트")
    void getUserDetailInfo() throws Exception {
        // given
        String userId = "testUser";

        // when
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"변경 할 이름\", \"userEmail\": \"변경 할 이메일\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("사용자 정보 변경 완료."));
        // then
    }

    @Test
    @DisplayName("유저 비밀번호 수정 API 테스트")
    void userPasswordModify() throws Exception {
        // given
        String userId = "testUser";
        String newPassword = "newTestPassword";

        // when
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/users/{userId}/password", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userPassword\": \"변경 할 비밀번호\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("사용자 비밀번호 변경 완료."));

        // then
    }


    @Test
    @DisplayName("유저 정보 삭제 API 테스트")
    void userInactive() throws Exception {
        // given
        String userId = "testUser";

        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{userId}/inactive", userId))
                .andExpect(status().isOk());
        // then
    }
}
 */

package live.smoothing.user.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.response.PasswordDto;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private PasswordDto passwordDto;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("유저 생성 API 테스트")
    void createUser() throws Exception {
        // given
        UserCreateRequest request = new UserCreateRequest();
        request.setUserId("testUser");
        request.setUserPassword("testPassword");
        request.setUserName("Test User");
        request.setUserEmail("test@example.com");

        User createdUser = new User(); // 가짜 유저 생성
        ReflectionTestUtils.setField(createdUser, "userId", request.getUserId());
        ReflectionTestUtils.setField(createdUser, "userPassword", request.getUserPassword());
        ReflectionTestUtils.setField(createdUser, "userName", request.getUserName());
        ReflectionTestUtils.setField(createdUser, "userEmail", request.getUserEmail());

        when(userService.createUser(any(UserCreateRequest.class))).thenReturn(new User());

        mockMvc.perform(post("/api/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());

        // 메소드 호출 검증
        verify(userService, times(1)).createUser(any(UserCreateRequest.class));

    }

    @Test
    @DisplayName("특정 유저 조회 API 테스트")
    void getUserSimpleInfo() throws Exception {
        // given
        String userId = "testUser";
        User user = new User();
        user.setUserId(userId);
        when(userService.getUser(userId)).thenReturn(user);

        // when
        mockMvc.perform(get("/api/users/{userId}", userId))
                .andExpect(status().isOk());

        // then
    }

    @Test
    @DisplayName("유저 정보 수정 API 테스트")
    void getUserDetailInfo() throws Exception {
        // given
        String userId = "testUser";

        // when
        mockMvc.perform(patch("/api/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"변경 할 이름\", \"userEmail\": \"변경 할 이메일\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("사용자 정보 변경 완료."));
        // then
    }

    @Test
    @DisplayName("유저 비밀번호 수정 API 테스트")
    void userPasswordModify() throws Exception {
        // given
        String userId = "testUser";

        // when
        mockMvc.perform(patch("/api/users/{userId}/password", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userPassword\": \"변경 할 비밀번호\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("사용자 비밀번호 변경 완료."));

        // then
    }


    @Test
    @DisplayName("유저 정보 삭제 API 테스트")
    void userInactive() throws Exception {
        // given
        String userId = "testUser";

        // when
        mockMvc.perform(delete("/api/users/{userId}/inactive", userId))
                .andExpect(status().isOk());
        // then
    }
}
