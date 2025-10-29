package com.example.umc_9th_chiki.Domain.Review.Converter;

import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDto;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDto toReviewResponseDto(Review review) {
        String memberName = (review.getMember() != null) ? review.getMember().getName() : null;
        String storeName = (review.getStore() != null) ? review.getStore().getName() : null;

        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .memberName(memberName)
                .storeName(storeName)
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static List<ReviewResponseDto> toReviewResponseDtoList(List<Review> reviewList) {
        if (reviewList == null) {
            return List.of();
        }
        return reviewList.stream()
                .map(ReviewConverter::toReviewResponseDto)
                .collect(Collectors.toList());
    }
}