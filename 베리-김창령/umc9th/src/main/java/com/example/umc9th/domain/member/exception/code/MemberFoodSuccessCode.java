package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberFoodSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MEMBERFOOD200_1",
            "성공적으로 사용자 선호 음식 정보를 조회했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
