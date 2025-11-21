package dto;

import lombok.Builder;

import java.time.LocalDateTime;
public class UserResDto {
	
	 @Builder
	    public record SignUpDto(
	            Integer userId,
	            LocalDateTime createdAt
	    ) {}
}
