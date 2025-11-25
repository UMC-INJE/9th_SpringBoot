package com.example.umc_9th_chiki.Domain.Review.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDto {
        private Long reviewId;
        private LocalDateTime createdAt;
    }

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt,
            String storeName
    ){}
}