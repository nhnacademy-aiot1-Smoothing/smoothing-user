package live.smoothing.user.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoModifyRequest {

    @NotNull
    @Size(max = 30, message = "최대 30자 까지 입니다")
    private String userName;

    @NotNull
    @Size(max = 100, message = "최대 100자 까지 입니다")
    private String userEmail;
}
