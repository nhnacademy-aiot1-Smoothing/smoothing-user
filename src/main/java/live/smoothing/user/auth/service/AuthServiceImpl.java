package live.smoothing.user.auth.service;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.auth.dto.AuthResponse;
import live.smoothing.user.auth.dto.CreateAuthRequest;
import live.smoothing.user.auth.dto.UpdateAuthRequest;
import live.smoothing.user.auth.entity.Auth;
import live.smoothing.user.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Override
    @Transactional
    public void createAuth(CreateAuthRequest request) {

        boolean exists = authRepository.existsByAuthInfo(request.getAuthInfo());

        if (exists) {
            throw new ServiceException(ErrorCode.DUPLICATED_AUTH);
        }
        authRepository.save(new Auth(request.getAuthInfo()));
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse getAuth(Long authId) {

        Auth auth = authRepository.findById(authId)
                .orElseThrow(() -> new ServiceException(ErrorCode.AUTH_NOT_FOUND));

        return new AuthResponse(auth.getAuthId(), auth.getAuthInfo());
    }

    @Override
    @Transactional
    public void updateAuth(long authId, UpdateAuthRequest request) {

        Auth auth = authRepository.findById(authId)
                .orElseThrow(() -> new ServiceException(ErrorCode.AUTH_NOT_FOUND));

        auth.updateAuthInfo(request.getAuthInfo());

        authRepository.save(auth);
    }

    @Override
    @Transactional
    public void deleteAuth(Long authId) {

        Auth auth = authRepository.findById(authId)
                .orElseThrow(() -> new ServiceException(ErrorCode.AUTH_NOT_FOUND));

        authRepository.delete(auth);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthResponse> getAllAuths() {
        List<Auth> authList = authRepository.findAll();
        return authList.stream()
                .map(auth -> new AuthResponse(auth.getAuthId(), auth.getAuthInfo()))
                .collect(Collectors.toList());
    }
}
