package dto;

import entity.Review;
import java.time.LocalDateTime;

public record ReviewResponse(
        Integer id,          // ← Long → Integer 로 변경
        String storeName,
        Integer rating,
        String content,
        LocalDateTime createdAt
) {
    public static ReviewResponse from(Review r) {
        return new ReviewResponse(
                r.getId(),                         // OK: Integer → Integer
                r.getStore().getStoreName(),
                r.getRating(),
                r.getContent(),
                r.getCreatedAt()
        );
    }
}
