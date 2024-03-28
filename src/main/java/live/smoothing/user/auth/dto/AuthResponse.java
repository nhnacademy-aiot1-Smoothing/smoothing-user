package live.smoothing.user.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private Long authId;
    private String authInfo;

    // Getter, Setter로 메서드 생략

}
