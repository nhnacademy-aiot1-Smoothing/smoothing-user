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
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private AuthRepository authRepository; // Mock 객체로 AuthRepository 생성

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthServiceImpl(authRepository);
    }

    @Test
    public void CreateAuth() {
        // Given
        CreateAuthRequest request = new CreateAuthRequest("Test Auth"); // 생성 요청 생성
        Auth savedAuth = new Auth(1L, "Test Auth"); // 저장된 권한 생성
        when(authRepository.save(any(Auth.class))).thenReturn(savedAuth); // save 메서드가 호출될 때 savedAuth 반환하도록 설정

        // When
        authService.createAuth(request); // 생성 메서드 호출

        // Then
        verify(authRepository, times(1)).save(any(Auth.class)); // save 메서드가 한 번 호출되었는지 확인
    }

    @Test
    public void GetAuth() {
        // Given
        long authId = 1L;
        Auth auth = new Auth(authId, "Test Auth");
        when(authRepository.findById(authId)).thenReturn(Optional.of(auth));

        // When
        AuthResponse result = authService.getAuth(authId);

        // Then
        assertEquals(authId, result.getAuthId());
        assertEquals("Test Auth", result.getAuthInfo());
    }

    @Test
    public void UpdateAuth() {
        // Given
        long authId = 1L;
        UpdateAuthRequest request = new UpdateAuthRequest("Updated Auth");
        Auth auth = new Auth(authId, "Test Auth");
        when(authRepository.findById(authId)).thenReturn(Optional.of(auth));
        when(authRepository.save(auth)).thenReturn(auth);

        // When
        authService.updateAuth(authId, request);

        // Then
        verify(authRepository, times(1)).save(auth);
        assertEquals("Updated Auth", auth.getAuthInfo());
    }

    @Test
    public void DeleteAuth() {
        // Given
        long authId = 1L;
        Auth auth = new Auth(authId, "Test Auth");
        when(authRepository.findById(authId)).thenReturn(Optional.of(auth));

        // When
        authService.deleteAuth(authId);

        // Then
        verify(authRepository, times(1)).delete(auth);
    }

    @Test
    public void GetAuth_NotFound() {
        // Given
        long nonExistingAuthId = 99L;
        when(authRepository.findById(nonExistingAuthId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(RuntimeException.class, () -> authService.getAuth(nonExistingAuthId));
    }

    @Test
    public void UpdateAuth_NotFound() {
        // Given
        long nonExistingAuthId = 99L;
        UpdateAuthRequest request = new UpdateAuthRequest("Updated Auth");
        when(authRepository.findById(nonExistingAuthId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> authService.updateAuth(nonExistingAuthId, request));
    }

    @Test
    public void DeleteAuth_NotFound() {
        // Given
        long nonExistingAuthId = 99L;
        when(authRepository.findById(nonExistingAuthId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> authService.deleteAuth(nonExistingAuthId));
    }
}
