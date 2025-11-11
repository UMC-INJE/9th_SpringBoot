package response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private int code;
    private String message;
    private T data;

   

    public static <T> ApiResponse<T> success(T data, SuccessEnum successEnum) {
        return new ApiResponse<>(successEnum.getStatus(), successEnum.getCode(), successEnum.getMessage(), data);
    }

    public static <T> ApiResponse<T> success(SuccessEnum successEnum) {
        return new ApiResponse<>(successEnum.getStatus(), successEnum.getCode(), successEnum.getMessage(), null);
    }

}

