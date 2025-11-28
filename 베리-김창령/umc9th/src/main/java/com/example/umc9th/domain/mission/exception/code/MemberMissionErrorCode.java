package com.example.umc9th.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_MISSION404_1", "해당 회원 미션을 찾을 수 없습니다."),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MEMBER_MISSION400_1", "이미 완료된 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
