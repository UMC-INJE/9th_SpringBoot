package dto;

import java.time.LocalDate;

public class UserReqDto {
	
    public record SignUpDto(
            String userName,
            String gender,
            LocalDate birthDate,
            String address,
            String detailAddress,
            String socialUid,
            String socialType,
            String email,
            String phone,
            Integer foodCategoryId
    ) {}
    
}
