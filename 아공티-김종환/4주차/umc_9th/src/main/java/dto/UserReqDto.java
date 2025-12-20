package dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserReqDto {

    @Builder
    public record SignUpDto(
            Integer foodCategoryId,
            String userName,
            String gender,
            LocalDate birthDate,
            String address,
            String detailAddress,
            String email,
            String password,
            String phone
    ) {}

    @Builder
    public record LoginDto(
            String email,
            String password
    ) {}
}
