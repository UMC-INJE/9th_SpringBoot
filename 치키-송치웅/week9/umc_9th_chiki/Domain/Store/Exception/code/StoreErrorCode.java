package com.example.umc_9th_chiki.Domain.Store.Exception.code;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    // ▼▼▼ 아까 코드에서 사용한 그 에러입니다! ▼▼▼
    NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404", "해당 가게를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}