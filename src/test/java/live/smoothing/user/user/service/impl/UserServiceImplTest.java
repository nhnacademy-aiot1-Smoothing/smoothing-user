package live.smoothing.user.user.service.impl;


import live.smoothing.user.adapter.AuthAdapter;
import live.smoothing.user.role.entity.Role;
import live.smoothing.user.role.repository.RoleRepository;
import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.response.PasswordEncodingResponse;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Constructor;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserServiceImplTest {

    String ID = "USER";
    String PW = "PW1234";

    String encodedPW ="f3409gklfgbh4234059gjkh9";
    String NAME = "U_S_E_R";
    String EMAIL = "user@gmail.com";

    @Mock
    AuthAdapter authAdapter;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("유저 생성 테스트")
    void createUserTest() throws Exception {
        Constructor<UserCreateRequest> constructor = UserCreateRequest.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        UserCreateRequest userRequest = constructor.newInstance();

        ReflectionTestUtils.setField(userRequest, "userId", ID);
        ReflectionTestUtils.setField(userRequest, "userPassword", PW);
        ReflectionTestUtils.setField(userRequest, "userName", NAME);
        ReflectionTestUtils.setField(userRequest, "userEmail", EMAIL);

        PasswordEncodingResponse response = new PasswordEncodingResponse(encodedPW);
        Role role = new Role("ROLE_USER");

        // 모의 처리
        Mockito.when(authAdapter.encodingPassword(any())).thenReturn(Optional.of(response));
        Mockito.when(roleRepository.getReferenceById(1L)).thenReturn(role);

        // 실제 메소드 실행
        userService.createUser(userRequest);

        // 메소드 호출 검증
        verify(userRepository, times(1)).save(any(User.class));

        // 메소드 로직내 전달된 객체 포착 및 검증
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getUserId()).isEqualTo(ID);
        assertThat(capturedUser.getUserPassword()).isEqualTo(encodedPW);
        assertThat(capturedUser.getUserName()).isEqualTo(NAME);
        assertThat(capturedUser.getUserEmail()).isEqualTo(EMAIL);
    }
}
