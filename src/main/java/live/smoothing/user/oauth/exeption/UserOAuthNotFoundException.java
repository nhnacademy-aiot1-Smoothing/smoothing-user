package live.smoothing.user.oauth.exeption;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class UserOAuthNotFoundException extends CommonException {
    public UserOAuthNotFoundException() {
        super(HttpStatus.NOT_FOUND, "OAuth 정보를 찾을 수 없습니다.");
    }
}
