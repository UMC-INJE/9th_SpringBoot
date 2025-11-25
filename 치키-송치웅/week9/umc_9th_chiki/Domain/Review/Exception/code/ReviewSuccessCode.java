package com.example.umc_9th_chiki.Domain.Review.Exception.code;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATE_OK(HttpStatus.OK, "REVIEW200", "리뷰 작성을 성공했습니다."),
    REVIEW_DELETE_OK(HttpStatus.OK, "REVIEW200", "리뷰 삭제를 성공했습니다."),
    FOUND(HttpStatus.OK, "REVIEW200", "리뷰 목록 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}