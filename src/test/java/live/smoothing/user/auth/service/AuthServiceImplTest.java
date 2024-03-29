package live.smoothing.user.auth.service;

import live.smoothing.user.auth.dto.AuthResponse;
import live.smoothing.user.auth.dto.CreateAuthRequest;
import live.smoothing.user.auth.dto.UpdateAuthRequest;
import live.smoothing.user.auth.entity.Auth;
import live.smoothing.user.auth.repository.AuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAuth() {
        CreateAuthRequest request = new CreateAuthRequest("Test Auth");
        Auth saveAuth = new Auth("Test Auth");
        //Mockito.doReturn(Optional.of(auth)).when(authRepository).findById(authId);
        when(authRepository.save(any(Auth.class))).thenReturn(saveAuth);

        // when
        authService.createAuth(request);

        // then
        verify(authRepository, times(1)).save(any(Auth.class));
    }

    @Test
    void getAuth() {
        long authId = 1L;
        Auth auth = new Auth("Test Auth");
        ReflectionTestUtils.setField(auth, "authId", authId);
        Mockito.doReturn(Optional.of(auth)).when(authRepository).findById(authId);

        // when
        AuthResponse result = authService.getAuth(authId);

        // then
        assertNotNull(result); // 결과가 null이 아닌지 확인
        assertEquals(authId, result.getAuthId()); // authId가 일치하는지 확인
        assertEquals("Test Auth", result.getAuthInfo()); // authInfo가 일치하는지 확인
    }

    @Test
    void updateAuth() {
        // given
        long authId = 1L;
        UpdateAuthRequest request = new UpdateAuthRequest("Updated Auth");
        Auth auth = new Auth("Test Auth");
        Mockito.doReturn(Optional.of(auth)).when(authRepository).findById(authId);

        // when
        authService.updateAuth(authId, request);
        ReflectionTestUtils.setField(auth, "authInfo", "Updated Auth");

        // then
        verify(authRepository, times(1)).findById(authId);
        verify(authRepository, times(1)).save(auth);
        assertEquals("Updated Auth", auth.getAuthInfo());
    }

    @Test
    void deleteAuth() {
        // given
        long authId = 1L;
        Auth auth = new Auth("Test Auth");
        Mockito.doReturn(Optional.of(auth)).when(authRepository).findById(authId);

        // when
        authService.deleteAuth(authId);

        // then
        verify(authRepository, times(1)).delete(auth);
    }



}