package com.example.umc_9th_chiki.Domain.Review.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPreViewDTO {
    private String ownerNickname; // 리뷰 주인
    private Float score;
    private String body;
    private LocalDate createdAt;
    private String storeName;
}