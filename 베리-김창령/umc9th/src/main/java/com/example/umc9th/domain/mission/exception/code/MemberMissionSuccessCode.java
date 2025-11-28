package com.example.umc9th.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.OK, "MEMBER_MISSION200_0", "회원 미션 도전 성공"),
    FOUND(HttpStatus.OK, "MEMBER_MISSION200_1", "회원 미션 조회 성공"),
    UPDATED(HttpStatus.OK, "MEMBER_MISSION200_2", "회원 미션 완료 처리 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
