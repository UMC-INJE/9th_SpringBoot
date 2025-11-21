package response;

public enum ExceptionEnum {
    BAD_REQUEST("FAIL", 400, "잘못된 요청입니다."),
    UNAUTHORIZED("FAIL", 401, "인증이 필요합니다."),
    FORBIDDEN("FAIL", 403, "접근 권한이 없습니다."),
    NOT_FOUND("FAIL", 404, "리소스를 찾을 수 없습니다."),
    CONFLICT("FAIL", 409, "이미 존재하거나 충돌이 발생했습니다."),
    SERVER_ERROR("FAIL", 500, "서버 내부 오류가 발생했습니다.");

    private final String status;
    private final int code;
    private final String message;

    ExceptionEnum(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getStatus() { return status; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
}
