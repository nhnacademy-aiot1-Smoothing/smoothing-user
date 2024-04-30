package live.smoothing.user.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // ROLE 서비스 ERROR
    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "권한 정보를 찾을 수 없습니다"),
    DUPLICATED_ROLE(HttpStatus.CONFLICT, "이미 등록된 권한명 입니다."),

    // USER 서비스 ERROR
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다"),
    DUPLICATED_USER(HttpStatus.CONFLICT, "이미 등록된 유저 입니다."),

    // API 요청 ERROR
    ENCODING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "PW 인코딩 실패."),

    // POINT 서비스 ERROR
    INSUFFICIENT_BALANCE(HttpStatus.FORBIDDEN, "포인트 잔액이 부족합니다."),
    INVALID_POINT_AMOUNT(HttpStatus.BAD_REQUEST, "포인트 값이 유효하지 않습니다."),

    // Organization 서비스 ERROR
    MULTIPLE_ORGANIZATION_NOT_ALLOWED(HttpStatus.FORBIDDEN, "조직이 이미 등록 되어 있습니다."),
    ORGANIZATION_NOT_FOUND(HttpStatus.NOT_FOUND, "조직을 찾을 수 없습니다.");
    private final HttpStatus status;
    private final String message;
}
