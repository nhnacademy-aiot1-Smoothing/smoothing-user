package live.smoothing.user.auth.controller;

import live.smoothing.user.auth.dto.AuthResponse;
import live.smoothing.user.auth.dto.CreateAuthRequest;
import live.smoothing.user.auth.dto.UpdateAuthRequest;
import live.smoothing.user.auth.service.AuthService;
import live.smoothing.user.common.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/{authId}")
    public ResponseEntity<AuthResponse> getAuth(@PathVariable Long authId) {

        AuthResponse authResponse = authService.getAuth(authId);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AuthResponse>> getAllAuths() {

        List<AuthResponse> authResponses = authService.getAllAuths();
        return ResponseEntity.ok(authResponses);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createAuth(@Valid @RequestBody CreateAuthRequest request) {

        authService.createAuth(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("권한 정보 생성 완료"));
    }

    @PutMapping("/{authId}")
    public ResponseEntity<MessageResponse> updateAuth(@PathVariable Long authId,
                                             @Valid @RequestBody UpdateAuthRequest request) {

        authService.updateAuth(authId, request);
        return ResponseEntity.ok(new MessageResponse("권한명 수정 완료"));
    }

    @DeleteMapping("/{authId}")
    public ResponseEntity<MessageResponse> deleteAuth(@PathVariable Long authId) {

        authService.deleteAuth(authId);
        return ResponseEntity.ok(new MessageResponse("권한 정보 삭제 완료"));
    }
}
