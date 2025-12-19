package com.example.umc_9th_chiki.Domain.Member.Exception.code;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),
    INVALID(HttpStatus.BAD_REQUEST,
            "MEMBER400",
            "잘못된 인증 정보입니다.") // (나중에 로그인 비밀번호 오류 시 사용)
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
