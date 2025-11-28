package com.example.umc9th.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK, "MISSION200_1", "미션 조회 성공"),
    CREATED(HttpStatus.CREATED, "MISSION201_1", "미션 생성 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
