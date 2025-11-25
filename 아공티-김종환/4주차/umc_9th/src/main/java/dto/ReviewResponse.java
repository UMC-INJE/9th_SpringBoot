package dto;

import entity.Review;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewResponse(
        Integer reviewId,
        Integer storeId,
        String storeName,
        Integer rating,
        String content,
        LocalDateTime createdAt
) {

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getStoreId())          
                .storeName(review.getStore().getStoreName())      
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
