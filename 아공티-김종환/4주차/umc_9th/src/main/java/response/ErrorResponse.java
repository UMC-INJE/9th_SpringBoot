package response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse<T> {
    private String status;
    private int code;
    private String message;
    private T data;

    public static <T> ErrorResponse<T> of(ExceptionEnum exceptionEnum, T data) {
        return new ErrorResponse<>(
            exceptionEnum.getStatus(),
            exceptionEnum.getCode(),
            exceptionEnum.getMessage(),
            data
        );
    }

    public static ErrorResponse<Void> of(ExceptionEnum exceptionEnum) {
        return new ErrorResponse<>(
            exceptionEnum.getStatus(),
            exceptionEnum.getCode(),
            exceptionEnum.getMessage(),
            null
        );
    }
}
