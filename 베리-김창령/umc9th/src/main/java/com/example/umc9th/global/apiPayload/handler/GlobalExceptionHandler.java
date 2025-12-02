package com.example.umc9th.global.apiPayload.handler;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Valid 검증 실패
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(e -> e.getDefaultMessage() != null ? e.getDefaultMessage() : "잘못된 요청입니다.")
                .orElse("잘못된 요청입니다.");

        return ApiResponse.onFailure(
                GeneralErrorCode.INVALID_PARAMETER,
                errorMessage
        );
    }

    /**
     * 직접 던진 GeneralException 처리
     */
    @ExceptionHandler(GeneralException.class)
    public ApiResponse<String> handleGeneralException(GeneralException ex) {

        BaseErrorCode errorCode = ex.getCode();

        return ApiResponse.onFailure(
                errorCode,
                errorCode.getMessage()
        );
    }

    /**
     * 예상 못한 모든 예외 처리
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleException(Exception ex) {

        return ApiResponse.onFailure(
                GeneralErrorCode.INTERNAL_SERVER_ERROR,
                ex.getMessage() != null ? ex.getMessage() : "서버 내부 오류가 발생했습니다."
        );
    }
}
