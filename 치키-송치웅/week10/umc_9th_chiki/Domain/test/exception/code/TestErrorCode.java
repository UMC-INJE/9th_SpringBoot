package com.example.umc_9th_chiki.Domain.test.exception.code;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements BaseErrorCode {

    // TestException에서 사용할 오류 코드 정의
    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST_400_1", "이거는 테스트 예외");

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}