package exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import response.ErrorResponse;
import response.ExceptionEnum;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ErrorResponse<?> handleCustomException(CustomException ex) {
        return ErrorResponse.of(ex.getExceptionEnum());
    }

    // 예상 못한 모든 오류 처리
    @ExceptionHandler(Exception.class)
    public ErrorResponse<?> handleException(Exception ex) {
        return ErrorResponse.of(ExceptionEnum.SERVER_ERROR);
    }
}
