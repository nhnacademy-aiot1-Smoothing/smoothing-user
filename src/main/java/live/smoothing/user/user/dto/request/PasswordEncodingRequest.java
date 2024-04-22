package live.smoothing.user.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordEncodingRequest {

    @NotNull
    String originalPassword;

    public PasswordEncodingRequest(String originalPassword) {

        this.originalPassword = originalPassword;
    }
}
