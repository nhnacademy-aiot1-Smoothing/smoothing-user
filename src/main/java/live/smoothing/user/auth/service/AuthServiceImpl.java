package live.smoothing.user.auth.service;

import live.smoothing.user.auth.dto.AuthResponse;
import live.smoothing.user.auth.dto.CreateAuthRequest;
import live.smoothing.user.auth.dto.UpdateAuthRequest;
import live.smoothing.user.auth.entity.Auth;
import live.smoothing.user.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    @Transactional
    public void createAuth(CreateAuthRequest request) {
        Auth auth = new Auth(request.getAuthInfo());
        authRepository.save(auth);
    }

    @Override
    public AuthResponse getAuth(Long authId) {
        Auth auth = authRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("인증 ID를 찾을 수 없습니다." + authId));

        return new AuthResponse(auth.getAuthId(), auth.getAuthInfo());
    }

    @Override
    @Transactional
    public void updateAuth(Long authId, UpdateAuthRequest request) {
        Auth auth = authRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("인증 Id를 찾을 수 없습니다." + authId));

        auth.updateAuthInfo(request.getAuthInfo());
    }

    @Override
    public void deleteAuth(Long authId) {
        Auth auth = authRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("인증 ID를 찾을 수 없습니다." + authId));
        authRepository.delete(auth);
    }
}
