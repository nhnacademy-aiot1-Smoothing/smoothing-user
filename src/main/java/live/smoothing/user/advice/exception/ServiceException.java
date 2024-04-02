package live.smoothing.user.advice.exception;

import live.smoothing.common.exception.CommonException;
import live.smoothing.user.advice.ErrorCode;

public class ServiceException extends CommonException {
    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.getMessage());
    }
}
