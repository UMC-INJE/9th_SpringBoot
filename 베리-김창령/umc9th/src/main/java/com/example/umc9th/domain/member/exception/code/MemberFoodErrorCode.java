package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberFoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBERFOOD404_1",
            "해당 사용자 선호 음식을 찾지 못했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
