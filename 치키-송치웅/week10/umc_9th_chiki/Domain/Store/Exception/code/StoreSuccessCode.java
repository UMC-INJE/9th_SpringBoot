package com.example.umc_9th_chiki.Domain.Store.Exception.code;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    // 1. 가게 등록 성공
    CREATE_STORE_OK(HttpStatus.OK, "STORE201", "가게 등록을 성공했습니다."),
    // 2. 가게 정보 수정 성공
    UPDATE_STORE_OK(HttpStatus.OK, "STORE200", "가게 정보 수정을 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}