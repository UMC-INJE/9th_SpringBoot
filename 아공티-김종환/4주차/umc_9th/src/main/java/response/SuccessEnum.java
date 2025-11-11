package response;

public enum SuccessEnum {
    OK("SUCCESS", 200, "요청이 성공적으로 처리되었습니다."),
    CREATED("SUCCESS", 201, "리소스가 성공적으로 생성되었습니다."),
    UPDATED("SUCCESS", 200, "리소스가 성공적으로 수정되었습니다."),
    DELETED("SUCCESS", 200, "리소스가 성공적으로 삭제되었습니다.");

    private final String status;
    private final int code;
    private final String message;

    SuccessEnum(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getStatus() { return status; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
}

