package live.smoothing.user.advice;

import feign.FeignException;
import live.smoothing.common.dto.ErrorResponse;
import live.smoothing.user.advice.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {

        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + "는(은) " + (fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "알수없는 오류"))
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(errorMessage)
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> serviceInstanceNotFound(FeignException e, HttpServletRequest request) {

        String regex = "the service (.+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(e.getMessage());
        matcher.find();

        String errorMessage = matcher.group(1) + " 에게 요청을 실패 하였습니다.";

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ErrorResponse.builder()
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .errorMessage(errorMessage)
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> commonException(ServiceException e, HttpServletRequest request){

        return ResponseEntity.status(e.getStatus()).body(e.toEntity(request.getRequestURI()));
    }
}
