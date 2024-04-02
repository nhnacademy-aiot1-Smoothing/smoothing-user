package live.smoothing.user.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSimpleResponse {

    private final String userId;
    private final String userPassword;
}

