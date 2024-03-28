package live.smoothing.user.auth.service;


import live.smoothing.user.auth.dto.AuthResponse;
import live.smoothing.user.auth.dto.CreateAuthRequest;
import live.smoothing.user.auth.dto.UpdateAuthRequest;

import java.util.List;

public interface AuthService {
    void createAuth(CreateAuthRequest request);

    AuthResponse getAuth(Long authId);
    void updateAuth(Long authId, UpdateAuthRequest request);
    void deleteAuth(Long authId);

    List<AuthResponse> getAllAuths();
}
