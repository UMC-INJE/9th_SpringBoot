package com.example.umc_9th_chiki.Domain.Review.Dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDto {
    private Long reviewId;
    private String memberName; // Member 엔티티 대신 이름만
    private String storeName;  // Store 엔티티 대신 이름만
    private Float star;
    private String content;
    private LocalDateTime createdAt;
}