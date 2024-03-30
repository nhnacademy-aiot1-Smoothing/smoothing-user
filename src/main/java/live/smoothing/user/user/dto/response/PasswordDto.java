package live.smoothing.user.user.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordDto {

    @NotNull
    String password;

    public PasswordDto(String password) {

        this.password = password;
    }
}
