package live.smoothing.user.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Auth 서비스 ERROR
    AUTH_NOT_FOUND(HttpStatus.NOT_FOUND, "권한 정보를 찾을 수 없습니다"),
    DUPLICATED_AUTH(HttpStatus.CONFLICT, "이미 등록된 권한명 입니다."),

    // USER 서비스 ERROR
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다"),
    DUPLICATED_USER(HttpStatus.CONFLICT, "이미 등록된 유저 입니다."),

    // API 요청 ERROR
    ENCODING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "PW 인코딩 실패.");


    private final HttpStatus status;
    private final String message;
}
