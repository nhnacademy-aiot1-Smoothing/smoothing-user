package live.smoothing.user.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPWModifyRequest {

    @NotNull
    @Size(min = 10, max = 50, message = "10 ~ 100자 까지 입니다")
    private String userPassword;
}
