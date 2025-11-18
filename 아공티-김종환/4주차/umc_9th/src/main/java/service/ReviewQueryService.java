package service;

import dto.ReviewResponse;
import entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // ★ 추가
import repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true) 
    public Page<ReviewResponse> getMyReviews(
            Integer userId,
            String storeName,
            Integer rating,
            int page,
            int size
    ) {
       
        String normalizedStoreName = (storeName != null && !storeName.isBlank())
                ? storeName.trim()
                : null;

        int safePage = Math.max(page, 0);
        int safeSize = Math.min(Math.max(size, 1), 100); 

        
        Pageable pageable = PageRequest.of(safePage, safeSize);

        Page<Review> result = reviewRepository.findMyReviews(
                userId,
                normalizedStoreName,
                rating,
                pageable
        );

        List<ReviewResponse> mapped = result.getContent()
                .stream()
                .map(ReviewResponse::from)
                .toList();

        return new PageImpl<>(mapped, pageable, result.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> searchMyReviews(
            Integer userId,
            String type,
            String query,
            int page,
            int size
    ) {
        String storeName = null;
        Integer rating = null;

        String t = type == null ? "" : type.trim().toLowerCase();
        String q = (query == null) ? "" : query.trim();

        switch (t) {
            case "location" -> storeName = q;
            case "star" -> rating = parseRating(q);
            case "both" -> {
                String[] parts = q.split("&", 2);
                if (parts.length == 2) {
                    storeName = parts[0].trim();
                    rating = parseRating(parts[1].trim());
                } else {
                    
                    storeName = q;
                }
            }
            default -> {
                
            }
        }

        
        if (storeName != null && storeName.isBlank()) storeName = null;

        return getMyReviews(userId, storeName, rating, page, size);
    }

    private Integer parseRating(String s) {
        try {
            int r = (int) Math.floor(Float.parseFloat(s));
            if (r < 1) r = 1;
            if (r > 5) r = 5;
            return r;
        } catch (Exception e) {
            return null;
        }
    }
}
