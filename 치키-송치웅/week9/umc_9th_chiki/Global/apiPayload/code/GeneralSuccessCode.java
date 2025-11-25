package com.example.umc_9th_chiki.Global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "200 OK",
            "요청에 성공했습니다."),
    CREATED(HttpStatus.CREATED,
            "201 Created",
            "리소스가 성공적으로 생성되었습니다."),
    ACCEPTED(HttpStatus.ACCEPTED,
            "202 Accepted",
            "요청을 수신했습니다."),
    NON_AUTHORITATIVE_INFORMATION(HttpStatus.NON_AUTHORITATIVE_INFORMATION,
            "203 NonInformation",
            "신뢰한 수 없는 정보입니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT,
            "204 no content",
            "콘텐츠가 없습니다."),
    RESET_CONTENT(HttpStatus.RESET_CONTENT,
            "205 Reset Content",
            "콘텐츠를 재설정합니다."),
    PARTIAL_CONTENT(HttpStatus.PARTIAL_CONTENT,
            "206 Partial Content",
            "일부 콘텐츠만 보냅니다."),
    MULTI_STATUS(HttpStatus.MULTI_STATUS,
            "207 Multi-Status",
            "처리 결과의 스테이터스가 여러 개입니다.(다중상태)"),
    GET_REVIEW_LIST_SUCCESS(HttpStatus.OK,
            "REVIEW_001",
            "리뷰 목록 조회가 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }
    @Override
    public String getCode() {
        return this.code;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
