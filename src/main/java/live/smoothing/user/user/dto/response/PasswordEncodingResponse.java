package live.smoothing.user.user.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordEncodingResponse {

    @NotNull
    String encodedPassword;

    public PasswordEncodingResponse(String encodedPassword) {

        this.encodedPassword = encodedPassword;
    }
}
