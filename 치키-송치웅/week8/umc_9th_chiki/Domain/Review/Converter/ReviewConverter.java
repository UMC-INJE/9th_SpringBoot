package com.example.umc_9th_chiki.Domain.Review.Converter;

import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewRequestDTO;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.CreateResultDto toCreateResultDto(Review review) {
        return ReviewResponseDTO.CreateResultDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.JoinDto request) {
        return Review.builder()
                .content(request.getBody()) // DTO의 body -> Entity의 content
                .star(request.getScore())   // DTO의 score -> Entity의 star
                .build();
    }

    public static ReviewResponseDTO toReviewResponseDto(Review review) {
        String memberName = (review.getMember() != null) ? review.getMember().getName() : null;
        String storeName = (review.getStore() != null) ? review.getStore().getName() : null;

        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .memberName(memberName)
                .storeName(storeName)
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static List<ReviewResponseDTO> toReviewResponseDtoList(List<Review> reviewList) {
        if (reviewList == null) {
            return List.of();
        }
        return reviewList.stream()
                .map(ReviewConverter::toReviewResponseDto)
                .collect(Collectors.toList());
    }
}