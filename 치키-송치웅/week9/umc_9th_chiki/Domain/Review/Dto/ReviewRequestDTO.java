package com.example.umc_9th_chiki.Domain.Review.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class JoinDto {
        @NotNull
        private Long memberId;

        @NotNull
        private String body;

        @NotNull
        private Float score;
    }
}