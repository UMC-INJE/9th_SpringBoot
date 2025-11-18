package com.example.umc_9th_chiki.Domain.Review.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDTO {
    private Long reviewId;
    private String memberName;
    private String storeName;
    private Float star;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDto {
        private Long reviewId;
        private LocalDateTime createdAt;
    }
}