package live.smoothing.user.contoller;

import live.smoothing.user.auth.dto.AuthResponse;
import live.smoothing.user.auth.dto.CreateAuthRequest;
import live.smoothing.user.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<AuthResponse>> getAllAuths() {
        List<AuthResponse> authResponses = authService.getAllAuths();
        return ResponseEntity.ok(authResponses);
    }

    @PostMapping
    public ResponseEntity<Void> createAuth(@RequestBody CreateAuthRequest request) {
        authService.createAuth(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{authId}")
    public ResponseEntity<Void> updateAuth(@PathVariable Long authId, @RequestBody CreateAuthRequest request) {
        authService.deleteAuth(authId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{authId}")
    public ResponseEntity<Void> deleteAuth(@PathVariable Long authId) {
        authService.deleteAuth(authId);
        return ResponseEntity.ok().build();
    }
}
