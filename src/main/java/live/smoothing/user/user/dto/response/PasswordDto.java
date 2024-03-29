package live.smoothing.user.user.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordDto {

    String password;

    public PasswordDto(String password) {
        this.password = password;
    }
}
