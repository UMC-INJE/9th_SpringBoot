package com.example.umc9th.domain.store.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode {

    FOUND("STORE200_1", "성공적으로 조회되었습니다.");

    private final String code;
    private final String message;
}
