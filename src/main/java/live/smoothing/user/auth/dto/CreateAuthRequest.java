package live.smoothing.user.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateAuthRequest {

    @NotNull
    @Size(max = 30, message = "최대 30자 까지 입니다")
    private String authInfo;

    public CreateAuthRequest(String authInfo) {
        this.authInfo = authInfo;
    }
}
