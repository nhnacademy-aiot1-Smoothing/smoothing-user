package live.smoothing.user.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDetailResponse {

    private final String userId;
    private final String userPassword;
    private final String userName;
    private final String userEmail;
}
