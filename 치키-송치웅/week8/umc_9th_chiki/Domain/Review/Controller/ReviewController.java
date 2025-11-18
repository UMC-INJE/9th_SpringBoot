package com.example.umc_9th_chiki.Domain.Review.Controller;

import com.example.umc_9th_chiki.Domain.Review.Converter.ReviewConverter;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.service.query.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/search")
    public List<ReviewResponseDTO> searchReview(
            @RequestParam String query,
            @RequestParam String type) {

        log.info("API 호출됨 (searchReview): query='{}', type='{}'", query, type);
        List<Review> reviewList = reviewQueryService.searchReview(query, type);
        return ReviewConverter.toReviewResponseDtoList(reviewList);
    }

    @GetMapping("/members/{memberId}/reviews")
    public List<ReviewResponseDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating
    ) {
        log.info("API 호출됨 (getMyReviews): memberId={}, storeName='{}', rating={}", memberId, storeName, rating);
        List<Review> reviewList = reviewQueryService.findMyReviews(memberId, storeName, rating);
        return ReviewConverter.toReviewResponseDtoList(reviewList);
    }
}