package com.example.umc9th.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
